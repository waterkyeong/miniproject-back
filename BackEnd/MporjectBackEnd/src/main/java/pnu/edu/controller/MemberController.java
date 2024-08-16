package pnu.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pnu.edu.domain.Member;
import pnu.edu.service.MemberService;

@Slf4j
@RestController
public class MemberController {

	@Autowired
	private MemberService memService;
	
//	@GetMapping("/signin")
//	public ResponseEntity<?> getsignin() {return null;}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody Member member) {
		log.info("signin");
		return ResponseEntity.ok(memService.signin(member));
	}
}
