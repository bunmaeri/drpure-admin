package admin.contents.service;

import java.util.List;
import java.util.Map;

public interface BooksService {
	/**
	 * 책 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> books(Map<String, Object> map) throws Exception;

	/**
	 * 책 상세
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> bookInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 책 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int isBook(Map<String, Object> map) throws Exception;
	
	/**
	 * 책 수정
	 * @param map
	 * @throws Exception
	 */
	void updateBook(Map<String, Object> map) throws Exception;
	
	/**
	 * MAX 책 번호
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	int maxBookId(Map<String, Object> map) throws Exception;
	
	/**
	 * 책 추가
	 * @param map
	 * @throws Exception
	 */
	void addBook(Map<String, Object> map) throws Exception;
	
	/**
	 * 책 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteBook(Map<String, Object> map) throws Exception;
	
	/**
	 * 이미지 수정
	 * @param map
	 * @throws Exception
	 */
	void updateImage(Map<String, Object> map) throws Exception;
}
