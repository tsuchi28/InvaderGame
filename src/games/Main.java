package games;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
	
	Stage s1;
	Stage2 s2 = null;
	Stage3 s3 = null;
	
	//---スタート---------------------------------------------
	public void start(Stage stage) {
		s1 = stage;
		
		stage.setTitle("InvaderGame - Menu");
		stage.setWidth(Screen.WIDTH);
		stage.setHeight(Screen.HEIGHT);
		stage.setMaxWidth(Screen.WIDTH); //最大サイズ幅
		stage.setMaxHeight(Screen.HEIGHT); //最大サイズ高さ
		stage.setMinWidth(Screen.WIDTH); //最小サイズ幅
		stage.setMinHeight(Screen.HEIGHT); //最小サイズ高さ
		stage.setX(200); //画面が出る場所X
		stage.setY(100); //画面が出る場所Y
		
		//BGM
		Screen.BGM1.loopPlay();
		
		//背景
		Image img = new Image(Paths.get(Screen.BG_PATH1).toUri().toString());
		BackgroundImage bimg = new BackgroundImage(img, null, null, null, null);
		Background bg1 = new Background(bimg);
		
		//タイトル(上)
		Label lblTitle = new Label("InvaderGame");
		lblTitle.setTextFill(Color.RED); //ラベルの色
		lblTitle.setFont(Screen.f48);
		
		//ボタン群(中央)
		Button btnStart = new Button("スタート");
		btnStart.setPrefWidth(300); //ボタンの幅
		btnStart.setOnAction(event -> btnScreenTransitionEvent3());
		
		Button btnSetting = new Button("設定");
		btnSetting.setPrefWidth(300); //ボタンの幅
		btnSetting.setOnAction(event -> btnScreenTransitionEvent2());
		
		//root
		VBox root = new VBox();
		root.getChildren().addAll(lblTitle,btnStart,btnSetting);
		root.setBackground(bg1);
		root.setPadding(new Insets(100,0,0,350));
		
		Scene sc = new Scene(root);
		
		stage.setScene(sc);
		stage.show();
		
		//×押したら終了
		stage.setOnCloseRequest(event -> {
			System.out.println("終了します...");
			System.exit(0);
		});
	}
	
	//---メイン-----------------------------------------------
	public static void main(String[] args) {
		launch();
	}
	
	//---設定画面遷移EVENT--------------------------------------
	void btnScreenTransitionEvent2() { //ボタンが押された時に画面を遷移する処理
		if(s2 == null) {
			s2 = new Stage2(s1);
		}else {
			s2.show();
		}
	}
	
	//---GAME画面遷移EVENT--------------------------------------
	void btnScreenTransitionEvent3() { //ボタンが押された時に画面を遷移する処理
		if(s3 == null) {
			s3 = new Stage3(s1);
		}else {
			s3.show();
		}
	}
	
}
