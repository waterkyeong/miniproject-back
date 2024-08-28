package pnu.edu.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Fileutil {
	
	
//	@Value("${spring.servlet.multipart.location}")
	private static String location = "C:/Mproject/BackEnd/MporjectBackEnd/src/main/resources/static/photos";
	
	public static String[] uploadImg(MultipartFile[] files) {
		String [] fnames = new String[files.length];
		if(files != null && 0<files.length) {
			try {
				for(int i = 0 ; i < files.length ; i++) {
					System.out.println("file:"+location+File.separator+files[i].getOriginalFilename());
					files[i].transferTo(new File(location+File.separator+files[i].getOriginalFilename()));
					
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

//	public static Map<String, Object> getResourceByImgId(int imgid) { // 하나만 받는메소드 이 메소드는 파일을 다운받을때 사용하면 된다.
//		
//		Resource resource = null;
//		Map<String, Object> map = new HashMap<>();
//		//셋중에 하나만 넘어가야함.
//		// 성공인경우
//		map.put("image", resource);
//		map.put("message", "ok");
//		// db에 없을경우
//		map.put("message", "Not Found Record");
//		// db에 있지만,파일이 없는경우
//		map.put("message", "Not Found File");
//		
//		return null;
//	}
	
}
