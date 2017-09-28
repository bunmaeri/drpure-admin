package admin.common.service;

import java.util.List;
import java.util.Map;

public interface ExcelService {
	List<Map<String,Object>> getExportOrders(Map<String, Object> map) throws Exception;
}
