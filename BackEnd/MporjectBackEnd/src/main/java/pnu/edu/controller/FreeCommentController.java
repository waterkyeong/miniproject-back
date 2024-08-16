package pnu.edu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pnu.edu.domain.FreeComment;
import pnu.edu.domain.dto.FreeCommentDTO;
import pnu.edu.service.FreeBoardCommentService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FreeCommentController {

	private final FreeBoardCommentService fcServcie;
	
	
	@PostMapping("/api/freeboard/{freeboardid}/freecomment")
	public ResponseEntity<?> insertFreeComment(@PathVariable int freeboardid, /*@RequestParam String username,*/ @RequestBody FreeCommentDTO freeCommentDTO ) {
		log.info("insert FreeComment");
		return ResponseEntity.ok(fcServcie.insertFreeComment(freeboardid,/* username,*/ freeCommentDTO));
	}
	
	@PutMapping("/api/freeboard/freecomment/{freecommentid}")
	public ResponseEntity<?> updateFreeComment(@PathVariable int freecommentid, @RequestBody FreeComment freeComment) {
		log.info("update FreeComment");
		return ResponseEntity.ok(fcServcie.updateFreeComment(freecommentid, freeComment));
	}
	
	@DeleteMapping("/api/freeboard/freecomment/{freecommentid}")
	public ResponseEntity<?> deleteFreeComment(@PathVariable int freecommentid) {
		log.info("delete FreeComment");
		return ResponseEntity.ok(fcServcie.deleteFreeComment(freecommentid));
	}
}
