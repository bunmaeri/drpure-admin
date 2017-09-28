package admin.common.util;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {
	private static String filePath;
	private static String viewPath;
	
	public void parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
		String filePath = getFilePath();
		String viewPath = getViewPath();
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
    	Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
    	
    	MultipartFile multipartFile = null;
    	String originalFileName = null;
    	String originalFileExtension = null;
    	String storedFileName = null;
    	
    	File file = new File(filePath);
        if(file.exists() == false){
        	file.mkdirs();
        }
        
        while(iterator.hasNext()){
        	multipartFile = multipartHttpServletRequest.getFile(iterator.next());
        	if(multipartFile.isEmpty() == false){
        		originalFileName = multipartFile.getOriginalFilename();
        		originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        		storedFileName = CommonUtils.getRandomString() + originalFileExtension;
//        	System.err.println(storedFileName);
//        	System.err.println(originalFileName);
        		file = new File(filePath + storedFileName);
        		multipartFile.transferTo(file);
        		
        		map.put("image", viewPath+storedFileName);
        		map.put("original_image", viewPath+originalFileName);
//        		listMap = new HashMap<String,Object>();
//        		listMap.put("PRODUCT_IDX", productIdx);
//        		listMap.put("ORIGINAL_FILE_NAME", originalFileName); 
//        		listMap.put("STORED_FILE_NAME", storedFileName);
//        		listMap.put("FILE_SIZE", multipartFile.getSize());
//        		list.add(listMap);
        	}
        }
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		FileUtils.filePath = filePath;
	}

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		FileUtils.viewPath = viewPath;
	}
}
