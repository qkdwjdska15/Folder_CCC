package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {
@FXML
private Label lblLogin;//로그인 라벨
@FXML
private TextField txtId;//아이디 입력 필드칸
@FXML
private PasswordField txtPassword;//비밀번호 입력 필드 칸
@FXML
private Button btnCancel;//닫기 버튼
@FXML
private Button btnLogin;//로그인 버튼
@FXML
private Button btnJoin;//등록 버튼
@FXML
private ToggleGroup loginGroup;//로그인 토글
@FXML
private RadioButton rbManager;
@FXML
private RadioButton rbStudent;
@FXML
private Label lnlIconImg;
@FXML
private ImageView iconImg;

public static String managerName="";
public static String studentId="";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtId.setOnKeyPressed(event -> handlerTxtIdKeyPressed(event));//아이디 입력에서 Enter키 이벤트 적용
		txtPassword.setOnKeyPressed(event -> handlerTxtPasswordKeyPressed(event));//패스워드 입려에서 Enter키 이벤트 적용
		btnJoin.setOnAction(event -> handlerBtnJoinAction(event));//관리자 등록창으로 전환
		btnLogin.setOnAction(event -> handlerBtnLoginAction(event));//로그인
		btnCancel.setOnAction(event -> handlerBtnCancelAction(event));//로그인창 닫기
		rbManager.setOnAction(event -> handlerRbManagerAction(event));
		rbStudent.setOnAction(event -> handlerRbStudentAction(event));
	}



	//학생 라디오 버튼 이벤트 핸들러
	public void handlerRbStudentAction(ActionEvent event) {
		URL srtImg = getClass().getResource("../image/student.png");
		Image image = new Image(srtImg.toString());
		iconImg.setImage(image);
		lblLogin.setText("학생 로그인");
		btnJoin.setDisable(true);
		btnLogin.setText("학생 로그인");
	}
	
	//관리자 라디오 버튼 이벤트 핸들러
	public void handlerRbManagerAction(ActionEvent event) {
		URL srtImg = getClass().getResource("../image/manager.png");
		Image image = new Image(srtImg.toString());
		iconImg.setImage(image);
		lblLogin.setText("관리자 로그인");
		btnJoin.setDisable(false);
		btnLogin.setText("관리자 로그인");
	}
	
	//아이디 입력에서 Enter키 이벤트 적용
	public void handlerTxtIdKeyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			txtPassword.requestFocus();
		}
	}
	
	//패스워드 입력에서 Enter키 이벤트 적용
	public void handlerTxtPasswordKeyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			login();
		}
	}
	
	//관리자 등록창으로 전환
	public void handlerBtnJoinAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/join.fxml"));
			Parent mainView = (Parent)loader.load();
			Scene scene = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("관리자 등록");
			mainMtage.setScene(scene);
			Stage oldStage = (Stage)btnLogin.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.out.println("오류" + e);
		}
	}
	
	//로그인창 닫기
	public void handlerBtnCancelAction(ActionEvent event) {
		Platform.exit();
	}
	
	//로그인
	public void handlerBtnLoginAction(ActionEvent event) {
		login();
	}

	//로그인 메소드
	public void login() {
		LoginDAO login = new LoginDAO();
		StudentDAO sLogin = new StudentDAO();
		boolean sucess = false;
		try {
			if("manager".equals(loginGroup.getSelectedToggle().getUserData().toString())) {
				managerName = managerLoginName();
				sucess = login.getLogin(txtId.getText().trim(), txtPassword.getText().trim());
				
				//로그인 성공시 메인 페이지로 이동
				if(sucess) {
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
						Parent mainView = (Parent)loader.load();
						Scene scene = new Scene(mainView);
						Stage mainMtage = new Stage();
						mainMtage.setTitle("미래 대학교 학사관리");
						mainMtage.setResizable(false);
						mainMtage.setScene(scene);
						Stage oldStage = (Stage)btnLogin.getScene().getWindow();
						oldStage.close();
						mainMtage.show();
					} catch (IOException e) {
						System.err.println("오류" + e);
					}
				}else {
					//아이디 패스워드 확인하라는 창
					Alert alert;
					alert = new Alert(AlertType.WARNING);
					alert.setTitle("로그인 실패");
					alert.setHeaderText("아이디와 비밀번호 불일치");
					alert.setContentText("아이디와 비밀번호가 일치하지 않습니다 \n 다시 제대로 입력하시오.");
					alert.setResizable(false);
					alert.showAndWait();
					txtId.clear();
					txtPassword.clear();
				}
			}else if("student".equals(loginGroup.getSelectedToggle().getUserData().toString())) {
				sucess = sLogin.getLogin(txtId.getText().trim(),txtPassword.getText().trim());
				
				//로그인 성공시 메인 페이지로 이동
				if(sucess) {
					try {
						studentId = txtId.getText();
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/trainee.fxml"));
						Parent mainView = (Parent)loader.load();
						Scene scene = new Scene(mainView);
						Stage mainMtage = new Stage();
						mainMtage.setTitle("미래 대학교 학사관리");
						mainMtage.setResizable(false);
						mainMtage.setScene(scene);
						Stage oldStage = (Stage)btnLogin.getScene().getWindow();
						oldStage.close();
						mainMtage.show();
					} catch (IOException e) {
						System.err.println("오류" + e);
					}
				}else {
					//아이디패스워드 확인하라는 창
					Alert alert;
					alert = new Alert(AlertType.WARNING);
					alert.setTitle("로그인 실패");
					alert.setHeaderText("아이디와 비밀번호 불일치");
					alert.setContentText("아이디와 비밀번호가 일치하지않습니다\n다시 제대로 입력하시오.");
					alert.setResizable(false);
					alert.showAndWait();
					txtId.clear();
					txtPassword.clear();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(txtId.getText().equals("")||txtPassword.getText().equals("")) {
			Alert alert;
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("로그인 실패");
			alert.setHeaderText("아이디와 비밀번호 미입력");
			alert.setContentText("아이디와 비밀번호중 입력하지 않은 항목이 있습니다 \n 다시 제대로 입력하시오");
			alert.setResizable(false);
			alert.showAndWait();
		}
	}
	public String managerLoginName() {
		LoginDAO ldao = new LoginDAO();
		String name = null;
		try {
			name = ldao.getLoginName(txtId.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}



	
	public String studentLoginName() {
		StudentDAO sdao = new StudentDAO();
		String name = null;
		try {
			name = sdao.getLoginName(txtId.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
}
