package admin.common.service;

import java.util.List;
import java.util.Map;
 
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import admin.common.dao.ExcelDAO;

@Service(value = "excelService")
public class ExcelServiceImpl implements ExcelService {
 
    @Resource(name = "excelDAO")
    private ExcelDAO excelDAO;
 
 
    @Override
    public List<Map<String,Object>> getExportOrders(Map<String, Object> map) throws Exception {
    	return excelDAO.getExportOrders(map);//검색조건 searchMap를 넘겨줌
    }
 
}
