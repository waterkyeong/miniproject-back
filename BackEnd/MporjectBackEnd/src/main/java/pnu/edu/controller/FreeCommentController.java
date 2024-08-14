package pnu.edu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pnu.edu.domain.FreeComment;
import pnu.edu.service.FreeBoardCommentService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FreeCommentController {

	private final FreeBoardCommentService fcServcie;
	
	
	@PostMapping("/api/freeboard/{freeboardid}/freecomment")
	public ResponseEntity<?> insertFreeComment(@PathVariable int freeboardid, @RequestParam String username, @RequestBody FreeComment freeComment ) {
		log.info("insert FreeComment");
		return ResponseEntity.ok(fcServcie.insertFreeComment(freeboardid, username, freeComment));
	}
	
}
