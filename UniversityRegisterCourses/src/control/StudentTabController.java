package control;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.StudentVO;
import model.SubjectVO;

public class StudentTabController implements Initializable {
	// 학생 등록 탭
	@FXML
	private ComboBox<SubjectVO> cbx_subjectName;
	@FXML
	private TextField txtsd_num;
	@FXML
	private TextField txtsd_name;
	@FXML
	private TextField txtsd_id;
	@FXML
	private PasswordField txtsd_passwd;
	@FXML
	private TextField txtsd_birthday;
	@FXML
	private TextField txtsd_phone;
	@FXML
	private TextField txtsd_address;
	@FXML
	private TextField txtsd_email;
	@FXML
	private Button btnIdCheck; // 아이디 체크 버튼
	@FXML
	private Button btnStudentInsert;// 학생 등록 버튼
	@FXML
	private Button btnStudentUpdate;// 학생 수정 버튼
	@FXML
	private Button btnStudentInit;// 학생 초기화 버튼
	@FXML
	private Button btnStudentTotalList;// 학생 전체 목록

	@FXML
	private TableView<StudentVO> studentTableView = new TableView<>();

	ObservableList<StudentVO> studentDataList = FXCollections.observableArrayList();
	ObservableList<StudentVO> selectStudent = null;// 학생등록 테이블에서 선택한 정보저장
	int selectedStudentIndex;// 학생등록 탭에서 선택한 학과 정보 인덱스 저장
	String studentNumber = "";
	private String selectSubjectNum;// 선택한 학과명의 학과코드

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			// 학생등록 초기화
			btnStudentInsert.setDisable(true);
			btnStudentUpdate.setDisable(true);
			btnStudentInit.setDisable(true);
			studentTableView.setEditable(false);

			// 학번 수정 금지
			txtsd_num.setEditable(false);

			// 학생 테이블 뷰 컬럼이름 설정
			@SuppressWarnings("rawtypes")
			TableColumn colStudentNo = new TableColumn("NO.");
			colStudentNo.setPrefWidth(30);
			colStudentNo.setStyle("-fx-alignment:CENTER");
			colStudentNo.setCellValueFactory(new PropertyValueFactory<>("no"));

			TableColumn colStudentNum = new TableColumn("학번");
			colStudentNum.setPrefWidth(70);
			colStudentNum.setStyle("-fx-alignment:CENTER");
			colStudentNum.setCellValueFactory(new PropertyValueFactory<>("sd_num"));

			TableColumn colStudentName = new TableColumn("이름");
			colStudentName.setPrefWidth(80);
			colStudentName.setStyle("-fx-alignment:CENTER");
			colStudentName.setCellValueFactory(new PropertyValueFactory<>("sd_name"));

			TableColumn colStudentId = new TableColumn("아이디");
			colStudentId.setPrefWidth(80);
			colStudentId.setStyle("-fx-alignment:CENTER");
			colStudentId.setCellValueFactory(new PropertyValueFactory<>("sd_id"));

			TableColumn colStudentPassword = new TableColumn("비밀번호");
			colStudentPassword.setPrefWidth(80);
			colStudentPassword.setStyle("-fx-alignment:CENTER");
			colStudentPassword.setCellValueFactory(new PropertyValueFactory<>("sd_passwd"));

			TableColumn colSubjectNum = new TableColumn("학과명");
			colSubjectNum.setPrefWidth(70);
			colSubjectNum.setStyle("-fx-alignment:CENTER");
			colSubjectNum.setCellValueFactory(new PropertyValueFactory<>("s_num"));

			TableColumn colStudentBirthday = new TableColumn("생년월일");
			colStudentBirthday.setPrefWidth(80);
			colStudentBirthday.setStyle("-fx-alignment:CENTER");
			colStudentBirthday.setCellValueFactory(new PropertyValueFactory<>("sd_birthday"));

			TableColumn colStudentPhone = new TableColumn("연락처");
			colStudentPhone.setPrefWidth(80);
			colStudentPhone.setStyle("-fx-alignment:CENTER");
			colStudentPhone.setCellValueFactory(new PropertyValueFactory<>("sd_phone"));

			TableColumn colStudentAddress = new TableColumn("주소");
			colStudentAddress.setPrefWidth(150);
			colStudentAddress.setStyle("-fx-alignment:CENTER");
			colStudentAddress.setCellValueFactory(new PropertyValueFactory<>("sd_address"));

			TableColumn colStudentEmail = new TableColumn("이메일");
			colStudentEmail.setPrefWidth(80);
			colStudentEmail.setStyle("-fx-alignment:CENTER");
			colStudentEmail.setCellValueFactory(new PropertyValueFactory<>("sd_email"));

			TableColumn colStudentDate = new TableColumn("등록일");
			colStudentDate.setPrefWidth(80);
			colStudentDate.setStyle("-fx-alignment:CENTER");
			colStudentDate.setCellValueFactory(new PropertyValueFactory<>("sd_date"));

			studentTableView.setItems(studentDataList);
			studentTableView.getColumns().addAll(colStudentNo, colStudentNum, colStudentName, colStudentId,
					colStudentPassword, colSubjectNum, colStudentBirthday, colStudentPhone, colStudentAddress,
					colStudentEmail, colStudentDate);

			// 학생 전체 목록
			studentTotalList();

			// 추가된 학과명 호출
			// addSubjectName();

			btnStudentInsert.setOnAction(event -> handlerBtnStudentInsertAction(event));
			cbx_subjectName.setOnAction(event -> handlerCbx_subjectNameActoin(event));
			btnIdCheck.setOnAction(event -> handlerBtnIdCheckAction(event));
			studentTableView.setOnMouseClicked(event -> handlerStudentTableViewAction(event));
			btnStudentUpdate.setOnAction(event -> handlerBtnStudentUpdateAction(event));
			btnStudentInit.setOnAction(event -> handlerBtnStudentInitAction(event));
			btnStudentTotalList.setOnAction(event -> handlerBtnStudentTotalListAction(event));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 학과명 호출 이벤트
	public void addSubjectName() throws Exception {
		StudentDAO sDao = new StudentDAO();
		ArrayList subjectNameList = new ArrayList<>();
		subjectNameList = sDao.subjectTotalList();
		// 학생 등록 탭 학과 번호 콤보 값 설정
		cbx_subjectName.setItems(FXCollections.observableArrayList(subjectNameList));

	}

	// 학생 등록 이벤트 핸들러
	public void handlerBtnStudentInsertAction(ActionEvent event) {
		try {
			studentDataList.removeAll(studentDataList);

			StudentVO svo = null;
			StudentDAO sdao = null;

			svo = new StudentVO(txtsd_num.getText().trim(), txtsd_name.getText().trim(), txtsd_id.getText().trim(),
					txtsd_passwd.getText().trim(), selectSubjectNum, txtsd_birthday.getText().trim(),
					txtsd_phone.getText().trim(), txtsd_address.getText().trim(), txtsd_email.getText().trim());
			sdao = new StudentDAO();
			sdao.getStudentRegiste(svo);

			if (sdao != null) {
				studentTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 입력");
				alert.setHeaderText(txtsd_name.getText() + " 학생이 성공적으로 추가되었습니다.");
				alert.setContentText("다음 학생을 입력하세요");
				alert.showAndWait();

				txtsd_num.clear();
				txtsd_name.clear();
				txtsd_id.clear();
				txtsd_passwd.clear();
				selectSubjectNum = "";
				txtsd_birthday.clear();
				txtsd_phone.clear();
				txtsd_address.clear();
				txtsd_email.clear();
				txtsd_name.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("학과 정보 입력");
			alert.setHeaderText("학과 정보를 정확히 입력하시오.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait();
		}
	}

	// 학생 아이디 중복 체크
	public void handlerBtnIdCheckAction(ActionEvent event) {
		btnStudentInsert.setDisable(false);
		btnIdCheck.setDisable(true);

		StudentDAO sDao = null;

		String searchId = "";
		boolean searchResult = true;

		try {
			searchId = txtsd_id.getText().trim();
			sDao = new StudentDAO();
			searchResult = (boolean) sDao.getStudentIdOverlap(searchId);

			if (!searchResult && !searchId.equals("")) {
				txtsd_id.setDisable(true);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + " 를 사용할 수 있습니다.");
				alert.setContentText("패스워드를 입력하세요.");
				alert.showAndWait();

				btnStudentInsert.setDisable(false);
				btnIdCheck.setDisable(true);
			} else if (searchId.equals("")) {
				btnStudentInsert.setDisable(true); // 등록 버튼 비활성화
				btnIdCheck.setDisable(false); // 중복체크 버튼 활성화

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText("아이디를 입력하세요");
				alert.setContentText("등록할 아이디를 입력하세요");
				alert.showAndWait();
			} else { // 중복된 값이 있을 경우
				btnStudentInsert.setDisable(true); // 등록 버튼 비활성화
				btnIdCheck.setDisable(false); // 중복체크 버튼 활성화
				txtsd_id.clear();

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + "를 사용할 수 없습니다");
				alert.setContentText("다른 아이디를 사용하세요");
				alert.showAndWait();

				txtsd_id.requestFocus(); // 아이디 텍스트 필드 포커스
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("아이디 중복 검사 오류");
			alert.setHeaderText("아이디 중복 검사에 오류가 발생했습니다");
			alert.setContentText("다시 하세요");
			alert.showAndWait();
		}

	}

	// 학생 등록 탭의 학과 선택 이벤트 핸들러
	public void handlerCbx_subjectNameActoin(ActionEvent event) {
		SubjectDAO sudao = new SubjectDAO();
		StudentDAO sdao = new StudentDAO();
		String serialNumber = ""; // 일련번호
		String sdYear = "";

		try {
			selectSubjectNum = sudao.getSubjectNum(cbx_subjectName.getSelectionModel().getSelectedItem() + "");

			// 학번은 8자리로 구성(연도2 + 학과2 + 일련4 ex.06010001)
			SimpleDateFormat sdf = new SimpleDateFormat("yy");
			sdYear = sdf.format(new Date());

			serialNumber = sdao.getStudentCount(selectSubjectNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		studentNumber = sdYear + selectSubjectNum + serialNumber; // 모든 값을 더해 학번에 저장
		txtsd_num.setText(studentNumber); // 학번을 텍스트필드에 설정한다

	}

	// 학생 전체 목록 이벤트
	public void studentTotalList() throws Exception {
		studentDataList.removeAll(studentDataList);
		StudentDAO sDao = new StudentDAO();
		StudentVO sVo = null;
		ArrayList<String> title;
		ArrayList<StudentVO> list;

		title = sDao.getStudentColumnName();
		int columnCount = title.size();

		list = sDao.getStudentTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);
			studentDataList.add(sVo);
		}
		// 추가된 학과명 호출
		addSubjectName();

	}

	// 학생 테이블 뷰 더블 클릭 이벤트 핸들러
	public void handlerStudentTableViewAction(MouseEvent event) {
		if (event.getClickCount() == 2) { // 더블 클릭 하면

			try {
				selectStudent = studentTableView.getSelectionModel().getSelectedItems();
				selectedStudentIndex = selectStudent.get(0).getNo();
				String selectedSd_num = selectStudent.get(0).getSd_num();
				String selectedSd_name = selectStudent.get(0).getSd_name();
				String selectedSd_id = selectStudent.get(0).getSd_id();
				String selectedSd_passwd = selectStudent.get(0).getSd_passwd();
				String selectedSd_birthday = selectStudent.get(0).getSd_birthday();
				String selectedSd_phone = selectStudent.get(0).getSd_phone();
				String selectedSd_address = selectStudent.get(0).getSd_address();
				String selectedSd_email = selectStudent.get(0).getSd_email();

				txtsd_num.setText(selectedSd_num);
				txtsd_name.setText(selectedSd_name);
				txtsd_id.setText(selectedSd_id);
				txtsd_passwd.setText(selectedSd_passwd);
				txtsd_birthday.setText(selectedSd_birthday);
				txtsd_phone.setText(selectedSd_phone);
				txtsd_address.setText(selectedSd_address);
				txtsd_email.setText(selectedSd_email);

				txtsd_num.setEditable(false); // 학번 텍스트필드 수정 불가
				txtsd_name.setEditable(false); // 이름 텍스트필드 수정 불가
				txtsd_id.setEditable(false); // 아이디 텍스트필드 수정 불가

				btnIdCheck.setDisable(true); // 중복 체크 버튼 비활성화
				cbx_subjectName.setDisable(true); // 과목 콤보박스 비활성화
				btnStudentUpdate.setDisable(false); // 학생 정보 수정 버튼 활성화
				btnStudentInit.setDisable(false); // 초기화 버튼 활성화
				btnStudentInsert.setDisable(true); // 학생 추가 버튼 비활성화

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// 학생 전체 목록
	public void handlerBtnStudentTotalListAction(ActionEvent event) {
		try {
			studentDataList.removeAll(studentDataList);
			studentTotalList();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 학생 초기화 이벤트
	public void handlerBtnStudentInitAction(ActionEvent event) {

		try {
			studentDataList.removeAll(studentDataList);
			studentTotalList(); // 학생 전체 메소드 호출

			// 텍스트 필드값 모두 초기화
			txtsd_num.clear();
			txtsd_name.clear();
			txtsd_id.clear();
			txtsd_passwd.clear();
			txtsd_birthday.clear();
			txtsd_phone.clear();
			txtsd_address.clear();
			txtsd_email.clear();

			txtsd_num.setEditable(true); // 학과번호 수정 가능
			txtsd_name.setEditable(true); // 이름 수정 가능
			txtsd_id.setEditable(true); // id 수정 가능

			btnIdCheck.setDisable(false); // 아이디 중복체크 활성화
			cbx_subjectName.setDisable(false); // 학과명 콤보박스 활성화
			btnStudentUpdate.setDisable(true); // 수정 버튼 비활성화
			btnStudentInit.setDisable(true); // 초기화 버튼 비활성화
			btnStudentInsert.setDefaultButton(true); // 추가 버튼 비활성화
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 학생 정보 수정 이벤트
	public void handlerBtnStudentUpdateAction(ActionEvent event) {
		try {

			boolean sucess;

			StudentDAO sdao = new StudentDAO();
			sucess = sdao.getStudentUpdate(selectedStudentIndex, txtsd_passwd.getText().trim(),
					txtsd_birthday.getText().trim(), txtsd_phone.getText().trim(), txtsd_address.getText().trim(),
					txtsd_email.getText().trim());

			if (sucess) {
				studentDataList.removeAll(studentDataList);
				studentTotalList();

				txtsd_num.clear();
				txtsd_name.clear();
				txtsd_id.clear();
				txtsd_passwd.clear();
				txtsd_birthday.clear();
				txtsd_phone.clear();
				txtsd_address.clear();
				txtsd_email.clear();

				txtsd_num.setEditable(true); // 학과번호 수정 가능
				txtsd_name.setEditable(true); // 이름 수정 가능
				txtsd_id.setEditable(true); // id 수정 가능

				btnIdCheck.setDisable(false); // 아이디 중복체크 활성화
				cbx_subjectName.setDisable(false); // 학과명 콤보박스 활성화
				btnStudentUpdate.setDisable(true); // 수정 버튼 비활성화
				btnStudentInit.setDisable(true); // 초기화 버튼 비활성화
				btnStudentInsert.setDefaultButton(true); // 추가 버튼 비활성화
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
