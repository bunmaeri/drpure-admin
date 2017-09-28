package admin.myhome.contents.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import admin.common.common.CommandMap;
import admin.common.constant.Session;
import admin.common.controller.BaseController;
import admin.common.util.FileUtils;
import admin.common.util.ObjectUtils;
import admin.common.util.Pagemaker;
import admin.common.util.ScriptUtils;
import admin.myhome.common.util.MyStoreUtils;
import admin.myhome.contents.service.MyBooksService;
import admin.system.service.LanguagesService;

@Controller
public class MyBooksController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myBooksService")
	private MyBooksService myBooksService;
	
	@Resource(name="languagesService")
	private LanguagesService languagesService;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;

	/**
	 * Contents > books
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_contents/books.dr")
    public ModelAndView book(CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_contents/books");

    	commandMap.put("language_id", MyStoreUtils.getLanguageId());
    	List<Map<String,Object>> list = myBooksService.books(commandMap.getMap());
    	mv.addObject("list", list);
    	
    	mv.addObject("meta_title", "책");
    	
    	return mv;
    }

	/**
	 * Contents > book
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_contents/book/add.dr")
    public ModelAndView bookNew(Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_contents/book_info");
    	
    	Map<String,Object> info = new HashMap<String,Object>();
    	info.put("language_id", MyStoreUtils.getLanguageId());
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Add");
    	mv.addObject("book_id", "0");
    	mv.addObject("language_id", MyStoreUtils.getLanguageId());
    	
    	mv.addObject("meta_title", "책");
    	
    	return mv;
    }
	
	/**
	 * Contents > book
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_contents/book/{book_id}/{language_id}.dr")
    public ModelAndView bookEdit(HttpServletRequest request, @PathVariable String book_id, @PathVariable String language_id, Pagemaker pagemaker, CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/my_contents/book_info");
    	
    	/**
    	 * 기본 정보
    	 */
    	commandMap.put("book_id", book_id);
    	commandMap.put("language_id", 1);
    	
    	Map<String,Object> book = myBooksService.bookInfo(commandMap.getMap());
    	mv.addObject("book", book.get("map"));
    	
    	/**
    	 * 언어별 정보
    	 */
    	commandMap.put("language_id", language_id);
    	Map<String,Object> info = myBooksService.bookInfo(commandMap.getMap());
    	mv.addObject("info", info.get("map"));
    	
    	mv.addObject("languages", languagesService.languages());
    	mv.addObject("title", "Edit");
    	mv.addObject("book_id", book_id);
    	mv.addObject("language_id", language_id);
    	
    	if(null!=BaseController.getCustomSession(request, Session.SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SUCCESS);
    	}
    	if(null!=BaseController.getCustomSession(request, Session.ERROR)) {
    		mv.addObject("errorMsg", BaseController.getCustomSession(request, Session.ERROR));
    		BaseController.setCustomSession(request, null, Session.ERROR);
    	}
    	
    	ScriptUtils.bookViewImaeScript(mv);
    	
    	return mv;
    }
	
	/**
	 * book 저장
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_contents/book/save.dr")
    public ModelAndView bookSave(HttpServletRequest request, CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/my_contents/book/"+commandMap.get2String("book_id")+"/"+commandMap.get2String("language_id")+".dr");
    	
		Map<String, Object> validMap = new HashMap<String,Object>();
		this.validForm(validMap, commandMap);
		
		if(ObjectUtils.isEmpty(validMap)) {
			if(commandMap.getMap().get("sort_order").equals("")) {
				commandMap.put("sort_order", "0");
			}
			int count = myBooksService.isBook(commandMap.getMap());
			if(count==0) {
				if(commandMap.getMap().get("book_id").equals("0")) {
					commandMap.put("book_id", myBooksService.maxBookId(commandMap.getMap()));
				}
				myBooksService.addBook(commandMap.getMap());
			} else {
				myBooksService.updateBook(commandMap.getMap());
			}
			
			// 이미지 업로드
			String folder = "catalog/blog/";
			fileUtils.setFilePath(MyStoreUtils.getFilepath()+folder);
			fileUtils.setViewPath(folder);
			fileUtils.parseInsertFileInfo(commandMap.getMap(), request);
			if(!commandMap.get2String("image").equals("")) {
				myBooksService.updateImage(commandMap.getMap());
			}
		
			BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
		} else {
			List<String> errList = new ArrayList<String>();
			if(!ObjectUtils.isEmpty(validMap.get("title"))) {
				errList.add(ObjectUtils.null2void(validMap.get("title")));
			}
			if(!ObjectUtils.isEmpty(validMap.get("description"))) {
				errList.add(ObjectUtils.null2void(validMap.get("description")));
			}
			if(!ObjectUtils.isEmpty(validMap.get("meta_title"))) {
				errList.add(ObjectUtils.null2void(validMap.get("meta_title")));
			}
			BaseController.setCustomSession(request, errList, Session.ERROR);
//			validMap.put("warning", Message.WARNING);
//			mv.addObject("Error", validMap);
		}
    	return mv;
    }
	
	public void validForm(Map<String, Object> validMap, CommandMap commandMap) throws Exception{
		String title = commandMap.get("title").toString();
		if(ObjectUtils.isEmpty(title) || (title.length()<3 && title.length()>64)) {
			BaseController.setErrorMessage(validMap, "title", "제목을 입력하세요.");
		}
		
		String description = commandMap.get("description").toString();
		if(ObjectUtils.isEmpty(description) || description.length()<1) {
			BaseController.setErrorMessage(validMap, "description", "내용을 입력하세요.");
		}
		
		String meta_title = commandMap.get("meta_title").toString();
		if(ObjectUtils.isEmpty(meta_title) || meta_title.length()<1) {
			BaseController.setErrorMessage(validMap, "meta_title", "Meta Title은 반드시 입력하셔야 합니다.");
		}
	}
	
	/**
	 * book 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/my_contents/book/delete/{book_id}.dr")
    public ModelAndView deletebook(@PathVariable String book_id, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/my_contents/books.dr");

    	commandMap.put("book_id", book_id);
    	myBooksService.deleteBook(commandMap.getMap());
    	
    	return mv;
    }
}
