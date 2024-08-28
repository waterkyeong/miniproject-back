package pnu.edu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pnu.edu.domain.ShareComment;
import pnu.edu.domain.dto.ShareCommentDTO;
import pnu.edu.service.ShareBoardCommentService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShareCommentController {

	private final ShareBoardCommentService scService;
	
	@PostMapping("/users/shareboard/{shareboardid}/sharecomment")
	public ResponseEntity<?> insertShareComment(@PathVariable int shareboardid, @AuthenticationPrincipal User principal, @RequestBody ShareCommentDTO shareCommentDTO) {
		log.info("insert ShareComment");
		return ResponseEntity.ok(scService.insertFreeComment(shareboardid, principal.getUsername(), shareCommentDTO));
	}
	
	@PutMapping("/users/shareboard/sharecomment/{sharecommentid}")
	public ResponseEntity<?> updateShareComment(@PathVariable int sharecommentid, @RequestBody ShareComment shareComment) {
		log.info("update ShareComment");
		return ResponseEntity.ok(scService.updateShareComment(sharecommentid, shareComment));
	}
	
	@DeleteMapping("/users/shareboard/sharecomment/{sharecommentid}")
	public ResponseEntity<?> deleteShareComment(@PathVariable int sharecommentid) {
		log.info("delete ShareComment");
		return ResponseEntity.ok(scService.deleteShareComment(sharecommentid));
	}
}
