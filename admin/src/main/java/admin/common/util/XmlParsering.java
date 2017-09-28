package admin.common.util;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
 
import org.w3c.dom.Document;

public class XmlParsering {

	public Document start(String api_url) throws Exception{
        URL url = new URL(api_url);
        URLConnection connection = url.openConnection();
 
//        InputStream stream = connection.getInputStream();
        Document doc = parseXML(connection.getInputStream());      
        
        return doc;
    }
	
	private Document parseXML(InputStream stream) throws Exception{
		 
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
 
        try{
 
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
 
            doc = objDocumentBuilder.parse(stream);
 
        }catch(Exception ex){
            throw ex;
        }       
 
        return doc;
    }
}
