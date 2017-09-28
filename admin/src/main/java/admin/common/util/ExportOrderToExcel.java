package admin.common.util;

import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportOrderToExcel extends AbstractExcelPOIView {
	 
    @SuppressWarnings("unchecked")
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        String fileName = ObjectUtils.null2Value(model.get("fileName"),("Order_Export"+DateUtils.getToday("yyyy-MM-dd")));
 
        //Object로 넘어온 값을 각 Model에 맞게 형변환 해준다.
        List<Map<String, Object>> list = (List<Map<String, Object>>) model.get("excelList");
//        System.err.println("size=============================>"+list.size());
 
        //Sheet 생성
        Sheet sheet = workbook.createSheet(fileName);
        Row row = null;
        int rowCount = 0;
        int cellCount = 0;
 
        // 제목 Cell 생성
        row = sheet.createRow(rowCount++);
 
        row.createCell(cellCount++).setCellValue("주문번호"); // 1
        row.createCell(cellCount++).setCellValue("고객코드"); // 2
        row.createCell(cellCount++).setCellValue("vcode"); // 3
        row.createCell(cellCount++).setCellValue("운송장번호"); // 4
        row.createCell(cellCount++).setCellValue("발송번호"); // 5
        row.createCell(cellCount++).setCellValue("배송자명"); // 6
        row.createCell(cellCount++).setCellValue("배송자"); // 7
        row.createCell(cellCount++).setCellValue("배송자 팩스"); // 8
        row.createCell(cellCount++).setCellValue("배송자 email"); // 9
        row.createCell(cellCount++).setCellValue("배송자 주소"); // 10
        row.createCell(cellCount++).setCellValue("배송자 city"); // 11
        row.createCell(cellCount++).setCellValue("배송자 state"); // 12
        row.createCell(cellCount++).setCellValue("배송자 zip"); // 13
        row.createCell(cellCount++).setCellValue("수신자명"); // 14
        row.createCell(cellCount++).setCellValue("수신자 전화번호"); // 15
        row.createCell(cellCount++).setCellValue("수신자 전화번호2"); // 16
        row.createCell(cellCount++).setCellValue("수신자 email"); // 17
        row.createCell(cellCount++).setCellValue("수신자 주소"); // 18
        row.createCell(cellCount++).setCellValue("수신자 city"); // 19
        row.createCell(cellCount++).setCellValue("수신자 state"); // 20
        row.createCell(cellCount++).setCellValue("수신자 우편번호"); // 21
        row.createCell(cellCount++).setCellValue("수신자 국가"); // 22
        row.createCell(cellCount++).setCellValue("WEIGHT"); // 23
        row.createCell(cellCount++).setCellValue("지불금액"); // 24
        row.createCell(cellCount++).setCellValue("배송료"); // 25
        row.createCell(cellCount++).setCellValue("배송료tax"); // 26
        row.createCell(cellCount++).setCellValue("Memo"); // 27
        row.createCell(cellCount++).setCellValue("주민번호"); // 28
        row.createCell(cellCount++).setCellValue("Brand"); // 29
        row.createCell(cellCount++).setCellValue("물품명1"); // 30
        row.createCell(cellCount++).setCellValue("Commodity"); // 31
        row.createCell(cellCount++).setCellValue("수량1"); // 32
        row.createCell(cellCount++).setCellValue("중량1"); // 33
        row.createCell(cellCount++).setCellValue("단가1"); // 34
        row.createCell(cellCount++).setCellValue("가격1"); // 35
        row.createCell(cellCount++).setCellValue("HS Code"); // 36
 
        // 데이터 Cell 생성
        int num = 0;
        String befOrderId = "";
        String nowOrderId = "";
        String telephone = "";
        String order_number = "drp"+DateUtils.getToday("MM-dd-yy");
        for (Map<String, Object> order : list) {
        	nowOrderId = ObjectUtils.null2void(order.get("order_id"));
        	if(!befOrderId.equals(nowOrderId)) {
        		num++;
        	}
            row = sheet.createRow(rowCount++);
            cellCount = 0;
            row.createCell(cellCount++).setCellValue(order_number+"-"+num); // 1.주문번호
            row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("order_id"))); // 2.고객코드
            row.createCell(cellCount++).setCellValue("SDPN"); // 3.vcode
            row.createCell(cellCount++).setCellValue(""); // 4.운송장번호
            row.createCell(cellCount++).setCellValue(""); // 5.발송번호
            row.createCell(cellCount++).setCellValue("Pure Natural(www.drpurenatural.com)"); // 6.배송자명
            row.createCell(cellCount++).setCellValue("-"); // 7.배송자
            row.createCell(cellCount++).setCellValue(""); // 8.배송자 팩스
            row.createCell(cellCount++).setCellValue(""); // 9.배송자 email
            row.createCell(cellCount++).setCellValue("P.O. Box 1533"); // 10.배송자 주소
            row.createCell(cellCount++).setCellValue("La Mirada"); // 11.배송자 city
            row.createCell(cellCount++).setCellValue("CA"); // 12.배송자 state
            row.createCell(cellCount++).setCellValue("90637"); // 13.배송자 zip
            row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("shipping_firstname"))+" "+ObjectUtils.null2void(order.get("shipping_lastname"))); // 14.수신자명
            if(null!=order.get("shipping_telephone") && !order.get("shipping_telephone").equals("")) {
            	telephone = StringUtils.getOnlyNum(ObjectUtils.null2void(order.get("shipping_telephone")));
            } else {
            	telephone = StringUtils.getOnlyNum(ObjectUtils.null2void(order.get("telephone")));
            }
            row.createCell(cellCount++).setCellValue(telephone); // 15.수신자 전화번호
            row.createCell(cellCount++).setCellValue(""); // 16.수신자 전화번호2
            row.createCell(cellCount++).setCellValue(""); // 17.수신자 email
            row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("shipping_address_1"))+" "+ObjectUtils.null2void(order.get("shipping_address_2"))); // 18.수신자 주소
            row.createCell(cellCount++).setCellValue(""); // 19.수신자 city
            row.createCell(cellCount++).setCellValue(""); // 20.수신자 state
            row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("shipping_postcode"))); // 21.수신자 우편번호
            row.createCell(cellCount++).setCellValue("KR"); // 22.수신자 국가
            row.createCell(cellCount++).setCellValue(""); // 23.WEIGHT
            row.createCell(cellCount++).setCellValue(0); // 24.지불금액
            row.createCell(cellCount++).setCellValue(0); // 25.배송료
            row.createCell(cellCount++).setCellValue(0); // 26.배송료tax
            row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("comment"))); // 27.Memo
            row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("requisition_id"))); // 28.주민번호
            row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("manufacturer_name"))); // 29.Brand
            if(null!=order.get("ean") && !order.get("ean").equals("")) {
            	row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("ean"))); // 30.물품명1
            } else {
            	row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("name"))); // 30.물품명1
            }
//            System.err.println(order.get("category_id"));
            if(null!=order.get("category_id") && order.get("category_id").equals("91")) {
            	row.createCell(cellCount++).setCellValue("Cosmetic"); // 31.Commodity
            } else {
            	row.createCell(cellCount++).setCellValue("FOOD PREPARATIONS FOR HEALTH"); // 31.Commodity
            }
            row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("quantity"))); // 32.수량1
            row.createCell(cellCount++).setCellValue(""); // 33.중량1
            row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("price"))); // 34.단가1
            row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("total"))); // 35.가격1
            row.createCell(cellCount++).setCellValue(ObjectUtils.null2void(order.get("mpn"))); // 36.HS Code
            befOrderId = nowOrderId;
        }
 
    }
 
    @Override
    protected Workbook createWorkbook() {
        return new XSSFWorkbook();
    }
 
}