package admin.customers.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("customersDAO")
public class CustomersDAO extends AbstractDAO {
	
	/**
	 * 고객 총 합계
	 * @param map
	 * @return int
	 * @throws Exception
	 */
	public int totalCustomers(Map<String, Object> map) throws Exception{
		return (int) selectOne("customers.totalCustomers", map);
	}
	
	/**
	 * 고객 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listCustomers(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("customers.listCustomers", map);
	}
	
	/**
	 * 고객 주소 목록 조회
	 * @param customer_id
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> addressList(String customer_id) throws Exception{
		return (List<Map<String, Object>>) selectList("customers.addressList", customer_id);
	}
	
	/**
	 * 고객 주소 조회
	 * @param address_id
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> address(String address_id) throws Exception{
		return (Map<String, Object>) selectOne("customers.address", address_id);
	}
	
	/**
	 * 비밀번호 저장 (고객목록에서 변경)
	 * @param map
	 * @throws Exception
	 */
	public void updatePasswordByUserid(Map<String, Object> map) throws Exception{
		update("customers.updatePasswordByUserid", map);
	}
	
	/**
	 * 고객 정보 조회
	 * @param customer_id
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> customerInfo(String customer_id) throws Exception{
		return (Map<String, Object>) selectOne("customers.customerInfo", customer_id);
	}
	
	/**
	 * 고객 기본정보 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateCustomerInfo(Map<String, Object> map) throws Exception{
		update("customers.updateCustomerInfo", map);
	}
	
	/**
	 * 이메일 중복 체크
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int duplicateEmail(String email) throws Exception{
		return (int) selectOne("customers.duplicateEmail", email);
	}

	/**
	 * 고객 포인트 총 갯수
	 * @param customer_id
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> totalCustomerReward(String customer_id) throws Exception{
		return (Map<String, Object>) selectOne("customers.totalCustomerReward", customer_id);
	}
	
	/**
	 * 고객 포인트 목록
	 * @param customer_id
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listCustomerReward(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("customers.listCustomerReward", map);
	}
	
	/**
	 * 고객 Reward 추가
	 * @param map
	 * @throws Exception
	 */
	public void addCustomerReward(Map<String, Object> map) throws Exception{
		insert("customers.addCustomerReward", map);
	}

	/**
	 * 고객 Reward 삭제
	 * @param customer_reward_id
	 * @throws Exception
	 */
	public void deleteCustomerReward(String customer_reward_id) throws Exception{
		delete("customers.deleteCustomerReward", customer_reward_id);
	}
	
	/**
	 * 고객 로그인 총 횟수
	 * @param customer_id
	 * @return int
	 * @throws Exception
	 */
	public int totalCustomerLoginhistory(String customer_id) throws Exception{
		return (int) selectOne("customers.totalCustomerLoginhistory", customer_id);
	}
	
	/**
	 * 고객 로그인 목록
	 * @param customer_id
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listCustomerLoginhistory(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectPagingList("customers.listCustomerLoginhistory", map);
	}
	
	/**
	 * 주소 정보 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateCustomerAddress(Map<String, Object> map) throws Exception{
		update("customers.updateCustomerAddress", map);
	}
	
	/**
	 * 주소 추가
	 * @param map
	 * @throws Exception
	 */
	public void addAddress(Map<String, Object> map) throws Exception{
		insert("customers.addAddress", map);
	}
	
	/**
	 * 고객 기본주소 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateCustomerInfoAddress(Map<String, Object> map) throws Exception{
		update("customers.updateCustomerInfoAddress", map);
	}
	
	/**
	 * 고객 주소 삭제
	 * @param customer_reward_id
	 * @throws Exception
	 */
	public void deleteCustomerAddress(String address_id) throws Exception{
		delete("customers.deleteCustomerAddress", address_id);
	}
	
	/**
	 * 고객 그룹 정보 조회
	 * @param customer_group_id
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> customerGroup(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("customers.customerGroup", map);
	}
	
	/**
	 * 고객 그룹 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateCustomerGroup(Map<String, Object> map) throws Exception{
		update("customers.updateCustomerGroup", map);
	}
	
	/**
	 * 고객 그룹 추가
	 * @param map
	 * @throws Exception
	 */
	public void addCustomerGroup(Map<String, Object> map) throws Exception{
		insert("customers.addCustomerGroup", map);
	}
}
