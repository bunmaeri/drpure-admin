package admin.common.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import admin.common.common.CommandMap;

@Controller
public class ServerRestartController {
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * Admin
	 * http://drpurenatural.com:9999/atserver.dr
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/atserver.dr")
	public ModelAndView atserver(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/server_restart");
		
		shellCmd("sh /usr/local/server/tomcat8_admin/script/serverRestart.sh");
		return mv;
	}
	
	/**
	 * Dr. Pure
	 * http://drpurenatural.com:9999/tserver.dr
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/tserver.dr")
	public ModelAndView tserver(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/server_restart");
		
		shellCmd("sh /usr/local/server/tomcat8/script/serverRestart.sh");
		return mv;
	}
	
	/**
	 * My Home
	 * http://drpurenatural.com:9999/mtserver.dr
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mtserver.dr")
	public ModelAndView mtserver(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/server_restart");
		
		shellCmd("sh /usr/local/server/tomcat8_my/script/serverRestart.sh");
		return mv;
	}
	
	public static void shellCmd(String command) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while((line = br.readLine()) != null) {
        	System.out.println(line);
        }
	}
}
