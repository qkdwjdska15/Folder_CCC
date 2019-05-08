package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.JoinVO;

public class JoinController implements Initializable {
	@FXML
	private TextField txtId;// 아이디 텍스트필드
	@FXML
	private PasswordField txtPassword;// 비밀번호 패스워드필드
	@FXML
	private PasswordField txtPasswordRepeat;// 비밀번호확인 패스워드필드
	@FXML
	private TextField txtName;// 관리자이름 텍스트필드
	@FXML
	private Button btnCancel;// 닫기 버튼
	@FXML
	private Button btnJoin;// 등록 버튼
	@FXML
	private Button btnOverlap;// 아이디 중복 확인 버튼

	JoinVO joinVO = new JoinVO();// model.join인스턴스화 --> VO

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnJoin.setDisable(true);// 등록 버튼 비활성화
		txtPassword.setEditable(false);// 비밀번호 입력 필드 수정 불가
		txtPasswordRepeat.setEditable(false);// 비밀번호 확인 입력 필드 수정 불가

		btnOverlap.setOnAction(event -> handlerBtnOverlapAction(event));// 아이디 중복 검사 버튼 이벤트 설정
		btnJoin.setOnAction(event -> handlerBtnJoinAction(event));// 관리자 등록 버튼 이벤트 설정
		btnCancel.setOnAction(event -> handlerBtnCancelAction(event));// 등록창 닫기 버튼 이벤트 설정

	}

	// 아이디 중복 검사 버튼 이벤트
	public void handlerBtnOverlapAction(ActionEvent event) {
		btnJoin.setDisable(false);// 등록 버튼 활성화
		btnOverlap.setDisable(true);// 아이디 중복 확인 버튼 비활성화

		// JoinDAO 인스턴스화
		JoinDAO jDao = null;
		// 검색할 아이디 변수
		String searchId = "";
		// 검색 결과 판단변수 초기값 true
		boolean searchResult = true;

		try {
			// 검색창에 입력된 아이디를 앞뒤 공백제거후 가져와 아이디변수에 넣는다.
			searchId = txtId.getText().trim();
			// jDao 인스턴스 생성
			jDao = new JoinDAO();
			// JoinDAO클래스의 IdOverlap메소드에 검색아이디를 넣고 boolean을 반환 받는다.
			searchResult = (boolean) jDao.getIdOverlap(searchId);

			// 만약 검색결과나 검색아이디가 빈게 아닐경우
			if (!searchResult && !searchId.equals("")) {

				txtId.setDisable(true);// 아이디 입력칸 비활성화

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + "를 사용할 수 있습니다.");
				alert.setContentText("패스워드를 입력하세요.");
				alert.showAndWait();

				// 버튼 설정
				btnJoin.setDisable(false);// 등록버튼 활성화
				btnOverlap.setDisable(true);// 아이디 중복확인버튼 비활성화

				// 텍스트 필드 설정
				txtPassword.setEditable(true);// 비밀번호 입력칸 비활성화
				txtPasswordRepeat.setEditable(true);// 비밀번호 확인 입력칸 비활성화

				// 포커스이동
				txtPassword.requestFocus();// 비밀번호 입력칸으로 이동

				// 검색 아이디가 비워있을경우
			} else if (searchId.equals("")) {
				btnJoin.setDisable(true);// 등록버튼 비활성화
				btnOverlap.setDisable(false);// 아이디 중복 확인버튼 활성화
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("아이디 중복 검색");
				alert.setHeaderText("아이디를 입력하시오.");
				alert.setContentText("등록할 아이디를 입력하세요!");
				alert.showAndWait();

			} else {
				btnJoin.setDisable(true);// 등록 버튼 비활성화
				btnOverlap.setDisable(false);// 아이디 중복 확인버튼 활성화
				txtId.clear();// 아이디 입력칸 초기화
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + "를 사용할 수 없습니다.");
				alert.setContentText("아이디를 다른것으로 입력하세요");
				alert.showAndWait();

				// 포커스 이동
				txtId.requestFocus();// 아이디 입력칸으로 이동
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("아이디 중복 검사 오류");
			alert.setHeaderText("아이디 중복 검사에 오류가 발생하였습니다.");
			alert.setContentText("다시시도해 주세요.");
			alert.showAndWait();
		}
	}

	// 등록창 닫기 버튼 이벤트
	public void handlerBtnCancelAction(ActionEvent event) {
		try {
			// 레이아웃 불러오기
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));

			// 부모창을 login.fxml로 로드
			Parent mainView = (Parent) loader.load();

			// Scene객체생성
			Scene scene = new Scene(mainView);

			// Stage 객체 생성
			Stage mainMtage = new Stage();
			mainMtage.setTitle("관리자 로그인");// 타이틀 설정
			mainMtage.setScene(scene);// 씬 설정

			// 새 스테이지(탭) 추가
			Stage oldStage = (Stage) btnJoin.getScene().getWindow();

			// 탭 창 닫음
			oldStage.close();

			// 로그인 창 열기
			mainMtage.show();

		} catch (IOException e) {
			System.err.println("오류" + e);
		}
	}

	// 관리자 등록
	public void handlerBtnJoinAction(ActionEvent event) {

		JoinVO jvo = null;// JoinVO 인스턴스화
		JoinDAO jdao = null; // JoinDAO 인스턴스화
		boolean joinSucess = false;// 등록 결과 판단변수 초기값false

		// 패스워드 확인
		// 비밀번호 입력칸에 입력한 내용과 비밀번호 확인 입력칸에 입력한 내용이 일치하고
		// 관리자 이름이 빈게아닐경우
		if (txtPassword.getText().trim().equals(txtPasswordRepeat.getText().trim())
				&& !txtName.getText().trim().equals("")) {

			// JoinVO 아이디입력,비밀번호입력,관리자이름입력 인스턴스생성
			jvo = new JoinVO(txtId.getText().trim(), txtPassword.getText().trim(), txtName.getText().trim());

			jdao = new JoinDAO();// JoinDAO 인스턴스 생성
			
			try {
				//JoinDAO의 getManagerRegiste메소드를 불러와 jvo인스턴스에 적용한것을
				//joinSucess에 값에 저장
				joinSucess = jdao.getManagerRegiste(jvo);
				if(joinSucess) {
					handlerBtnCancelAction(event);//닫기 버튼 이벤트 호출
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else {
			txtPassword.clear();//비밀번호 입력 칸 초기화
			txtPasswordRepeat.clear();//비밀번호 확인 칸 초기화
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("패스워드 , 이름 확인");
			alert.setHeaderText("패스워드,이름 확인 검사에 오류가 발생하였습니다.");
			alert.setContentText("패스워드와 이름을 다시 입력하세요.");
			alert.showAndWait();
		}

	}

}
