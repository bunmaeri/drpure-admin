import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.common.util.CommonUtils;

public class DrUpdateStockStatusByQuantity {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://208.72.223.11/drpure_database?autoReconnect=true";

	//  Database credentials
	static final String USER = "drpure_user";
	static final String PASS = "Notafan12?";

	public static void main(String[] args) {
		Connection conn = null;
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
				sql.append("SELECT pq.model, pq.quantity, p.stock_status_id FROM product_quantity pq, dr_product p WHERE pq.model=p.model ORDER BY pq.model");

				pstmt = conn.prepareStatement(sql.toString());        
				rs = pstmt.executeQuery();
				int count=0;
				// 출력
				while(rs.next()){ // boolean 값을 던짐
					String model = rs.getString(1); // 바인딩
					int quantity = rs.getInt(2);
					int stock_status_id = rs.getInt(3);
					try
					{
						System.out.println();
						System.out.println("["+count+++"]"+model+"::"+quantity+"::"+stock_status_id);
						
						update(conn, model, quantity, stock_status_id);
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
				if(conn!=null){    conn.close();        }
			}catch(SQLException se2){
				se2.printStackTrace();
			}            
		}
	}
	
	public static void update(Connection conn, String model, int quantity, int stock_status_id) {
		PreparedStatement pstmt = null;
		
		try {
			if(stock_status_id==5 || stock_status_id==7) {
				// 출력 준비
				StringBuffer sql = new StringBuffer();
				if(quantity>0) {
					stock_status_id = 7;
				} else {
					stock_status_id = 5;
				}
				
				sql.append("UPDATE dr_product SET stock_status_id = ").append(stock_status_id).append(" WHERE model = '").append(model).append("'");
				pstmt = conn.prepareStatement(sql.toString());        
				pstmt.executeUpdate(sql.toString());
			}
		}catch(Exception e){
			System.out.println("중복 Skip====================");
			//e.printStackTrace();
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

