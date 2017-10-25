import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.common.util.CommonUtils;

public class PasswordDecode {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://208.72.223.11/drpure_database?autoReconnect=true";

	//  Database credentials
	static final String USER = "drpure_user";
	static final String PASS = "Notafan12?";

	public static void main(String[] args) {
		Connection conn = null;
//		Statement stmt = null;
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
//				sql.append("SELECT MAX(string_no) AS max_string_no, string_id, new_string1 FROM dr_customer_string WHERE string_id=30732 group by string_id, new_string1");
//				sql.append("SELECT MAX(string_no) AS max_string_no, string_id, new_string1 FROM dr_customer_string WHERE date_added > '2017-09-21' group by string_id, new_string1 ORDER BY string_id, date_added ASC");
				sql.append("SELECT * ");
				sql.append("  FROM dr_customer_string cs ");
				sql.append(" WHERE cs.string_no in( ");
				sql.append("SELECT MAX(string_no) as max_string_no  FROM dr_customer_string ");
				sql.append(" WHERE date_added > '2017-09-21' ");
				sql.append(" GROUP BY string_id) ");
				sql.append(" ORDER BY string_id ");

//				stmt = conn.createStatement();
				pstmt = conn.prepareStatement(sql.toString());        
				rs = pstmt.executeQuery();
				int count=0;
				// 출력
				while(rs.next()){ // boolean 값을 던짐
					int string_id = rs.getInt(2); // 바인딩
					String new_string1 = CommonUtils.base64Decode(rs.getString(3));
					try
					{
//						System.out.print("["+count+++"]"+new_string1+"::");
						if(!isMatch(conn, string_id, new_string1)) {
//						    update(conn, string_id, new_string1);
						}
					}
					catch (Exception e)
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
//			System.out.println("finally");
			try{ // 연결 해제(한정돼 있으므로)
				if(rs!=null){        rs.close();            }
				if(pstmt!=null){    pstmt.close();        }
//				if(stmt!=null){    stmt.close();        }
				if(conn!=null){    conn.close();        }
			}catch(SQLException se2){
				se2.printStackTrace();
			}            
		}
	}
	
	public static boolean isMatch(Connection conn, int customer_id, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isMatch = true;
		
		try {
//			System.out.println(customer_id+","+CommonUtils.shaEncoder(password));
			// 출력 준비
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT password FROM dr_customer WHERE customer_id=").append(customer_id);
			pstmt = conn.prepareStatement(sql.toString());  
			rs = pstmt.executeQuery();
			rs.next();
			String db_password = rs.getString("password");
			String last_password = CommonUtils.shaEncoder(password);
//			System.out.println(customer_id+",db:"+db_password+":"+last_password);
			if(!db_password.equals(last_password)) {
				isMatch = false;
				System.out.println("[FALSE]"+password+"..."+customer_id+",db:"+db_password+":"+last_password);
			} else {
//				System.out.println("[TRUE]"+customer_id+",db:"+db_password+":"+last_password);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			System.out.println("finally");
			try{ // 연결 해제(한정돼 있으므로)
				if(rs!=null){        rs.close();            }
				if(pstmt!=null){    pstmt.close(); }
			}catch(SQLException se2){
				se2.printStackTrace();
			}            
		}
		return isMatch;
	}
	
	public static void update(Connection conn, int customer_id, String password) {
		PreparedStatement pstmt = null;
		
		try {
			System.out.println(customer_id+","+CommonUtils.shaEncoder(password));
			// 출력 준비
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE dr_customer SET password='").append(CommonUtils.shaEncoder(password)).append("' WHERE customer_id=").append(customer_id);
			pstmt = conn.prepareStatement(sql.toString());        
			pstmt.executeUpdate(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("finally");
			try{ // 연결 해제(한정돼 있으므로)
				if(pstmt!=null){    pstmt.close(); }
			}catch(SQLException se2){
				se2.printStackTrace();
			}            
		}
	}

}

