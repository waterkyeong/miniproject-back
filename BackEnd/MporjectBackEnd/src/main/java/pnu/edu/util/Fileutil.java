package pnu.edu.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class Fileutil {
	
	
	@Value("${spring.servlet.multipart.location}")
	private static String location;
	
	public static void uploadImg(MultipartFile[] files) {
		if(files != null && 0<files.length) {
			try {
				for(MultipartFile file : files) {
					System.out.println("file:"+location+file.getOriginalFilename());
					file.transferTo(new File(location+file.getOriginalFilename()));
				}
			}catch(Exception e) {
				e.getMessage();
			}
		}
	}
}
