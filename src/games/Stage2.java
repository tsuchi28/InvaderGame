package games;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Stage2 extends Stage {
	
	Stage s1;
	
	//スライダー(音量調節バー)の宣言
	Slider sbBGM = new Slider(); //BGMの音量(0~100)
	Slider sbSE = new Slider(); //SEの音量(0~100)
	
	public Stage2(Stage s1) {
		this.s1 = s1;
		
		setTitle("InvaderGame - Setting");
		setWidth(Screen.WIDTH);
		setHeight(Screen.HEIGHT);
		setMaxWidth(Screen.WIDTH); //最大サイズ幅
		setMaxHeight(Screen.HEIGHT); //最大サイズ高さ
		setMinWidth(Screen.WIDTH); //最小サイズ幅
		setMinHeight(Screen.HEIGHT); //最小サイズ高さ
		setX(200); //画面が出る場所X
		setY(100); //画面が出る場所Y
		
		//タイトル
		Label lbl = new Label("設定画面");
		lbl.setTextFill(Color.RED); //ラベルの色
		lbl.setFont(Screen.f48); //フォントをセット
		
		//BGM調節バー
		HBox volumeBGM = new HBox();
		Label lblBGM = new Label("BGM");
		sbBGM.setMin(0); //最小値
		sbBGM.setMax(100); //最大値
		sbBGM.setValue(70); //デフォルト値
		Label lblBGMvalue = new Label("70"); //現在値を示すラベル
		sbBGM.setOnMouseDragged(event -> sliderEvent(sbBGM,lblBGMvalue));
		volumeBGM.getChildren().addAll(lblBGM,sbBGM,lblBGMvalue);
		
		//SE調節バー
		HBox volumeSE = new HBox();
		Label lblSE = new Label("SE");
		sbSE.setMin(0); //最小値
		sbSE.setMax(100); //最大値
		sbSE.setValue(70); // デフォルト値
		Label lblSEvalue = new Label("70"); //現在値を示すラベル
		sbSE.setOnMouseDragged(event -> sliderEvent(sbSE,lblSEvalue));
		volumeSE.getChildren().addAll(lblSE,sbSE,lblSEvalue);
		
		//戻るボタン
		Button btnBack = new Button("メニューへ戻る");
		btnBack.setPrefWidth(300); //ボタンの幅
		btnBack.setOnAction(event -> btnScreenTransitionEvent());
		
		//root
		VBox root = new VBox();
	    root.getChildren().addAll(lbl,volumeBGM,volumeSE,btnBack);
	    
	    Scene sc = new Scene(root);
	    setScene(sc);
	    show();
	}
	
	//---メイン画面遷移EVENT--------------------------------------
	void btnScreenTransitionEvent() { //ボタンが押された時に画面を遷移する処理
		s1.show();
	}
	
	//---スライダーEVENT----------------------------------------
	void sliderEvent(Slider s,Label l) { //スライダーが動かされた時にラベルを動的に変更する処理
		l.setText(String.valueOf((int)s.getValue()));
	}
}
