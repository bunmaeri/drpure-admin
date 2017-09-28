package admin.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import admin.common.common.CommandMap;
import admin.common.service.ExcelService;
import admin.common.util.DateUtils;

@Controller
public class ExcelController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
    ExcelService excelService;
	
	@RequestMapping("/sales/orders/export.dr")
    public String salesOrdersExport(CommandMap commandMap, Map<String, Object> ModelMap, HttpServletResponse response) throws Exception{
        String fileName = "Order_Export"+DateUtils.getToday("yyyy-MM-dd");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//	    response.setHeader("Content-Disposition", "attachment; filename=report.xlsx");
//		response.setHeader("Content-Type", "text/csv; charset=UTF-8"); // CSV
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".csv"); // 파일명 작성
 
        //엑셀에 작성할 리스트를 가져온다.
        commandMap.put("order_status_id", 34);
        commandMap.put("shipping_country_id", "113");
        commandMap.put("table_prefix", "dr");
    	commandMap.put("store_id", "0");
        List<Map<String, Object>> drExcelList = excelService.getExportOrders(commandMap.getMap());
        
        commandMap.put("table_prefix", "my");
    	commandMap.put("store_id", "1");
        List<Map<String, Object>> myExcelList = excelService.getExportOrders(commandMap.getMap());
         
        List<Map<String, Object>> excelList = new ArrayList<Map<String, Object>>();
        excelList.addAll(drExcelList);
        excelList.addAll(myExcelList);
        //ExcelView(kr.co.myapp.util.ExcelView) 에 넘겨줄 값 셋팅
        ModelMap.put("excelList", excelList); 
        ModelMap.put("fileName", fileName); 

        return "exportOrderToExcel"; //servlet-context.xml 에서 name이 exportOrderToExcel(admin.common.util.ExportOrderToExcel)인것 호출
    }
}
