package admin.products.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import admin.products.dao.ProductViewDAO;


@Service("productViewService")
public class ProductViewServiceImpl implements ProductViewService {
Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="productViewDAO")
	private ProductViewDAO productViewDAO;
	
	/**
	 * 제품 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> productInfo(Map<String, Object> map) throws Exception {
		return productViewDAO.productInfo(map);
	}
	
	/**
	 * 제품 Meta 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> productMeta(Map<String, Object> map) throws Exception {
		return productViewDAO.productMeta(map);
	}
	
	/**
	 * 제품 Meta 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateProductMeta(Map<String, Object> map) throws Exception{
		productViewDAO.updateProductMeta(map);
	}
	
	/**
	 * 제품 Meta 추가
	 */
	@Override
	public void addProductMeta(Map<String, Object> map) throws Exception{
		productViewDAO.addProductMeta(map);
	}
	
	/**
	 * 제품 Meta NEW
	 */
	@Override
	public void newProductMeta(Map<String, Object> map) throws Exception{
		productViewDAO.newProductMeta(map);
	}
	
	/**
	 * MAX Model
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int maxModel() throws Exception {
		return productViewDAO.maxModel();
	}
	
	/**
	 * 제품 NEW
	 */
	@Override
	public void newProduct(Map<String, Object> map) throws Exception{
		productViewDAO.newProduct(map);
	}
	
	/**
	 * 제품 수량 NEW
	 */
	@Override
	public void newProductQuantity(Map<String, Object> map) throws Exception{
		productViewDAO.newProductQuantity(map);
	}

	/**
	 * 제품 기본 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> product(Map<String, Object> map) throws Exception {
		return productViewDAO.product(map);
	}
	
	/**
	 * 제품 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateProduct(Map<String, Object> map) throws Exception{
		productViewDAO.updateProduct(map);
	}
	
	/**
	 * 제품 추가
	 */
	@Override
	public void addProduct(Map<String, Object> map) throws Exception{
		productViewDAO.addProduct(map);
	}
	
	/**
	 * 제품 수량 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateProductQuantity(Map<String, Object> map) throws Exception{
		productViewDAO.updateProductQuantity(map);
	}
	
	/**
	 * 재고 상태 코드 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateProductStockStatus(Map<String, Object> map) throws Exception{
		productViewDAO.updateProductStockStatus(map);
	}
	
	/**
	 * 제품 수량 추가
	 */
	@Override
	public void addProductQuantity(Map<String, Object> map) throws Exception{
		productViewDAO.addProductQuantity(map);
	}

	/**
	 * 모델 중복 체크
	 * @param String
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int duplicateModel(String model) throws Exception {
		return productViewDAO.duplicateModel(model);
	}
	
	/**
	 * 제품 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateImage(Map<String, Object> map) throws Exception{
		productViewDAO.updateImage(map);
	}
	
	/**
	 * 카테고리 목록과 체크된 카테고리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> categoriesList(Map<String, Object> map) throws Exception {
		return productViewDAO.categoriesList(map);
	}
	
	/**
	 * 질병과 추천 생약제 목록(필수 & 선택)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> diseaseList(Map<String, Object> map) throws Exception {
		return productViewDAO.diseaseList(map);
	}
	
	/**
	 * 카테고리별 제품 테이블을 삭제한다.
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void deleteProductCategory(Map<String, Object> map) throws Exception{
		productViewDAO.deleteProductCategory(map);
	}
	
	/**
	 * 카테고리별 제품 테이블을 등록한다.
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void addProductCategory(Map<String, Object> map) throws Exception{
		productViewDAO.addProductCategory(map);
	}
	
	/**
	 * 질병과 추천 생약제 선택된 카테고리(필수 & 선택)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> diseaseSecurityContentsMedicine(Map<String, Object> map) throws Exception {
		return productViewDAO.diseaseSecurityContentsMedicine(map);
	}
	
	/**
	 * 질병과 추천 생약제 테이블을 삭제한다.
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void deleteSecurityContentsMedicine(Map<String, Object> map) throws Exception{
		productViewDAO.deleteSecurityContentsMedicine(map);
	}
	
	/**
	 * 질병과 추천 생약제 테이블을 등록한다.
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void addSecurityContentsMedicine(Map<String, Object> map) throws Exception{
		productViewDAO.addSecurityContentsMedicine(map);
	}
	
	/**
	 * 가격 할인
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> productSpecial(Map<String, Object> map) throws Exception {
		return productViewDAO.productSpecial(map);
	}
	
	/**
	 * 가격 할인 등록
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void addProductSpecial(Map<String, Object> map) throws Exception{
		productViewDAO.addProductSpecial(map);
	}
	
	/**
	 * 가격 할인 삭제
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void deleteProductSpecial(Map<String, Object> map) throws Exception{
		productViewDAO.deleteProductSpecial(map);
	}
	
	/**
	 * 제품 삭제
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void removeProduct(Map<String, Object> map) throws Exception{
		productViewDAO.removeProduct(map);
	}
}
