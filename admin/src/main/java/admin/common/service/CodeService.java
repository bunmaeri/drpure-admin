package admin.common.service;

import java.util.List;
import java.util.Map;

public interface CodeService {

	List<Map<String, Object>> getCodes() throws Exception;

	List<Map<String, Object>> getOrderStatus(String language_id) throws Exception;
	
	List<Map<String, Object>> getUserIps() throws Exception;
}
