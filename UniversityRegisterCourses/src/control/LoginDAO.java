package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

	//관리자 로그인
	public boolean getLogin(String loginId, String loginPassword) throws Exception{
		String sql = "select * from managerjoin where id = ? and password = ?";
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		boolean loginResult = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPassword);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginResult = true;//아이디와 패스워드가 일치한다.
				
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		}catch (Exception e) {
			System.out.println("e=[" + e + "]");
		}finally {
			try {
				//데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
			}
			
		}
	}
}
