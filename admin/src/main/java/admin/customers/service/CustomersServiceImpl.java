package admin.customers.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.customers.dao.CustomersDAO;


@Service("customersService")
public class CustomersServiceImpl implements CustomersService {
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="customersDAO")
	private CustomersDAO customersDAO;
	
	/**
	 * 고객 총 합계
	 * @param int
	 * @return
	 * @throws Exception
	 */
	@Override
	public int totalCustomers(Map<String, Object> map) throws Exception {
		return customersDAO.totalCustomers(map);
	}
	
	/**
	 * 고객 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> listCustomers(Map<String, Object> map) throws Exception {
		return customersDAO.listCustomers(map);
	}
	
	/**
	 * 고객 주소 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> addressList(String customer_id) throws Exception {
		return customersDAO.addressList(customer_id);
	}

	/**
	 * 고객 주소 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> address(String address_id) throws Exception {
		return customersDAO.address(address_id);
	}
	
	/**
	 * 비밀번호 저장 (고객목록에서 변경)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updatePasswordByUserid(Map<String, Object> map) throws Exception{
		customersDAO.updatePasswordByUserid(map);
	}
	
	/**
	 * 고객 정보 조회
	 * @param customer_id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> customerInfo(String customer_id) throws Exception {
		return customersDAO.customerInfo(customer_id);
	}
	
	/**
	 * 고객 기본정보 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateCustomerInfo(Map<String, Object> map) throws Exception{
		customersDAO.updateCustomerInfo(map);
	}
	
	/**
	 * 이메일 중복 체크
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@Override
	public int duplicateEmail(String email) throws Exception {
		return customersDAO.duplicateEmail(email);
	}
	
	/**
	 * 고객 포인트 총 갯수
	 * @param customer_id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> totalCustomerReward(String customer_id) throws Exception {
		return customersDAO.totalCustomerReward(customer_id);
	}
	
	/**
	 * 고객 포인트 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> listCustomerReward(Map<String, Object> map) throws Exception {
		return customersDAO.listCustomerReward(map);
	}
	
	/**
	 * 고객 Reward 추가
	 */
	@Override
	public void addCustomerReward(Map<String, Object> map) throws Exception{
		customersDAO.addCustomerReward(map);
	}
	
	/**
	 * 고객 Reward 삭제
	 * @param customer_reward_id
	 */
	@Override
	public void deleteCustomerReward(String customer_reward_id) throws Exception {
		customersDAO.deleteCustomerReward(customer_reward_id);
	}
	
	/**
	 * 고객 로그인 총 횟수
	 * @param customer_id
	 * @return
	 * @throws Exception
	 */
	@Override
	public int totalCustomerLoginhistory(String customer_id) throws Exception {
		return customersDAO.totalCustomerLoginhistory(customer_id);
	}
	
	/**
	 * 고객 목록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> listCustomerLoginhistory(Map<String, Object> map) throws Exception {
		return customersDAO.listCustomerLoginhistory(map);
	}
	
	/**
	 * 주소 정보 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateCustomerAddress(Map<String, Object> map) throws Exception{
		customersDAO.updateCustomerAddress(map);
	}
	
	/**
	 * 주소 추가
	 */
	@Override
	public void addAddress(Map<String, Object> map) throws Exception {
		customersDAO.addAddress(map);
	}
	
	/**
	 * 고객 기본주소 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateCustomerInfoAddress(Map<String, Object> map) throws Exception{
		customersDAO.updateCustomerInfoAddress(map);
	}
	
	/**
	 * 고객 주소 삭제
	 * @param address_id
	 */
	@Override
	public void deleteCustomerAddress(String address_id) throws Exception {
		customersDAO.deleteCustomerAddress(address_id);
	}
	
	/**
	 * 고객 그룹 정보 조회
	 * @param customer_group_id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> customerGroup(Map<String, Object> map) throws Exception {
		return customersDAO.customerGroup(map);
	}
	
	/**
	 * 고객 그룹 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateCustomerGroup(Map<String, Object> map) throws Exception{
		customersDAO.updateCustomerGroup(map);
	}
	
	/**
	 * 고객 그룹 추가
	 */
	@Override
	public void addCustomerGroup(Map<String, Object> map) throws Exception {
		customersDAO.addCustomerGroup(map);
	}
}
