import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProductMagento {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://208.72.223.11:3306/old_DB?autoReconnect=true";

	//  Database credentials
	static final String USER = "drpure_user";
	static final String PASS = "Notafan12?";
	
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            Class.forName(JDBC_DRIVER); // JDBC 드라이버 로드
            conn = DriverManager.getConnection(DB_URL, USER, PASS); // 데이터베이스 연결(id/pw)
            if(conn==null){
                System.out.println("연결실패");
            }else{
                System.out.println("연결성공");
                // 출력 준비
                StringBuffer sql = new StringBuffer();
                sql.append("SELECT m.entity_id,");
                sql.append("       m.sku, ");
                sql.append("       (select s.value from catalog_product_entity_varchar s where s.entity_id=m.entity_id and s.attribute_id=71 and s.store_id=0) AS p_name, ");
                sql.append("       IFNULL((select s.value from catalog_product_entity_varchar s where s.entity_id=m.entity_id and s.attribute_id=71 and s.store_id=3),");
                sql.append("              (select s.value from catalog_product_entity_varchar s where s.entity_id=m.entity_id and s.attribute_id=71 and s.store_id=0)) AS product_name,");
                sql.append("       IFNULL((select s.value from catalog_product_entity_text s where s.entity_id=m.entity_id and s.attribute_id=72 and s.store_id=3),'') AS description,");
                sql.append("       IFNULL((select s.value from catalog_product_entity_text s where s.entity_id=m.entity_id and s.attribute_id=73 and s.store_id=3),'') AS short_description");
                sql.append("  FROM catalog_product_entity m");
                sql.append(" order by product_name asc");
 
                stmt = conn.createStatement();
                pstmt = conn.prepareStatement(sql.toString());        
                rs = pstmt.executeQuery();
                // 출력
                while(rs.next()){ // boolean 값을 던짐
                    String entity_id = rs.getString(1); // 바인딩
                    String sku = rs.getString(2);
                    String p_name = rs.getString(3);
                    String product_name = rs.getString(4);
                    String description = rs.getString(5);
                    String short_description = rs.getString(6);
                    
                    try
                    {
                    	p_name = p_name.replace("/", "_");
                        FileWriter fw = new FileWriter("/Users/jo/Documents/DrPure/_etc/magento/products/"+p_name+".txt"); // 절대주소 경로 가능
                        BufferedWriter bw = new BufferedWriter(fw);
             
                        bw.write("[Product Name]:"+product_name);
                        bw.newLine(); // 줄바꿈
                        bw.newLine(); // 줄바꿈
                        bw.write("[Product Description]");
                        bw.newLine(); // 줄바꿈
                        bw.write(description);
                        bw.newLine(); // 줄바꿈
                        bw.newLine(); // 줄바꿈
                        bw.write("[Product Short Description]");
                        bw.newLine(); // 줄바꿈
                        bw.write(short_description);
                         
                        bw.close();
                        System.out.println(p_name);
                    }
                    catch (IOException e)
                    {
                        System.err.println(e); // 에러가 있다면 메시지 출력
                        System.exit(1);
                    }
                }
            }
        }catch(ClassNotFoundException ce){
            ce.printStackTrace();            
        }catch(SQLException se){
            se.printStackTrace();    
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            System.out.println("finally");
            try{ // 연결 해제(한정돼 있으므로)
                if(rs!=null){        rs.close();            }
                if(pstmt!=null){    pstmt.close();        }
                if(stmt!=null){    stmt.close();        }
                if(conn!=null){    conn.close();        }
            }catch(SQLException se2){
                se2.printStackTrace();
            }            
        }
    }

}

