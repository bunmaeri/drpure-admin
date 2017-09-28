package admin.common.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import admin.common.common.CommandMap;
import admin.common.service.CommonService;
import admin.common.util.CommonUtils;
import admin.common.util.StoreUtils;
import admin.common.util.FileUtils;

import com.froala.editor.Image;
import com.google.gson.Gson;

@Controller
public class CommonController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;
	
	@RequestMapping(value="/common/downloadFile.do")
	public void downloadFile(CommandMap commandMap, HttpServletResponse response) throws Exception{
		Map<String,Object> map = commonService.selectFileInfo(commandMap.getMap());
//		String storedFileName = (String)map.get("STORED_FILE_NAME");
//		String originalFileName = (String)map.get("ORIGINAL_FILE_NAME");
//		
//		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\dev\\file\\"+storedFileName));
//		
//		response.setContentType("application/octet-stream");
//		response.setContentLength(fileByte.length);
//		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
//		response.setHeader("Content-Transfer-Encoding", "binary");
//		response.getOutputStream().write(fileByte);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	/**
	 * 이미지 업로드
	 * @param request
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/imageUpload.dr")
    public ModelAndView imageUpload(HttpServletRequest request, CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		
		String folder = "contents/";
		fileUtils.setFilePath(StoreUtils.getFilepath()+folder);
		fileUtils.setViewPath(folder);
		fileUtils.parseInsertFileInfo(commandMap.getMap(), request);
		
		String imageLink = "/image/" + commandMap.get2String("image");
//		System.err.println(imageLink);
		mv.addObject("link", imageLink);
		return mv;
	}
	
	/**
	 * 이미지 목록 조회
	 * @param request
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/imageManager.dr")
    public void imageManager(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ArrayList<Object> responseData = new ArrayList<Object>();
		
		String folder = StoreUtils.getFilepath()+"contents/";
		//특정 directory를 File 객체로 생성
		File directory = new File(folder);

		//특정 directory 내 파일 목록 가져오기
		File[] files = directory.listFiles();

		String viewFolder = "/image/contents/";
		Map<String,Object> img = null;
		for (File file : files) {
			//파일이 directory 가 아닌 file 일때
			if (file.isFile()) {
				img = new HashMap<String,Object>();
				img.put("url", viewFolder+file.getName());
				img.put("thumb", viewFolder+file.getName());
				img.put("name", file.getName());
				//file 명 console 창에 뿌려주기
//				System.out.println(viewFolder+file.getName());
				responseData.add(img);
			}
		}

		String jsonResponseData = new Gson().toJson(responseData);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponseData);
	}
	
	/**
	 * 이미지 삭제
	 * @param request
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteImage.dr")
    public void deleteImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String src = request.getParameter("src");
//		System.err.println("==="+StoreUtils.getDeleteFilepath()+src);
        try {
        	java.io.File file = new java.io.File(StoreUtils.getDeleteFilepath()+src);
    		if (file.exists()) {
//    			System.err.println("name>>"+file.getName());
    			file.delete();
    		}
            //Image.delete(request, src);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        String jsonResponseData = new Gson().toJson("Success");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponseData);
	}
	
	/**
	 * 접속 불가
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/denied/access.dr")
    public ModelAndView blank(HttpServletRequest request, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/denied_access");
    	
    	mv.addObject("ip", CommonUtils.getClientIP(request));
    	return mv;
    }
	
	/**
	 * 톰캣서버 활성화 여부 체크
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/check.dr")
    public ModelAndView check(CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/check");
    	
    	return mv;
    }
}
