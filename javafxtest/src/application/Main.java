package application;

import java.util.Observable;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
//import javafx.application.Platform;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			HBox hbox = new HBox();	//HBox 컨테이너 생성
			hbox.setPadding(new Insets(10));//안쪽 여백 설정
			hbox.setSpacing(10);//컨트롤 간의 수평 간격 설정
			
			TextField textField = new TextField(); //TextField 컨트롤 생성
			textField.setPrefWidth(200); //TextField의 폭 설정
			
			Button button = new Button();//Button 컨트롤 생성
			button.setText("확인");//Button 글자 설정
			
			ObservableList list = hbox.getChildren(); //HBox의 ObservableList 얻기
			list.add(textField);//TextField 컨트롤 배치
			list.add(button);//Button의 컨트롤 배치
			
			Scene scene = new Scene(hbox);//장면 생성
			
			primaryStage.setTitle("AppMain");//윈도우 창 제목 설정
			primaryStage.setScene(scene);//윈도우 창에 장면 설정
			primaryStage.show();//윈도우 창 보여주기
			
			/*VBox root = new VBox(); // Parent 하위 객체인 Vbox 생성
			root.setPrefWidth(350);// VBox의 폭 설정
			root.setPrefHeight(150);// VBox의 높이 설정
			root.setAlignment(Pos.CENTER);// 컨트롤을 중앙으로 정렬
			root.setSpacing(20);// 컨트롤의 수직 간격
			
			Label label = new Label();// Label 컨트롤 생성
			label.setText("Hello, JavaFX");// text 속성 설정
			label.setFont(new Font(50));// font 속성 설정
			
			Button button = new Button();// Button 컨트롤 생성
			button.setText("확인");// text 속성 설정
			button.setOnAction(event -> Platform.exit());// 클릭 이벤트 처리
			
			root.getChildren().add(label);// VBox에 Label 컨트롤 추가
			root.getChildren().add(button);// VBox에 Button 컨트롤 추가
			
			Scene scene = new Scene(root);// VBox에 루트 컨테이너로 해서 Scene 생성
			primaryStage.setTitle("Stage and Scene");// 윈도우의 제목 설정
			primaryStage.setScene(scene);// 윈도웨 장면 설정
			primaryStage.show();// 윈도우 보여주기*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
