import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InformationMagento {
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
            		sql.append(" SELECT t1.title,");
            		sql.append("        t1.identifier,");
            		sql.append("        t1.content");
            		sql.append("   FROM cms_page t1, cms_page_store t2");
            		sql.append("  WHERE t1.page_id=t2.page_id");
            		sql.append("    AND t2.store_id=3");
	            
	                stmt = conn.createStatement();
	                pstmt = conn.prepareStatement(sql.toString());        
	                rs = pstmt.executeQuery();
	                // 출력
	                while(rs.next()){ // boolean 값을 던짐
	                    String title = rs.getString(1); // 바인딩
	                    String identifier = rs.getString(2);
	                    String content = rs.getString(3);
	                    
	                    try
	                    {
//	                    	title = title.replace("/", "_");
	                        FileWriter fw = new FileWriter("/Users/jo/Documents/DrPure/_etc/magento/informations/"+title+".txt"); // 절대주소 경로 가능
	                        BufferedWriter bw = new BufferedWriter(fw);
	             
	                        bw.write("[Title]");
	                        bw.newLine(); // 줄바꿈
	                        bw.write(title);
	                        bw.newLine(); // 줄바꿈
	                        bw.newLine(); // 줄바꿈
	                        bw.write("[Contents]");
	                        bw.newLine(); // 줄바꿈
	                        bw.write(content);
	                         
	                        bw.close();
	                        System.out.println(title);
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