package pnu.edu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pnu.edu.domain.ShareBoard;
import pnu.edu.service.ShareBoardService;


@Slf4j
@RestController
@RequiredArgsConstructor
@MultipartConfig(
		maxFileSize = 1000000,
		maxRequestSize = 10000000
		)
public class ShareBoardController {

	private final ShareBoardService shareService;
	
	@GetMapping("/public/shareboard")
	public ResponseEntity<?> getShareBoard(){
		return ResponseEntity.ok(shareService.getShareBoard());
	}
//	public ResponseEntity<?> getShareBoard(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "15") Integer size) {
//		log.info("get shareBoard : All");
//		return ResponseEntity.ok(shareService.getShareBoard(page, size));
//	}
	
	@GetMapping("/public/shareboard/{shareboardid}")
	public ResponseEntity<?> getShareBoard(@PathVariable int shareboardid) {
		log.info("get shareBoard : Id");
		return ResponseEntity.ok(shareService.getShareBoard(shareboardid));
	}
	
	@PostMapping("/api/shareboard")
	public ResponseEntity<?> insertShareBoard(@RequestPart(value="shareboarddata") ShareBoard shareBoard,
			@RequestPart(value = "files", required = false) MultipartFile[] files) {
		log.info("insert shareBoard");
		return ResponseEntity.ok(shareService.insertShareBoard(shareBoard, files));
	}
	
	@PutMapping("/api/shareboard")
	public ResponseEntity<?> updateShareBoard(@RequestPart(value ="shareboarddata", required = false) ShareBoard shareBoard,
			@RequestPart(value = "files", required = false) MultipartFile[] files) {
		log.info("update shareBoard");
		return ResponseEntity.ok(shareService.updateShareBoard(shareBoard, files));
	}
	
	@DeleteMapping("/api/shareboard/{shareboardid}")
	public ResponseEntity<?> deleteShareBoard(@PathVariable int shareboardid) {
		log.info("delete shareBoard");
		return ResponseEntity.ok(shareService.deleteShareBoard(shareboardid));
	}
}
