package admin.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import admin.common.common.CommandMap;
import admin.common.constant.Session;
import admin.common.controller.BaseController;
import admin.common.util.FileUtils;

import admin.system.service.OrderStatusService;

@Controller
public class OrderStatusController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="orderStatusService")
	private OrderStatusService orderStatusService;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;

	/**
	 * 주문상태 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/settings/order/status.dr")
    public ModelAndView productsManufacturer(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/system/order_status");

		// 제조사 목록
		List<Map<String,Object>> list = orderStatusService.orderStatusList(commandMap.getMap());
		mv.addObject("list", list);
		
		if(null!=BaseController.getCustomSession(request, Session.SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SUCCESS);
    	}
		
		return mv;
    }
	
	/**
	 * 주문상태 저장
	 * @param commandMap       
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/settings/order/status/save.dr")
    public ModelAndView productSaveCategory(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/settings/order/status.dr");
		
		String[] order_status_ids = request.getParameterValues("order_status_id");
		String[] names = request.getParameterValues("name");
		String[] sort_orderes = request.getParameterValues("sort_order");
		int size = order_status_ids.length;
		Map<String,Object> map = null;
		for(int i=0;i<size;i++) {
			map = new HashMap<String,Object>();
			if(!order_status_ids[i].equals("") && !names[i].equals("")) {
				map.put("order_status_id", order_status_ids[i]);
				map.put("name", names[i]);
				map.put("sort_order", sort_orderes[i]);
				orderStatusService.updateOrderStatus(map);
			} else 
			if(!names[i].equals("")) {
				map.put("language_id", "1");
				map.put("name", names[i]);
				map.put("sort_order", sort_orderes[i]);
				orderStatusService.addOrderStatus(map);
			}
		}
		
		BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
    	
       	return mv;
    }
	
	/**
	 * 주문상태 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/settings/order/status/delete/{order_status_id}.dr")
    public ModelAndView deleteManufacturer(HttpServletRequest request, @PathVariable String order_status_id, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/settings/order/status.dr");

    	commandMap.put("order_status_id", order_status_id);
    	orderStatusService.deleteOrderStatus(commandMap.getMap());
    	
    	BaseController.setCustomSession(request, "정상적으로 삭제되었습니다.", Session.SUCCESS);
    	return mv;
    }
}
