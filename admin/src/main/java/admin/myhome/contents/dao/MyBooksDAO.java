package admin.myhome.contents.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("myBooksDAO")
public class MyBooksDAO extends AbstractDAO {
	/**
	 * 책 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> books(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("my_books.books", map);
	}

	/**
	 * 책 상세
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> bookInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("my_books.bookInfo", map);
	}
	
	/**
	 * 책 데이터가 있는지 체크
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int isBook(Map<String, Object> map) throws Exception{
		return (int) selectOne("my_books.isBook", map);
	}
	
	/**
	 * 책 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateBook(Map<String, Object> map) throws Exception{
		update("my_books.updateBook", map);
	}
	
	/**
	 * MAX 책번호
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int maxBookId(Map<String, Object> map) throws Exception{
		return (int) selectOne("my_books.maxBookId", map);
	}
	
	/**
	 * 책 추가
	 * @param map
	 * @throws Exception
	 */
	public void addBook(Map<String, Object> map) throws Exception{
		insert("my_books.addBook", map);
	}
	
	/**
	 * 이미지 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateImage(Map<String, Object> map) throws Exception{
		update("my_books.updateImage", map);
	}
	
	/**
	 * 책 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteBook(Map<String, Object> map) throws Exception{
		delete("my_books.deleteBook", map);
	}
}
