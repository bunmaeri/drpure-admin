package admin.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.system.dao.LanguagesDAO;
import admin.system.dao.OrderStatusDAO;

@Service("orderStatusService")
public class OrderStatusServiceImpl implements OrderStatusService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="orderStatusDAO")
	private OrderStatusDAO orderStatusDAO;
	
	/**
	 * 주문상태 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> orderStatusList(Map<String, Object> map) throws Exception {
		return orderStatusDAO.orderStatusList(map);
	}
	
	/**
	 * 주문상태 수정
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@Override
	public void updateOrderStatus(Map<String, Object> map) throws Exception {
		orderStatusDAO.updateOrderStatus(map);
	}
	
	/**
	 * 주문상태 추가
	 */
	@Override
	public void addOrderStatus(Map<String, Object> map) throws Exception {
		orderStatusDAO.addOrderStatus(map);
	}
	
	/**
	 * 주문상태 삭제
	 */
	@Override
	public void deleteOrderStatus(Map<String, Object> map) throws Exception {
		orderStatusDAO.deleteOrderStatus(map);
	}
}
