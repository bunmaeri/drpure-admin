package admin.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("excelDAO")
public class ExcelDAO extends AbstractDAO{

	/**
	 * Export Orders 데이터를 조회한다.
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getExportOrders(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("excel.getExportOrders", map);
	}
}
