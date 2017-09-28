package admin.common.util;

import java.io.File;

import org.apache.log4j.Logger;

public class StoreUtils {
	private static final Logger log = Logger.getLogger(StoreUtils.class);
	private static final int language_id = 1;
	private static final String language_name = "한국어";
	private static final String store_id = "0";
	private static final String store_name = "Dr. Pure Natural";
//	private static final String store_url = "http://drpurenatural.com/";
	private static final String store_url = "http://208.72.223.10:9090/";
	private static final String currency_id = "2";
	private static final String currency_value = "1.00000000";
	private static final String currency_code = "USD";
	private static final String country_id = "113";
	
	private static final String filePath = "/home/www/drpurenatural/image/"; //server
	private static final String deleteFilePath = "/home/www/drpurenatural"; //server
//	private static final String filePath = "/Users/jo/Drpure/_new/image/"; // local
//	private static final String deleteFilePath = "/Users/jo/Drpure/_new"; // local
	private static final String geolite = "/home/www/drpurenatural/geolite/"; //server
//	private static final String geolite = "/Users/jo/Drpure/_new/geolite/"; // local
	
	public static int getLanguageId(){
//		log.debug("-- Language id : " + language_id + "\n");
		return language_id;
	}
	
	public static String getLanguageIdString(){
//		log.debug("-- Language id : " + language_id + "\n");
		return String.valueOf(getLanguageId());
	}
	
	public static String getLanguageName(){
		log.debug("-- Language name : " + language_name + "\n");
		return language_name;
	}
	
	public static String getStoreId() {
		return store_id;
	}

	public static String getStoreName() {
		return store_name;
	}

	public static String getStoreUrl() {
		return store_url;
	}

	public static String getCurrencyId() {
		return currency_id;
	}

	public static String getCurrencyCode() {
		return currency_code;
	}

	public static String getCurrencyValue() {
		return currency_value;
	}

	public static String getCurrency() {
		return "$";
	}

	public static String getCountryId() {
		return country_id;
	}

	public static String getFilepath() {
		File d = new File(filePath);
        if(!d.isDirectory()){
        	return "/Users/jo/Drpure/_new/image/";
        }
		return filePath;
	}
	public static String getDeleteFilepath() {
		File d = new File(deleteFilePath);
        if(!d.isDirectory()){
        	return "/Users/jo/Drpure/_new";
        }
		return deleteFilePath;
	}
	public static String getGeolite() {
		File d = new File(geolite);
        if(!d.isDirectory()){
        	return "/Users/jo/Drpure/_new/geolite/";
        }
		return geolite;
	}
	public static String getTablePrefix(String store_id) {
		if(store_id.equals("0")) return "dr";
		else return "my";
	}
	
}
