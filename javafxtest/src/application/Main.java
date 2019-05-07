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
			HBox hbox = new HBox();	//HBox �����̳� ����
			hbox.setPadding(new Insets(10));//���� ���� ����
			hbox.setSpacing(10);//��Ʈ�� ���� ���� ���� ����
			
			TextField textField = new TextField(); //TextField ��Ʈ�� ����
			textField.setPrefWidth(200); //TextField�� �� ����
			
			Button button = new Button();//Button ��Ʈ�� ����
			button.setText("Ȯ��");//Button ���� ����
			
			ObservableList list = hbox.getChildren(); //HBox�� ObservableList ���
			list.add(textField);//TextField ��Ʈ�� ��ġ
			list.add(button);//Button�� ��Ʈ�� ��ġ
			
			Scene scene = new Scene(hbox);//��� ����
			
			primaryStage.setTitle("AppMain");//������ â ���� ����
			primaryStage.setScene(scene);//������ â�� ��� ����
			primaryStage.show();//������ â �����ֱ�
			
			/*VBox root = new VBox(); // Parent ���� ��ü�� Vbox ����
			root.setPrefWidth(350);// VBox�� �� ����
			root.setPrefHeight(150);// VBox�� ���� ����
			root.setAlignment(Pos.CENTER);// ��Ʈ���� �߾����� ����
			root.setSpacing(20);// ��Ʈ���� ���� ����
			
			Label label = new Label();// Label ��Ʈ�� ����
			label.setText("Hello, JavaFX");// text �Ӽ� ����
			label.setFont(new Font(50));// font �Ӽ� ����
			
			Button button = new Button();// Button ��Ʈ�� ����
			button.setText("Ȯ��");// text �Ӽ� ����
			button.setOnAction(event -> Platform.exit());// Ŭ�� �̺�Ʈ ó��
			
			root.getChildren().add(label);// VBox�� Label ��Ʈ�� �߰�
			root.getChildren().add(button);// VBox�� Button ��Ʈ�� �߰�
			
			Scene scene = new Scene(root);// VBox�� ��Ʈ �����̳ʷ� �ؼ� Scene ����
			primaryStage.setTitle("Stage and Scene");// �������� ���� ����
			primaryStage.setScene(scene);// ������ ��� ����
			primaryStage.show();// ������ �����ֱ�*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
