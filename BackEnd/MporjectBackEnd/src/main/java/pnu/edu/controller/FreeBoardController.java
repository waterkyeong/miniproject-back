package pnu.edu.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pnu.edu.domain.FreeBoard;
import pnu.edu.service.FreeBoardService;

@Slf4j
@RequiredArgsConstructor
@RestController
@MultipartConfig(
		maxFileSize = 1000000,
		maxRequestSize = 10000000
		)
public class FreeBoardController {

	private final FreeBoardService freeService;
	
	@GetMapping("/public/freeboard")
	public ResponseEntity<?> getFreeBoard(@RequestParam(required = false, defaultValue ="1") Integer page,  @RequestParam(required = false, defaultValue ="10") Integer size) {
		log.info("get freeBoard: All");
		return ResponseEntity.ok(freeService.getFreeBoard(page, size));
	}
	
	@GetMapping("/public/freeboard/{freeboardid}")
	public ResponseEntity<?> getFreeBoard(@PathVariable int freeboardid) {
		log.info("get freeBoard: id");
		return ResponseEntity.ok(freeService.getFreeBoard(freeboardid));
	}
	
	@PostMapping("/api/freeboard")
	public ResponseEntity<?> insertFreeBoard(@RequestPart(value="freeboarddata") FreeBoard freeBoard,
			@RequestPart(value = "files") MultipartFile[] files) throws IllegalStateException, IOException {
		log.info("insert freeBoard");
		return ResponseEntity.ok(freeService.insertFreeBoard(freeBoard, files));
	}
	
	@PutMapping("/api/freeboard")
	public ResponseEntity<?> updateFreeBoard(@RequestPart(value="freeboarddata") FreeBoard freeBoard,
			@RequestPart(value = "files") MultipartFile[] files) throws IllegalStateException, IOException {
		log.info("update freeBoard");
		return ResponseEntity.ok(freeService.updateFreeBoard(freeBoard, files));
	}
	
	@DeleteMapping("/api/freeboard/{freeboardid}")
	public ResponseEntity<?> deleteFreeBoard(@PathVariable int freeboardid) {
		log.info("delete freeboard");
		return ResponseEntity.ok(freeService.deleteFreeBoard(freeboardid));
	}
}
