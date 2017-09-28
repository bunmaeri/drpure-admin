package admin.myhome.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.common.dao.CodeDAO;

@Service("myCodeService")
public class MyCodeServiceImpl implements MyCodeService{
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

}
