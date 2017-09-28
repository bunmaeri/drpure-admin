package admin.customers.service;

import java.util.List;
import java.util.Map;

public interface CustomersService {
	/**
	 * 고객 총 합계
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int totalCustomers(Map<String, Object> map) throws Exception;
	
	/**
	 * 고객 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> listCustomers(Map<String, Object> map) throws Exception;
	
	/**
	 * 고객 주소 목록 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	List<Map<String, Object>> addressList(String customer_id) throws Exception;
	
	/**
	 * 고객 주소 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	Map<String, Object> address(String address_id) throws Exception;
	
	/**
	 * 비밀번호 저장 (고객목록에서 변경)
	 * @param map
	 * @throws Exception
	 */
	void updatePasswordByUserid(Map<String, Object> map) throws Exception;
	
	/**
	 * 고객 정보 조회
	 * @param customer_id
	 * @return list
	 * @throws Exception
	 */
	Map<String, Object> customerInfo(String customer_id) throws Exception;
	
	/**
	 * 고객 기본정보 저장
	 * @param map
	 * @throws Exception
	 */
	void updateCustomerInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 이메일 중복 체크
	 * @param email
	 * @return
	 * @throws Exception
	 */
	int duplicateEmail(String email) throws Exception;
	
	/**
	 * 고객 포인트 총 갯수
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> totalCustomerReward(String customer_id) throws Exception;
	
	/**
	 * 고객 포인트 목록
	 * @param customer_id
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> listCustomerReward(Map<String, Object> map) throws Exception;
	
	/**
	 * 고객 Reward 추가
	 * @param map
	 * @throws Exception
	 */
	void addCustomerReward(Map<String, Object> map) throws Exception;
	
	/**
	 * 고객 Reward 삭제
	 * @param customer_reward_id
	 * @throws Exception
	 */
	void deleteCustomerReward(String customer_reward_id) throws Exception;
	
	/**
	 * 고객 로그인 총 횟수
	 * @param customer_id
	 * @return
	 * @throws Exception
	 */
	int totalCustomerLoginhistory(String customer_id) throws Exception;
	
	/**
	 * 고객 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> listCustomerLoginhistory(Map<String, Object> map) throws Exception;
	
	/**
	 * 주소 정보 저장
	 * @param map
	 * @throws Exception
	 */
	void updateCustomerAddress(Map<String, Object> map) throws Exception;
	
	/**
	 * 주소 추가
	 * @param map
	 * @return
	 * @throws Exception
	 */
	void addAddress(Map<String, Object> map) throws Exception;
	
	/**
	 * 고객 기본주소 저장
	 * @param map
	 * @throws Exception
	 */
	void updateCustomerInfoAddress(Map<String, Object> map) throws Exception;
	
	/**
	 * 고객 주소 삭제
	 * @param address_id
	 * @throws Exception
	 */
	void deleteCustomerAddress(String address_id) throws Exception;
	
	/**
	 * 고객 그룹 정보 조회
	 * @param customer_group_id
	 * @return list
	 * @throws Exception
	 */
	Map<String, Object> customerGroup(Map<String, Object> map) throws Exception;
	
	/**
	 * 고객 그룹 저장
	 * @param map
	 * @throws Exception
	 */
	void updateCustomerGroup(Map<String, Object> map) throws Exception;
	
	/**
	 * 고객 그룹 추가
	 * @param map
	 * @return
	 * @throws Exception
	 */
	void addCustomerGroup(Map<String, Object> map) throws Exception;
}
