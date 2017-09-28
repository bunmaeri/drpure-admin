package admin.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("orderStatusDAO")
public class OrderStatusDAO extends AbstractDAO {

	/**
	 * 주문상태 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> orderStatusList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("system_orderstatus.orderStatusList", map);
	}
	
	/**
	 * 주문상태 수정
	 * @param map
	 * @throws Exception
	 */
	public void updateOrderStatus(Map<String, Object> map) throws Exception{
		update("system_orderstatus.updateOrderStatus", map);
	}
	
	/**
	 * 주문상태 추가
	 * @param map
	 * @throws Exception
	 */
	public void addOrderStatus(Map<String, Object> map) throws Exception{
		insert("system_orderstatus.addOrderStatus", map);
	}
	
	/**
	 * 주문상태 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteOrderStatus(Map<String, Object> map) throws Exception{
		delete("system_orderstatus.deleteOrderStatus", map);
	}
}
