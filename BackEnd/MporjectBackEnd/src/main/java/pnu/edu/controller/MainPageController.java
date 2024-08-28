package pnu.edu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pnu.edu.service.MainPageService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainPageController {

	private final MainPageService mpSer;
	
	@GetMapping("/public/freeboard/latest")
	public ResponseEntity<?> getLatestFB(){
		log.info("getBoard : mainFb");
		return ResponseEntity.ok(mpSer.getLatestfiveFB());
	}

	@GetMapping("/public/shareboard/latest")
	public ResponseEntity<?> getLatestSB(){
		log.info("getBoard : mainSb");
		return ResponseEntity.ok(mpSer.getLatestfiveSB());
	}
	
}
