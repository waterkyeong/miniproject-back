package pnu.edu.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class Fileutil {
	
	
	@Value("${spring.servlet.multipart.location}")
	private static String location;
	
	public static String[] uploadImg(MultipartFile[] files) {
		String [] fnames = new String[files.length];
		if(files != null && 0<files.length) {
			try {
				for(int i = 0 ; i < files.length ; i++) {
					System.out.println("file:"+location+files[i].getOriginalFilename());
					files[i].transferTo(new File(location+files[i].getOriginalFilename()));
					
					//rename
					fnames[i] = renameFile(location, files[i].getOriginalFilename());
				}
			}catch(Exception e) {
				e.getMessage();
			}
		}
		return fnames;
	}
	
	private static String renameFile(String sDirectyory, String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String newFileName = now + ext;
		File oldFile = new File(sDirectyory + File.separator + fileName);
		File newFile = new File(sDirectyory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		
		return newFileName;
	}
}
