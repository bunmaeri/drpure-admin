package admin.contents.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.contents.dao.BooksDAO;


@Service("booksService")
public class BooksServiceImpl implements BooksService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="booksDAO")
	private BooksDAO booksDAO;
	
	/**
	 * 책 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> books(Map<String, Object> map) throws Exception {
		return booksDAO.books(map);
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
		Map<String, Object> tempMap = booksDAO.bookInfo(map);
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
		return booksDAO.isBook(map);
	}
	
	/**
	 * 책 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateBook(Map<String, Object> map) throws Exception {
		booksDAO.updateBook(map);
	}
	
	/**
	 * MAX 책 번호
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int maxBookId(Map<String, Object> map) throws Exception {
		return booksDAO.maxBookId(map);
	}
	
	/**
	 * 책 추가
	 */
	@Override
	public void addBook(Map<String, Object> map) throws Exception {
		booksDAO.addBook(map);
	}
	
	/**
	 * 책 삭제
	 */
	@Override
	public void deleteBook(Map<String, Object> map) throws Exception {
		booksDAO.deleteBook(map);
	}
	
	/**
	 * 이미지 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateImage(Map<String, Object> map) throws Exception {
		booksDAO.updateImage(map);
	}
}
