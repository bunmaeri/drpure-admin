package admin.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("codeDAO")
public class CodeDAO extends AbstractDAO{

	/**
	 * 공통코드를 메모리에 로드한다.
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCodes() throws Exception{
		return (List<Map<String, Object>>)selectList("code.getCodes");
	}
	
	/**
	 * 주문상태코드를 메모리에 로드한다.
	 * @param language_id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOrderStatus(String language_id) throws Exception{
		return (List<Map<String, Object>>)selectList("code.getOrderStatus", language_id);
	}
	
	/**
	 * 접근 가능한 아이피를 메모리에 로드한다.
	 * @param language_id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getUserIps() throws Exception{
		return (List<Map<String, Object>>)selectList("code.getUserIps");
	}
}

