package admin.sales.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;
import admin.common.util.ObjectUtils;

@Repository("ordersDAO")
public class OrdersDAO extends AbstractDAO {
	/**
	 * Sales Order Total
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalOrder(Map<String, Object> map) throws Exception{
		return (int) selectOne("salesOrders.totalOrder", map);
	}
	
	/**
	 * Sales Order List
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listOrder(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("salesOrders.listOrder", map);
	}
	
	/**
	 * 인보이스 상태의 주문 수량
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> invoiceOrderCnt(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.invoiceOrderCnt", map);
	}
	
	/**
	 * 인보이스 상태의 주문 수량(ICE)
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> invoiceOrderCntIce(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.invoiceOrderCntIce", map);
	}
	
	/**
	 * 주문목록 조회 (인보이스 생성)
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listOrderForAction(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.listOrderForAction", map);
	}
	
	/**
	 * 주문상태 업데이트
	 * @param map
	 * @throws Exception
	 */
	public void updateOrderStatus(Map<String, Object> map) throws Exception{
	    update("salesOrders.updateOrderStatus", map);
	}
	
	/**
	 * 동일 주문번호로 등록된 Reward가 있는지 체크한다.
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int countCustomerRewardForDuplication(Map<String, Object> map) throws Exception{
		return (int) selectOne("salesOrders.countCustomerRewardForDuplication", map);
	}
	
	/**
	 * 고객 Reward 추가
	 * @param map
	 * @throws Exception
	 */
	public void addCustomerReward(Map<String, Object> map) throws Exception{
		insert("salesOrders.addCustomerReward", map);
	}
	
	/**
	 * 고객 Reward 삭제(주문 취소)
	 * @param map
	 * @throws Exception
	 */
	public void deleteCustomerReward(Map<String, Object> map) throws Exception{
		delete("salesOrders.deleteCustomerReward", map);
	}
	
	/**
	 * 주문 History 추가
	 * @param map
	 * @throws Exception
	 */
	public void addOrderHistory(Map<String, Object> map) throws Exception{
		insert("salesOrders.addOrderHistory", map);
	}
	
	/**
	 * 제품 수량 업데이트
	 * @param map
	 * @throws Exception
	 */
	public void updateProductQuantity(Map<String, Object> map) throws Exception{
		if(ObjectUtils.null2void(map.get("plusOrMinus")).equals("+")) {
			update("salesOrders.updateProductQuantityPlus", map);
		} else {
			update("salesOrders.updateProductQuantityMinus", map);
		}
	}
	
	/**
	 * 제품 수량을 다시 조회한다.
	 * @param customer_id
	 * @return
	 * @throws Exception
	 */
	public int selectProductQuantity(Map<String, Object> map) throws Exception{
		return (int) selectOne("salesOrders.selectProductQuantity", map);
	}
	
	/**
	 * 재고 상태 코드를 업데이트한다.
	 * @param map
	 * @throws Exception
	 */
	public void updateProductStockStatus(Map<String, Object> map) throws Exception{
	    update("salesOrders.updateProductStockStatus", map);
	}
	
	/**
	 * 제품 수량 0으로 업데이트
	 * @param map
	 * @throws Exception
	 */
	public void updateProductQuantityToZero(Map<String, Object> map) throws Exception{
	    update("salesOrders.updateProductQuantityToZero", map);
	}
	
	/**
	 * 인보이스 번호 생성
	 * @param map
	 * @throws Exception
	 */
	public void createInvoiceNo(Map<String, Object> map) throws Exception{
	    update("salesOrders.createInvoiceNo", map);
	}

	/**
	 * 인보이스 출력
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> printInvoice(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.printInvoice", map);
	}
	
	/**
	 * 인보이스 출력(NOT ICE)
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> printInvoiceNotIce(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.printInvoiceNotIce", map);
	}
	
	/**
	 * 인보이스 출력(ICE)
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> printInvoiceIce(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.printInvoiceIce", map);
	}
	
	/**
	 * 인보이스 출력(1건)
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> printInvoiceOne(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.printInvoiceOne", map);
	}
	
	/**
	 * GPS Tracking 대상 주문 데이터를 조회한다.
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listOrderForTracking(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.listOrderForTracking", map);
	}
	
	/**
	 * 주문상태변경(일괄적용)
	 * @param map
	 * @throws Exception
	 */
	public void updateOrdersStatusChange(Map<String, Object> map) throws Exception{
	    update("salesOrders.updateOrdersStatusChange", map);
	}
	
	/**
	 * Order View
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> orderView(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("salesOrders.orderView", map);
	}
	
	/**
	 * 적립포인트 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> customerRewardByOrder(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.customerRewardByOrder", map);
	}
	
	/**
	 * 주문한 제품을 목록 조회한다.
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> orderProducts(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.orderProducts", map);
	}
	
	/**
	 * 주문 합계를 목록 조회한다.
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> orderTotals(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.orderTotals", map);
	}
	
	/**
	 * 주문 이력을 목록 조회한다.
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> orderHistories(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.orderHistories", map);
	}
	
	/**
	 * 결제정보 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateOrderPaymentInfo(Map<String, Object> map) throws Exception{
	    update("salesOrders.updateOrderPaymentInfo", map);
	}
	
	/**
	 * 배송정보 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateOrderShippingInfo(Map<String, Object> map) throws Exception{
	    update("salesOrders.updateOrderShippingInfo", map);
	}
	
	/**
	 * 주소정보 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateAddress(Map<String, Object> map) throws Exception{
	    update("salesOrders.updateAddress", map);
	}
	
	/**
	 * TOP 검색
	 * @param order_id
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> searchOrder(String order_id) throws Exception{
		return (List<Map<String, Object>>) selectList("salesOrders.searchOrder", order_id);
	}
	
	/**
	 * 주문 합계 금액
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> orderTotalSum(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("salesOrders.orderTotalSum", map);
	}
	
	/**
	 * 마이홈닥고객 업데이트
	 * @param map
	 * @throws Exception
	 */
	public void updateToMyhomedoc(Map<String, Object> map) throws Exception{
	    update("salesOrders.updateToMyhomedoc", map);
	}
}
