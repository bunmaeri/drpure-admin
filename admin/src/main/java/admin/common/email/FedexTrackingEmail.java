package admin.common.email;

import java.util.Map;

public class FedexTrackingEmail {
	public static String getHtml(Map<String,Object> map) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/1999/REC-html401-19991224/strict.dtd\">");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		sb.append("<title>").append(map.get("store_name")).append(" - 상품배송이 시작되었습니(주문번호: ").append(map.get("order_id")).append(")</title>");
		sb.append("</head>");
		sb.append("<body style=\"font-family: Verdana, Helvetica, sans-serif; font-size: 14px; color: #000000;\">");
		sb.append("<div style=\"width: 720px;\">");
		sb.append("<a href=\"").append(map.get("store_url")).append("\" title=\"").append(map.get("store_name")).append("\"><img src=\"").append(map.get("store_url")).append("image/logo.png\" alt=\"").append(map.get("store_name")).append("\" style=\"margin-bottom: 20px; border: none;\" /></a>");
		sb.append("<div style=\"font-family:verdana, arial,','Malgun Gothic','맑은 고딕',sans-serif;\">");
		sb.append("    고객님의 상품배송이 시작되었습니다.<br/><br/>");
		sb.append("    주문번호: ").append(map.get("order_id")).append("<br/>");
		sb.append("    주문일자: ").append(map.get("order_date")).append("<br/>");
		sb.append("    배송회사: FedEx<br/>");
		sb.append("    Tracking#: ").append(map.get("tracking_number")).append("<br/><br/>");
		sb.append("    고객님의 배송상황을 보시려면 아래 링크를 클릭해주세요:<br>");
		sb.append("    <a href=\"https://www.fedex.com/apps/fedextrack/?tracknumbers=").append(map.get("tracking_number")).append("\" target=\"_blank\">https://www.fedex.com/apps/fedextrack/?tracknumbers=").append(map.get("tracking_number")).append("</a><br/>");
		sb.append("    링크에 문제가 있을 때에는 아래 주소로 들어가셔서 Tracking a Shipment에 Tracking#를 입력하고 Track 버튼을 클릭 하십시오.<br>");
		sb.append("    <a href=\"https://www.fedex.com/us/\" target=\"_blank\">https://www.fedex.com/us/</a><br/><br/>");
		sb.append("    고객님의 주문내용을 보시려면 아래 링크를 클릭해주세요:<br>");
		sb.append("    <a href=\"").append(map.get("store_url")).append("account/order/view/").append(map.get("order_id")).append(".dr\" target=\"_blank\">").append(map.get("store_url")).append("account/order/view/").append(map.get("order_id")).append(".dr</a><br/>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
	}
}
