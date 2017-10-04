package admin.sales.service;

import java.util.List;
import java.util.Map;

public interface OrdersService {
	/**
	 * 주문 갯수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int totalOrder(Map<String, Object> map) throws Exception;
	
	/**
	 * 주문 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> listOrder(Map<String, Object> map) throws Exception;
	
	/**
	 * 인보이스 상태의 주문 수량
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> invoiceOrderCnt(Map<String, Object> map) throws Exception;
	
	/**
	 * 인보이스 상태의 주문 수량(ICE)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> invoiceOrderCntIce(Map<String, Object> map) throws Exception;
	
	/**
	 * 주문목록 조회 (인보이스 생성)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> listOrderForAction(Map<String, Object> map) throws Exception;
	
	/**
	 * 주문상태 업데이트
	 * @param map
	 * @throws Exception
	 */
	void updateOrderStatus(Map<String, Object> map) throws Exception;
	
	/**
	 * 동일 주문번호로 등록된 Reward가 있는지 체크한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int countCustomerRewardForDuplication(Map<String, Object> map) throws Exception;
	
	/**
	 * 고객 Reward 추가
	 * @param map
	 * @throws Exception
	 */
	void addCustomerReward(Map<String, Object> map) throws Exception;
	
	/**
	 * 고객 Reward 삭제(주문 취소)
	 * @param map
	 * @throws Exception
	 */
	void deleteCustomerReward(Map<String, Object> map) throws Exception;
	
	/**
	 * 주문 History 추가
	 * @param map
	 * @throws Exception
	 */
	void addOrderHistory(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 수량 업데이트
	 * @param map
	 * @throws Exception
	 */
	void updateProductQuantity(Map<String, Object> map) throws Exception;
	
	/**
	 * 인보이스 번호 생성
	 * @param map
	 * @throws Exception
	 */
	void createInvoiceNo(Map<String, Object> map) throws Exception;
	
	/**
	 * 인보이스 출력
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> printInvoice(Map<String, Object> map) throws Exception;
	
	/**
	 * 인보이스 출력(ICE 여부)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> printInvoiceByIce(Map<String, Object> map) throws Exception;
	
	/**
	 * 인보이스 출력(1건)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> printInvoiceOne(Map<String, Object> map) throws Exception;
	
	/**
	 * 주문목록 조회 (GPS Tracking)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> listOrderForTracking(Map<String, Object> map) throws Exception;
	
	/**
	 * 주문상태변경(일괄적용)
	 * @param map
	 * @throws Exception
	 */
	void updateOrdersStatusChange(Map<String, Object> map) throws Exception;
	
	/**
	 * Order View
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> orderView(Map<String, Object> map) throws Exception;
	
	/**
	 * 주문한 제품을 목록 조회한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> orderProducts(Map<String, Object> map) throws Exception;
	
	/**
	 * 주문 합계를 목록 조회한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> orderTotals(Map<String, Object> map) throws Exception;
	
	/**
	 * 주문 이력을 목록 조회한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> orderHistories(Map<String, Object> map) throws Exception;
	
	/**
	 * 배송정보 저장
	 * @param map
	 * @throws Exception
	 */
	void updateOrderShippingInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 결제정보 저장
	 * @param map
	 * @throws Exception
	 */
	void updateOrderPaymentInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 주소정보 저장
	 * @param map
	 * @throws Exception
	 */
	void updateAddress(Map<String, Object> map) throws Exception;
	
	/**
	 * TOP 검색
	 * @param order_id
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> searchOrder(String order_id) throws Exception;
}
