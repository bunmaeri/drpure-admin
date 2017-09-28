package admin.myhome.products.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.myhome.products.dao.MyProductViewDAO;


@Service("myProductViewService")
public class MyProductViewServiceImpl implements MyProductViewService {
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myProductViewDAO")
	private MyProductViewDAO myProductViewDAO;
	
	/**
	 * 제품 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> productInfo(Map<String, Object> map) throws Exception {
		return myProductViewDAO.productInfo(map);
	}
	
	/**
	 * 제품 Meta 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> productMeta(Map<String, Object> map) throws Exception {
		return myProductViewDAO.productMeta(map);
	}
	
	/**
	 * 제품 Meta 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateProductMeta(Map<String, Object> map) throws Exception{
		myProductViewDAO.updateProductMeta(map);
	}
	
	/**
	 * 제품 Meta 추가
	 */
	@Override
	public void addProductMeta(Map<String, Object> map) throws Exception{
		myProductViewDAO.addProductMeta(map);
	}
	
	/**
	 * 제품 Meta NEW
	 */
	@Override
	public void newProductMeta(Map<String, Object> map) throws Exception{
		myProductViewDAO.newProductMeta(map);
	}
	
	/**
	 * MAX Model
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int maxModel() throws Exception {
		return myProductViewDAO.maxModel();
	}
	
	/**
	 * 제품 NEW
	 */
	@Override
	public void newProduct(Map<String, Object> map) throws Exception{
		myProductViewDAO.newProduct(map);
	}
	
	/**
	 * 제품 수량 NEW
	 */
	@Override
	public void newProductQuantity(Map<String, Object> map) throws Exception{
		myProductViewDAO.newProductQuantity(map);
	}

	/**
	 * 제품 기본 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> product(Map<String, Object> map) throws Exception {
		return myProductViewDAO.product(map);
	}
	
	/**
	 * 제품 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateProduct(Map<String, Object> map) throws Exception{
		myProductViewDAO.updateProduct(map);
	}
	
	/**
	 * 제품 추가
	 */
	@Override
	public void addProduct(Map<String, Object> map) throws Exception{
		myProductViewDAO.addProduct(map);
	}
	
	/**
	 * 제품 수량 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateProductQuantity(Map<String, Object> map) throws Exception{
		myProductViewDAO.updateProductQuantity(map);
	}
	
	/**
	 * 제품 수량 추가
	 */
	@Override
	public void addProductQuantity(Map<String, Object> map) throws Exception{
		myProductViewDAO.addProductQuantity(map);
	}

	/**
	 * 모델 중복 체크
	 * @param String
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int duplicateModel(String model) throws Exception {
		return myProductViewDAO.duplicateModel(model);
	}
	
	/**
	 * 제품 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateImage(Map<String, Object> map) throws Exception{
		myProductViewDAO.updateImage(map);
	}
	
	/**
	 * 카테고리 목록과 체크된 카테고리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> categoriesList(Map<String, Object> map) throws Exception {
		return myProductViewDAO.categoriesList(map);
	}
	
	/**
	 * 질병과 추천 생약제 목록(필수 & 선택)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> diseaseList(Map<String, Object> map) throws Exception {
		return myProductViewDAO.diseaseList(map);
	}
	
	/**
	 * 카테고리별 제품 테이블을 삭제한다.
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void deleteProductCategory(Map<String, Object> map) throws Exception{
		myProductViewDAO.deleteProductCategory(map);
	}
	
	/**
	 * 카테고리별 제품 테이블을 등록한다.
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void addProductCategory(Map<String, Object> map) throws Exception{
		myProductViewDAO.addProductCategory(map);
	}
	
	/**
	 * 질병과 추천 생약제 선택된 카테고리(필수 & 선택)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> diseaseSecurityContentsMedicine(Map<String, Object> map) throws Exception {
		return myProductViewDAO.diseaseSecurityContentsMedicine(map);
	}
	
	/**
	 * 질병과 추천 생약제 테이블을 삭제한다.
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void deleteSecurityContentsMedicine(Map<String, Object> map) throws Exception{
		myProductViewDAO.deleteSecurityContentsMedicine(map);
	}
	
	/**
	 * 질병과 추천 생약제 테이블을 등록한다.
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void addSecurityContentsMedicine(Map<String, Object> map) throws Exception{
		myProductViewDAO.addSecurityContentsMedicine(map);
	}
	
	/**
	 * 가격 할인
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> productSpecial(Map<String, Object> map) throws Exception {
		return myProductViewDAO.productSpecial(map);
	}
	
	/**
	 * 가격 할인 등록
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void addProductSpecial(Map<String, Object> map) throws Exception{
		myProductViewDAO.addProductSpecial(map);
	}
	
	/**
	 * 가격 할인 삭제
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void deleteProductSpecial(Map<String, Object> map) throws Exception{
		myProductViewDAO.deleteProductSpecial(map);
	}
}
