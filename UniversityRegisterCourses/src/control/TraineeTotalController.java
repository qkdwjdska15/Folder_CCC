package control;

import java.awt.Button;
import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TraineeVO;

public class TraineeTotalController implements Initializable {
	@FXML
	ComboBox<String> cbx_searchList;
	@FXML
	TextField txtSearchWord;
	@FXML
	Button btnSearch;
	@FXML
	Label lblCount;
	@FXML
	TableView<TraineeVO> traineeDataList = FXCollections.observableArrayList();

	
	public void initialize(URL location, ResourceBundle resources) {
		try {
			cbx_searchList.setItems(FXCollections.observableArrayList("학번", "과목명", "학생이름"));
			
			//수강 테이블 뷰 칼럼이름 설정
			TableColumn colNo = new TableColumn("NO");
			colNo.setPrefWidth(50);
			colNo.setStyle("-fx-alignment:CENTER");
			colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
			
			TableColumn colSdNum= new TableColumn("학번");
			colNo.setPrefWidth(150);
			colNo.setStyle("-fx-alignment:CENTER");
			colNo.setCellValueFactory(new PropertyValueFactory<>("sd_num"));
			
			TableColumn colSdName = new TableColumn("학생이름");
			colNo.setPrefWidth(150);
			colNo.setStyle("-fx-alignment:CENTER");
			colNo.setCellValueFactory(new PropertyValueFactory<>("sd_name"));
			
			TableColumn colLNum = new TableColumn("과목명");
			colNo.setPrefWidth(150);
			colNo.setStyle("-fx-alignment:CENTER");
			colNo.setCellValueFactory(new PropertyValueFactory<>("l_num"));
			
			TableColumn colTSection = new TableColumn("과목 구분");
			colNo.setPrefWidth(150);
			colNo.setStyle("-fx-alignment:CENTER");
			colNo.setCellValueFactory(new PropertyValueFactory<>("t_section"));
			TableColumn colTDate = new TableColumn("등록일");
			colNo.setPrefWidth(250);
			colNo.setStyle("-fx-alignment:CENTER");
			colNo.setCellValueFactory(new PropertyValueFactory<>("t_date"));
			
			traineeTatolTableView.setItems(traineeDataList);
			traineeTatolTableView.getColomns().addAll(colNo,colSdNum,colSdNum,colSdName,colLNum,colTSection,colTDate);
			
			//수강전체목록
			traineeTotalList();
			
			btnSearch.setOnAction(event -> handlerBtnSearchAction(event));
			
		}

	}

}
