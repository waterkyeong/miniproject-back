package pnu.edu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FreeBoardImgController {

	@PostMapping("/api/freeboard/upload")
	public ResponseEntity<?> uploadFBIfiles(@RequestPart MultipartFile fbifiles ) {
		return null;
		
	}
}
