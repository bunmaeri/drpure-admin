package admin.sales.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.common.util.ObjectUtils;
import admin.common.util.OrderUtils;
import admin.common.util.StoreUtils;
import admin.sales.dao.OrdersDAO;

@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="ordersDAO")
	private OrdersDAO ordersDAO;
	
	/**
	 * Sales Order Total
	 * @param int
	 * @return
	 * @throws Exception
	 */
	@Override
	public int totalOrder(Map<String, Object> map) throws Exception {
		return ordersDAO.totalOrder(map);
	}
	
	/**
	 * Sales Order List
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> listOrder(Map<String, Object> map) throws Exception {
		return ordersDAO.listOrder(map);
	}
	
	/**
	 * 인보이스 상태의 주문 수량
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> invoiceOrderCnt(Map<String, Object> map) throws Exception {
		return ordersDAO.invoiceOrderCnt(map);
	}
	
	/**
	 * 인보이스 상태의 주문 수량(ICE)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> invoiceOrderCntIce(Map<String, Object> map) throws Exception {
		return ordersDAO.invoiceOrderCntIce(map);
	}
	
	/**
	 * 주문목록 조회 (인보이스 생성)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> listOrderForAction(Map<String, Object> map) throws Exception {
		return ordersDAO.listOrderForAction(map);
	}
	
	/**
	 * 주문상태 업데이트
	 */
	@Override
	public void updateOrderStatus(Map<String, Object> map) throws Exception{
		ordersDAO.updateOrderStatus(map);
	}
	
	/**
	 * 동일 주문번호로 등록된 Reward가 있는지 체크한다.
	 * @param int
	 * @return
	 * @throws Exception
	 */
	@Override
	public int countCustomerRewardForDuplication(Map<String, Object> map) throws Exception {
		return ordersDAO.countCustomerRewardForDuplication(map);
	}
	
	/**
	 * 고객 Reward 추가
	 */
	@Override
	public void addCustomerReward(Map<String, Object> map) throws Exception{
		ordersDAO.addCustomerReward(map);
	}
	
	/**
	 * 주문 History 추가
	 */
	@Override
	public void addOrderHistory(Map<String, Object> map) throws Exception{
		ordersDAO.addOrderHistory(map);
	}
	
	/**
	 * 제품 수량 업데이트
	 * 제품 수량 조회해서 0이면 재고 상태코드를 5(재고 없음)으로 업데이트
	 * 제품 수량이 0보다 작으면 재고 상태코드를 5(재고 없음)으로 업데이트하고 제품 수량을 0으로 셋팅
	 */
	@Override
	public void updateProductQuantity(Map<String, Object> map) throws Exception{
		ordersDAO.updateProductQuantity(map);
		
		int product_quantity = ordersDAO.selectProductQuantity(map);
		if(product_quantity==0) {
			map.put("stock_status_id", 5); // 재고 없음
			ordersDAO.updateProductStockStatus(map);
		} else
		if(product_quantity < 0) {
			map.put("stock_status_id", 5); // 재고 없음
			ordersDAO.updateProductStockStatus(map);
			
			// 수량이 0보다 작으면 0으로 맞춘다.
			ordersDAO.updateProductQuantityToZero(map);
		} else {
			map.put("stock_status_id", 7); // 재고 있음
			ordersDAO.updateProductStockStatus(map);
		}
	}
	
	/**
	 * 인보이스 번호 생성
	 */
	@Override
	public void createInvoiceNo(Map<String, Object> map) throws Exception{
		ordersDAO.createInvoiceNo(map);
	}
	
	/**
	 * 인보이스 출력
	 */
	@Override
	public List<Map<String, Object>> printInvoice(Map<String, Object> map) throws Exception {
		OrderUtils outil = new OrderUtils();
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list = ordersDAO.printInvoice(map);
		Map<String,Object> order = null;
		int size=list.size();
		Map<String,Object> map_check = new HashMap<String,Object>();
		Map<String,Object> product = null;
//		Map<String,Object> total = null;
		List<Map<String, Object>> productList = null;
		List<Map<String, Object>> totalList = null;
//		List<Map<String, Object>> orderTotalList = null;
//		String befOrderId = "";
//		String nowOrderId = "";
		int icebox = 0;

		Map<String,Object> tmp = null;
		String[] array = (String[]) map.get("array");
		int arr = array.length;
		int idx = 0;
		String bef_order_id = "";
		String order_id = "";
		String store_id = "";
		for(int i=0;i<arr;i++) {
			idx = 0;
			icebox = 0;
			tmp = new HashMap<String,Object>();
			order = new HashMap<String,Object>();
			productList = new ArrayList<Map<String,Object>>();
			totalList = new ArrayList<Map<String,Object>>();
//			orderTotalList = new ArrayList<Map<String,Object>>();
			
			product = new HashMap<String,Object>();
//			total = new HashMap<String,Object>();
			order_id = "";
			store_id = "";
			for(int y=0;y<size;y++) {
				tmp = list.get(y);
				bef_order_id = ObjectUtils.null2void(tmp.get("order_id"));
				if(array[i].equals(bef_order_id)) {
					idx++;
					// 주소 정보 저장
					if(idx==1) {
						tmp.put("payment_address", outil.orderHistoryAddress("payment_", tmp));
						tmp.put("shipping_address", outil.orderHistoryAddress("shipping_", tmp));
						order.put("info", tmp);
						
						order_id = ObjectUtils.null2void(tmp.get("order_id"));
						store_id = ObjectUtils.null2void(tmp.get("store_id"));
					}
					icebox += Integer.parseInt(ObjectUtils.null2Value(tmp.get("icebox"), "0"));
					product = setProduct(tmp);
					map_check.put(tmp.get("order_id")+":"+tmp.get("product_id"), "Y");
					productList.add(product);
				}
			}
			if(idx>0 && !order_id.equals("")) {
				map.put("order_id", order_id);
				map.put("table_prefix", StoreUtils.getTablePrefix(store_id));
				totalList = ordersDAO.orderTotals(map);
//				totalList.addAll(orderTotalList);
				
				order.put("products", productList);
				order.put("totals", totalList);
				order.put("icebox", icebox);
				if(store_id.equals("1")) {
					order.put("logo", "_myhome");
				} else {
					order.put("logo", "");
				}
				result.add(order);
			}
		}
		/*
		for(int i=0;i<size;i++) {
			tmp = list.get(i);
			nowOrderId = ObjectUtils.null2void(tmp.get("order_id"));
			if(!befOrderId.equals(nowOrderId)) {
				icebox = 0;
				order = new HashMap<String,Object>();
				productList = new ArrayList<Map<String,Object>>();
				totalList = new ArrayList<Map<String,Object>>();
				orderTotalList = new ArrayList<Map<String,Object>>();
				
				product = new HashMap<String,Object>();
				total = new HashMap<String,Object>();
				
				tmp.put("payment_address", outil.orderHistoryAddress("payment_", tmp));
				tmp.put("shipping_address", outil.orderHistoryAddress("shipping_", tmp));
				order.put("info", tmp);
				
				icebox += Integer.parseInt(ObjectUtils.null2Value(tmp.get("icebox"), "0"));
				product = setProduct(tmp);
				map_check.put(tmp.get("order_id")+":"+tmp.get("product_id"), "Y");
				productList.add(product);
				
//				total = setOrderTotal(tmp);
				map_check.put(tmp.get("order_id")+":"+tmp.get("order_total_id"), "Y");
				
				map.put("order_id", tmp.get("order_id"));
				map.put("table_prefix", StoreUtils.getTablePrefix(ObjectUtils.null2Value(tmp.get("store_id"),"0")));
				orderTotalList = ordersDAO.orderTotals(map);
				totalList.addAll(orderTotalList);
				
				//if(i!=0) { 
				order.put("products", productList);
				order.put("totals", totalList);
				order.put("icebox", icebox);
				result.add(order);
				
				icebox = 0;
				productList = new ArrayList<Map<String,Object>>();
				totalList = new ArrayList<Map<String,Object>>();
				orderTotalList = new ArrayList<Map<String,Object>>();
				
				product = new HashMap<String,Object>();
				total = new HashMap<String,Object>();
				//}
			} else {
//				System.err.println(map_check.containsKey(tmp.get("order_id")+":"+tmp.get("product_id")));
				if(!map_check.containsKey(tmp.get("order_id")+":"+tmp.get("product_id"))) {
					icebox += Integer.parseInt(ObjectUtils.null2Value(tmp.get("icebox"), "0"));
					product = setProduct(tmp);
					map_check.put(tmp.get("order_id")+":"+tmp.get("product_id"), "Y");
					productList.add(product);
				}
				if(!map_check.containsKey(tmp.get("order_id")+":"+tmp.get("order_total_id"))) {
//					total = setOrderTotal(tmp);
					map_check.put(tmp.get("order_id")+":"+tmp.get("order_total_id"), "Y");
//					totalList.add(total);
					map.put("order_id", tmp.get("order_id"));
					map.put("table_prefix", StoreUtils.getTablePrefix(ObjectUtils.null2Value(tmp.get("store_id"),"0")));
					orderTotalList = ordersDAO.orderTotals(map);
					totalList.addAll(orderTotalList);
				}
			}
			
			befOrderId = nowOrderId;
		}
		
		if(size>0) {
			if(!map_check.containsKey(tmp.get("order_id")+":"+tmp.get("product_id"))) {
				productList = new ArrayList<Map<String,Object>>();
				icebox += Integer.parseInt(ObjectUtils.null2Value(tmp.get("icebox"), "0"));
				product = setProduct(tmp);
				productList.add(product);
			}
			if(!map_check.containsKey(tmp.get("order_id")+":"+tmp.get("order_total_id"))) {
				totalList = new ArrayList<Map<String,Object>>();
				orderTotalList = new ArrayList<Map<String,Object>>();
//				total = setOrderTotal(tmp);
//				totalList.add(total);
				map.put("order_id", tmp.get("order_id"));
				map.put("table_prefix", StoreUtils.getTablePrefix(ObjectUtils.null2Value(tmp.get("store_id"),"0")));
				orderTotalList = ordersDAO.orderTotals(map);
				totalList.addAll(orderTotalList);
			}
			
			order.put("products", productList);
			order.put("totals", totalList);
			order.put("icebox", icebox);
			result.add(order);
		}
		*/
//	    System.err.println("size====================================>"+result.size());
		return result;
	}
	
	/**
	 * 인보이스 출력(ICE 여부)
	 */
	@Override
	public List<Map<String, Object>> printInvoiceByIce(Map<String, Object> map) throws Exception {
		OrderUtils outil = new OrderUtils();
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list = null;
		// 아이스 제품 여부에 따른 분류
		if(ObjectUtils.null2void(map.get("iceYn")).equals("not")) {
			list = ordersDAO.printInvoiceNotIce(map);
		} else {
			list = ordersDAO.printInvoiceIce(map);
		}
				
		Map<String,Object> order = null;
		int size=list.size();
		Map<String,Object> map_check = new HashMap<String,Object>();
		Map<String,Object> product = null;
		List<Map<String, Object>> productList = null;
		List<Map<String, Object>> totalList = null;
		int icebox = 0;

		Map<String,Object> tmp = null;
		String[] array = (String[]) map.get("array");
		int arr = array.length;
		int idx = 0;
		String bef_order_id = "";
		String order_id = "";
		String store_id = "";
		for(int i=0;i<arr;i++) {
			idx = 0;
			icebox = 0;
			tmp = new HashMap<String,Object>();
			order = new HashMap<String,Object>();
			productList = new ArrayList<Map<String,Object>>();
			totalList = new ArrayList<Map<String,Object>>();
//			orderTotalList = new ArrayList<Map<String,Object>>();
			
			product = new HashMap<String,Object>();
//			total = new HashMap<String,Object>();
			order_id = "";
			store_id = "";
			for(int y=0;y<size;y++) {
				tmp = list.get(y);
				bef_order_id = ObjectUtils.null2void(tmp.get("order_id"));
				if(array[i].equals(bef_order_id)) {
					idx++;
					// 주소 정보 저장
					if(idx==1) {
						tmp.put("payment_address", outil.orderHistoryAddress("payment_", tmp));
						tmp.put("shipping_address", outil.orderHistoryAddress("shipping_", tmp));
						order.put("info", tmp);
						
						order_id = ObjectUtils.null2void(tmp.get("order_id"));
						store_id = ObjectUtils.null2void(tmp.get("store_id"));
					}
					icebox += Integer.parseInt(ObjectUtils.null2Value(tmp.get("icebox"), "0"));
					product = setProduct(tmp);
					map_check.put(tmp.get("order_id")+":"+tmp.get("product_id"), "Y");
					productList.add(product);
				}
			}
			if(idx>0 && !order_id.equals("")) {
				map.put("order_id", order_id);
				map.put("table_prefix", StoreUtils.getTablePrefix(store_id));
				totalList = ordersDAO.orderTotals(map);
//				totalList.addAll(orderTotalList);
				
				order.put("products", productList);
				order.put("totals", totalList);
				order.put("icebox", icebox);
				if(store_id.equals("1")) {
					order.put("logo", "_myhome");
				} else {
					order.put("logo", "");
				}
				result.add(order);
			}
		}
		
		return result;
	}
	
	public Map<String,Object> printInvoiceData(List<Map<String,Object>> list) {
		Map<String,Object> map = new HashMap<String,Object>();
		int size = list.size();
		
		return map;
	}
	
	/**
	 * 인보이스 출력(1건)
	 */
	@Override
	public List<Map<String, Object>> printInvoiceOne(Map<String, Object> map) throws Exception {
		OrderUtils outil = new OrderUtils();
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list = ordersDAO.printInvoiceOne(map);
		Map<String,Object> order = new HashMap<String,Object>();
		
		Map<String,Object> map_check = new HashMap<String,Object>();
		Map<String,Object> product = new HashMap<String,Object>();

		List<Map<String, Object>> productList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> totalList = new ArrayList<Map<String,Object>>();

		int icebox = 0;

		Map<String,Object> tmp = new HashMap<String,Object>();
		String param_order_id = ObjectUtils.null2void(map.get("order_id"));
		int idx = 0;
		String bef_order_id = "";
		String order_id = "";
		String store_id = "";
		
		int size=list.size();
		for(int y=0;y<size;y++) {
			tmp = list.get(y);
			bef_order_id = ObjectUtils.null2void(tmp.get("order_id"));
			if(param_order_id.equals(bef_order_id)) {
				idx++;
				// 주소 정보 저장
				if(idx==1) {
					tmp.put("payment_address", outil.orderHistoryAddress("payment_", tmp));
					tmp.put("shipping_address", outil.orderHistoryAddress("shipping_", tmp));
					order.put("info", tmp);
					
					order_id = ObjectUtils.null2void(tmp.get("order_id"));
					store_id = ObjectUtils.null2void(tmp.get("store_id"));
				}
				icebox += Integer.parseInt(ObjectUtils.null2Value(tmp.get("icebox"), "0"));
				product = setProduct(tmp);
				map_check.put(tmp.get("order_id")+":"+tmp.get("product_id"), "Y");
				productList.add(product);
			}
		}
		if(idx>0 && !order_id.equals("")) {
			map.put("order_id", order_id);
			map.put("table_prefix", StoreUtils.getTablePrefix(store_id));
			totalList = ordersDAO.orderTotals(map);
//				totalList.addAll(orderTotalList);
			
			order.put("products", productList);
			order.put("totals", totalList);
			order.put("icebox", icebox);
			if(store_id.equals("1")) {
				order.put("logo", "_myhome");
			} else {
				order.put("logo", "");
			}
			result.add(order);
		}

//	    System.err.println("size====================================>"+result.size());
		return result;
	}
	
	/**
	 * 제품 정보를 담는다.
	 * @param tmp
	 * @return
	 */
	private Map<String,Object> setProduct(Map<String,Object> tmp) {
		Map<String,Object> product = new HashMap<String,Object>();
		product.put("product_id", tmp.get("product_id"));
		if(null!=tmp.get("jan") && !ObjectUtils.null2void(tmp.get("jan")).equals("")) {
			String[] jans = ObjectUtils.null2void(tmp.get("jan")).split(",");
			int size = jans.length;
//			System.err.println("name:"+tmp.get("jan"));
//			System.err.println("size:"+jans.length);
			if(size>1) {
				String product_name = ObjectUtils.null2void(tmp.get("name"));
				for(int i=0;i<size;i++) {
//					System.err.println("jans["+i+"]:"+jans[i]);
					product_name = product_name.replace(jans[i].trim(), "<span class=\"w_special\">"+jans[i].trim()+"</span>");
//					System.err.println(product_name);
				}
				product.put("name", product_name);
			} else {
				String name = "<span class=\"w_special\">"+ObjectUtils.null2void(tmp.get("jan"))+"</span>";
				product.put("name", ObjectUtils.null2void(tmp.get("name")).replace(ObjectUtils.null2void(tmp.get("jan")), name));
			}
		} else {
			product.put("name", tmp.get("name"));
		}
		product.put("ean", tmp.get("ean"));
		product.put("icebox", tmp.get("icebox"));
		product.put("model", tmp.get("model"));
		product.put("manufacturer_name", tmp.get("manufacturer_name"));
		product.put("price", tmp.get("price"));
		product.put("quantity", tmp.get("quantity"));
		product.put("total", tmp.get("total"));
		return product;
	}
	
	/**
	 * 주문 합계 정보를 담는다.
	 * @param tmp
	 * @return
	 */
	private Map<String,Object> setOrderTotal(Map<String,Object> tmp) {
		Map<String,Object> total = new HashMap<String,Object>();
		total.put("code", tmp.get("code"));
		total.put("title", tmp.get("title"));
		total.put("value", tmp.get("value"));
		return total;
	}
	
	/**
	 * 주문목록 조회 (GPS Tracking)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> listOrderForTracking(Map<String, Object> map) throws Exception {
		return ordersDAO.listOrderForTracking(map);
	}
	
	/**
	 * 주문상태변경(일괄적용)
	 */
	@Override
	public void updateOrdersStatusChange(Map<String, Object> map) throws Exception{
		ordersDAO.updateOrdersStatusChange(map);
	}
	
	/**
	 * Order View
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> orderView(Map<String, Object> map) throws Exception {
		return ordersDAO.orderView(map);
	}
	
	/**
	 * 주문한 제품을 목록 조회한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> orderProducts(Map<String, Object> map) throws Exception {
		return ordersDAO.orderProducts(map);
	}
	
	/**
	 * 주문 합계를 목록 조회한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> orderTotals(Map<String, Object> map) throws Exception {
		return ordersDAO.orderTotals(map);
	}
	
	/**
	 * 주문 이력을 목록 조회한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> orderHistories(Map<String, Object> map) throws Exception {
		return ordersDAO.orderHistories(map);
	}
	
	/**
	 * 배송정보 저장
	 */
	@Override
	public void updateOrderShippingInfo(Map<String, Object> map) throws Exception{
		ordersDAO.updateOrderShippingInfo(map);
	}
	
	/**
	 * 결제정보 저장
	 */
	@Override
	public void updateOrderPaymentInfo(Map<String, Object> map) throws Exception{
		ordersDAO.updateOrderPaymentInfo(map);
	}
	
	/**
	 * 주소정보 저장
	 */
	@Override
	public void updateAddress(Map<String, Object> map) throws Exception{
		ordersDAO.updateAddress(map);
	}
	
	/**
	 * TOP 검색
	 * @param order_id
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> searchOrder(String order_id) throws Exception {
		return ordersDAO.searchOrder(order_id);
	}
}