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

import admin.system.service.TaxRatesService;

@Controller
public class TaxRatesController extends BaseController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="taxRatesService")
	private TaxRatesService taxRatesService;

	/**
	 * Tax 페이지
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/settings/tax/rates.dr")
    public ModelAndView productsManufacturer(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/system/tax_rates");

		// Zone 목록
		List<Map<String,Object>> geoZoneList = taxRatesService.geoZoneList();
		mv.addObject("geoZoneList", geoZoneList);
				
		// Tax 목록
		List<Map<String,Object>> list = taxRatesService.taxRatesList(commandMap.getMap());
		mv.addObject("list", list);
		
		if(null!=BaseController.getCustomSession(request, Session.SUCCESS)) {
    		mv.addObject("successMsg", BaseController.getCustomSession(request, Session.SUCCESS));
    		BaseController.setCustomSession(request, null, Session.SUCCESS);
    	}
		
		return mv;
    }
	
	/**
	 * Tax 저장
	 * @param commandMap       
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/settings/tax/rates/save.dr")
    public ModelAndView productSaveCategory(HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/settings/tax/rates.dr");
		
		String[] tax_rate_ids = request.getParameterValues("tax_rate_id");
		String[] geo_zone_ids = request.getParameterValues("geo_zone_id");
		String[] names = request.getParameterValues("name");
		String[] rates = request.getParameterValues("rate");
		int size = tax_rate_ids.length;
		Map<String,Object> map = null;
		for(int i=0;i<size;i++) {
			map = new HashMap<String,Object>();
			if(!tax_rate_ids[i].equals("") && !names[i].equals("")) {
				map.put("tax_rate_id", tax_rate_ids[i]);
				map.put("geo_zone_id", geo_zone_ids[i]);
				map.put("name", names[i]);
				map.put("rate", rates[i]);
				map.put("type", "P");
				taxRatesService.updateTaxRates(map);
			} else 
			if(!names[i].equals("") && !rates[i].equals("")) {
				map.put("geo_zone_id", geo_zone_ids[i]);
				map.put("name", names[i]);
				map.put("rate", rates[i]);
				map.put("type", "P");
				taxRatesService.addTaxRates(map);
			}
		}
		
		BaseController.setCustomSession(request, "정상적으로 저장되었습니다.", Session.SUCCESS);
    	
       	return mv;
    }
	
	/**
	 * Tax 삭제
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/settings/tax/rates/delete/{tax_rate_id}.dr")
    public ModelAndView deleteManufacturer(HttpServletRequest request, @PathVariable String tax_rate_id, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/settings/tax/rates.dr");

    	commandMap.put("tax_rate_id", tax_rate_id);
    	taxRatesService.deleteTaxRates(commandMap.getMap());
    	
    	BaseController.setCustomSession(request, "정상적으로 삭제되었습니다.", Session.SUCCESS);
    	return mv;
    }
}
