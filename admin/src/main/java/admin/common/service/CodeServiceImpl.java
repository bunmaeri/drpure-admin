package admin.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.common.dao.CodeDAO;

@Service("codeService")
public class CodeServiceImpl implements CodeService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="codeDAO")
	private CodeDAO codeDAO;

	/**
	 * 공통코드를 메모리에 로드한다.
	 */
	@Override
	public List<Map<String, Object>> getCodes() throws Exception {
		return codeDAO.getCodes();
	}
	
	/**
	 * 주문상태코드를 메모리에 로드한다.
	 */
	@Override
	public List<Map<String, Object>> getOrderStatus(String language_id) throws Exception {
		return codeDAO.getOrderStatus(language_id);
	}
	
	/**
	 * 접근 가능한 아이피를 메모리에 로드한다.
	 */
	@Override
	public List<Map<String, Object>> getUserIps() throws Exception {
		return codeDAO.getUserIps();
	}
}
