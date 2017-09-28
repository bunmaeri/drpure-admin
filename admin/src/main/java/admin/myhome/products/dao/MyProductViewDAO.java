package admin.myhome.products.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import admin.common.dao.AbstractDAO;

@Repository("myProductViewDAO")
public class MyProductViewDAO extends AbstractDAO {
	/**
	 * 제품 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> productInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("my_productView.productInfo", map);
	}
	
	/**
	 * 제품 Meta 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> productMeta(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("my_productView.productMeta", map);
	}
	
	/**
	 * 제품 Meta 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateProductMeta(Map<String, Object> map) throws Exception{
		update("my_productView.updateProductMeta", map);
	}
	
	/**
	 * 제품 Meta 추가
	 * @param map
	 * @throws Exception
	 */
	public void addProductMeta(Map<String, Object> map) throws Exception{
		insert("my_productView.addProductMeta", map);
	}
	
	/**
	 * 제품 Meta NEW
	 * @param map
	 * @throws Exception
	 */
	public void newProductMeta(Map<String, Object> map) throws Exception{
		insert("my_productView.newProductMeta", map);
	}
	
	/**
	 * MAX Model
	 * @return int
	 * @throws Exception
	 */
	public int maxModel() throws Exception{
		return (int) selectOne("my_productView.maxModel");
	}
	
	/**
	 * 제품 NEW
	 * @param map
	 * @throws Exception
	 */
	public void newProduct(Map<String, Object> map) throws Exception{
		insert("my_productView.newProduct", map);
	}
	
	/**
	 * 제품 수량 NEW
	 * @param map
	 * @throws Exception
	 */
	public void newProductQuantity(Map<String, Object> map) throws Exception{
		insert("my_productView.newProductQuantity", map);
	}
	
	/**
	 * 제품 기본 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> product(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("my_productView.product", map);
	}
	
	/**
	 * 제품 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateProduct(Map<String, Object> map) throws Exception{
		update("my_productView.updateProduct", map);
	}
	
	/**
	 * 제품 추가
	 * @param map
	 * @throws Exception
	 */
	public void addProduct(Map<String, Object> map) throws Exception{
		insert("my_productView.addProduct", map);
	}
	
	/**
	 * 제품 수량 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateProductQuantity(Map<String, Object> map) throws Exception{
		update("my_productView.updateProductQuantity", map);
	}
	
	/**
	 * 제품 수량 추가
	 * @param map
	 * @throws Exception
	 */
	public void addProductQuantity(Map<String, Object> map) throws Exception{
		insert("my_productView.addProductQuantity", map);
	}
	
	/**
	 * 모델 중복 체크
	 * @param String
	 * @return int
	 * @throws Exception
	 */
	public int duplicateModel(String model) throws Exception{
		return (int) selectOne("my_productView.duplicateModel", model);
	}
	
	/**
	 * 제품 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	public void updateImage(Map<String, Object> map) throws Exception{
		update("my_productView.updateImage", map);
	}
	
	/**
	 * 카테고리 목록과 체크된 카테고리
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> categoriesList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("my_productView.categoriesList", map);
	}
	
	/**
	 * 질병과 추천 생약제 목록(필수 & 선택)
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> diseaseList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("my_productView.diseaseList", map);
	}
	
	/**
	 * 카테고리별 제품 테이블을 삭제한다.
	 * @param map
	 * @throws Exception
	 */
	public void deleteProductCategory(Map<String, Object> map) throws Exception{
		delete("my_productView.deleteProductCategory", map);
	}
	
	/**
	 * 카테고리별 제품 테이블을 등록한다.
	 * @param map
	 * @throws Exception
	 */
	public void addProductCategory(Map<String, Object> map) throws Exception{
		insert("my_productView.addProductCategory", map);
	}
	
	/**
	 * 질병과 추천 생약제 선택된 카테고리(필수 & 선택)
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> diseaseSecurityContentsMedicine(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("my_productView.diseaseSecurityContentsMedicine", map);
	}
	
	/**
	 * 질병과 추천 생약제 테이블을 삭제한다.
	 * @param map
	 * @throws Exception
	 */
	public void deleteSecurityContentsMedicine(Map<String, Object> map) throws Exception{
		delete("my_productView.deleteSecurityContentsMedicine", map);
	}
	
	/**
	 * 질병과 추천 생약제 테이블을 등록한다.
	 * @param map
	 * @throws Exception
	 */
	public void addSecurityContentsMedicine(Map<String, Object> map) throws Exception{
		insert("my_productView.addSecurityContentsMedicine", map);
	}
	
	/**
	 * 가격 할인 목록
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> productSpecial(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("my_productView.productSpecial", map);
	}
	
	/**
	 * 가격 할인 등록
	 * @param map
	 * @throws Exception
	 */
	public void addProductSpecial(Map<String, Object> map) throws Exception{
		insert("my_productView.addProductSpecial", map);
	}
	
	/**
	 * 가격 할인 삭제
	 * @param map
	 * @throws Exception
	 */
	public void deleteProductSpecial(Map<String, Object> map) throws Exception{
		delete("my_productView.deleteProductSpecial", map);
	}
}
