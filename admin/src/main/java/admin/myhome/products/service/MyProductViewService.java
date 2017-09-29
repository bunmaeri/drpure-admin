package admin.myhome.products.service;

import java.util.List;
import java.util.Map;

public interface MyProductViewService {
	/**
	 * 제품 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	Map<String, Object> productInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 Meta 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	Map<String, Object> productMeta(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 Meta 저장
	 * @param map
	 * @throws Exception
	 */
	void updateProductMeta(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 Meta 추가
	 * @param map
	 * @throws Exception
	 */
	void addProductMeta(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 Meta NEW
	 * @param map
	 * @throws Exception
	 */
	void newProductMeta(Map<String, Object> map) throws Exception;
	
	/**
	 * MAX Model
	 * @return int
	 * @throws Exception
	 */
	int maxModel() throws Exception;
	
	/**
	 * 제품 NEW
	 * @param map
	 * @throws Exception
	 */
	void newProduct(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 수량 NEW
	 * @param map
	 * @throws Exception
	 */
	void newProductQuantity(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 기본 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> product(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 저장
	 * @param map
	 * @throws Exception
	 */
	void updateProduct(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 추가
	 * @param map
	 * @throws Exception
	 */
	void addProduct(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 수량 저장
	 * @param map
	 * @throws Exception
	 */
	void updateProductQuantity(Map<String, Object> map) throws Exception;
	
	/**
	 * 재고 상태 코드 저장
	 * @param map
	 * @throws Exception
	 */
	void updateProductStockStatus(Map<String, Object> map) throws Exception;
	
	/**
	 * 제품 수량 추가
	 * @param map
	 * @throws Exception
	 */
	void addProductQuantity(Map<String, Object> map) throws Exception;
	
	/**
	 * 모델 중복 체크
	 * @param String
	 * @return int
	 * @throws Exception
	 */
	int duplicateModel(String model) throws Exception;
	
	/**
	 * 제품 이미지 저장
	 * @param map
	 * @throws Exception
	 */
	void updateImage(Map<String, Object> map) throws Exception;
	
	/**
	 * 카테고리 목록과 체크된 카테고리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> categoriesList(Map<String, Object> map) throws Exception;
	
	/**
	 * 질병과 추천 생약제 목록(필수 & 선택)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> diseaseList(Map<String, Object> map) throws Exception;
	
	/**
	 * 카테고리별 제품 테이블을 삭제한다.
	 * @param map
	 * @throws Exception
	 */
	void deleteProductCategory(Map<String, Object> map) throws Exception;
	
	/**
	 * 카테고리별 제품 테이블을 등록한다.
	 * @param map
	 * @throws Exception
	 */
	void addProductCategory(Map<String, Object> map) throws Exception;
	
	/**
	 * 질병과 추천 생약제 선택된 카테고리(필수 & 선택)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> diseaseSecurityContentsMedicine(Map<String, Object> map) throws Exception;
	
	/**
	 * 질병과 추천 생약제 테이블을 삭제한다.
	 * @param map
	 * @throws Exception
	 */
	void deleteSecurityContentsMedicine(Map<String, Object> map) throws Exception;
	
	/**
	 * 질병과 추천 생약제 테이블을 등록한다.
	 * @param map
	 * @throws Exception
	 */
	void addSecurityContentsMedicine(Map<String, Object> map) throws Exception;
	
	/**
	 * 가격 할인
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> productSpecial(Map<String, Object> map) throws Exception;
	
	/**
	 * 가격 할인 등록
	 * @param map
	 * @throws Exception
	 */
	void addProductSpecial(Map<String, Object> map) throws Exception;
	
	/**
	 * 가격 할인 삭제
	 * @param map
	 * @throws Exception
	 */
	void deleteProductSpecial(Map<String, Object> map) throws Exception;
}
