package admin.system.service;

import java.util.List;
import java.util.Map;

public interface OrderStatusService {
	/**
	 * 주문상태 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> orderStatusList(Map<String, Object> map) throws Exception;
	
	/**
	 * 주문상태 수정
	 * @param map
	 * @throws Exception
	 */
	void updateOrderStatus(Map<String, Object> map) throws Exception;
	
	/**
	 * 주문상태 추가
	 * @param map
	 * @throws Exception
	 */
	void addOrderStatus(Map<String, Object> map) throws Exception;
	
	/**
	 * 주문상태 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteOrderStatus(Map<String, Object> map) throws Exception;
}
