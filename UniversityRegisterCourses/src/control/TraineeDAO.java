package control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.corba.se.pept.transport.Connection;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TraineeDAO {
	public StudentVO getStudentSubjectName(String sd_id) throws Exception{
		
		String sql = "select sd_num, sd_name, (select s_name from subject where s_num = (select s_num from student where sd_id = ? )) as s_num from student where sd_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO studentInfo = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.preapreStatememt(sql);
			pstmt.setString(1, sd_id);
			pstmt.setString(2, sd_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				studentInfo = new StudentVO();
				studentInfo.setSd_num(rs.getString("sd_num"));
				studentInfo.setSd_name(rs.getString("sd_name"));
				studentInfo.setS_num(rs.get.String("s_num"));
				
			}
			
		}catch(SQLException se) {
			System.out.println(se);
			
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			try {
				if(rs != null)  rs.close();
				if(pstmt != null)  pstmt.close();
				if(con != null)  con.close();
 			}catch (SQLException se) {
 				
 			}
		}
		return studentInfo;
	}
	//선택한 과목명의 과목번호
	public String getLessonNum(String lessonName)throws Exception{
		String l_num = "";
		
		String sql = "select l_num from lesson where l_name = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next) {
				l_num = rs.getString("l_num");
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("수강 과목의 과목번호");
				alert.setHeaderText("선택한" + lessonName + "과목의 과목번호가 없습니다");
				alert.setContentText("과목 검색 실패");
				alert.showAndWait();
			}
			
		}catch(SQLException se) {
			System.out.println(se);
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			try {
				if(rs != null)  rs.close();
				if(pstmt != null)  pstmt.close();
				if(con != null)   con.close();
			}catch (SQLException)
		}
		
	}

}
