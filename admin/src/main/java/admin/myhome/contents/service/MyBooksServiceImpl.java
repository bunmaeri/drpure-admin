package admin.myhome.contents.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.myhome.contents.dao.MyBooksDAO;


@Service("myBooksService")
public class MyBooksServiceImpl implements MyBooksService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myBooksDAO")
	private MyBooksDAO myBooksDAO;
	
	/**
	 * 책 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> books(Map<String, Object> map) throws Exception {
		return myBooksDAO.books(map);
	}
	
	/**
	 * 책 상세
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> bookInfo(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = myBooksDAO.bookInfo(map);
		resultMap.put("map", tempMap);
		
		return resultMap;
	}
	
	/**
	 * 책 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int isBook(Map<String, Object> map) throws Exception {
		return myBooksDAO.isBook(map);
	}
	
	/**
	 * 책 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateBook(Map<String, Object> map) throws Exception {
		myBooksDAO.updateBook(map);
	}
	
	/**
	 * MAX 책 번호
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int maxBookId(Map<String, Object> map) throws Exception {
		return myBooksDAO.maxBookId(map);
	}
	
	/**
	 * 책 추가
	 */
	@Override
	public void addBook(Map<String, Object> map) throws Exception {
		myBooksDAO.addBook(map);
	}
	
	/**
	 * 책 삭제
	 */
	@Override
	public void deleteBook(Map<String, Object> map) throws Exception {
		myBooksDAO.deleteBook(map);
	}
	
	/**
	 * 이미지 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateImage(Map<String, Object> map) throws Exception {
		myBooksDAO.updateImage(map);
	}
}
