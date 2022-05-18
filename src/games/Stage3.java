package games;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Stage3 extends Stage {
	
	Stage s1;
	
	//ゲーム内変数
	Shooter sho;
	UFO ufo;
	ShooterMissile sm;
	Score score;
	InvaderPane ip;
	Label status;
	
	public Stage3(Stage s1) {
		this.s1 = s1;
		
		setTitle("InvaderGame");
		setWidth(Screen.WIDTH);
		setHeight(Screen.HEIGHT);
		setMaxWidth(Screen.WIDTH); //最大サイズ幅
		setMaxHeight(Screen.HEIGHT); //最大サイズ高さ
		setMinWidth(Screen.WIDTH); //最小サイズ幅
		setMinHeight(Screen.HEIGHT); //最小サイズ高さ
		setX(200); //画面が出る場所X
		setY(100); //画面が出る場所Y
		
		Screen.BGM1.stop();
		Screen.BGM2.loopPlay();
		//レイアウトPane使用
		Pane root = new Pane();
		// 各インスタンス生成
		ufo = new UFO();
		sm = new ShooterMissile();
		score = new Score();
		status = new Label("");
		status.setLayoutX(100);
		status.setFont(Screen.f48);
		status.setTextFill(Color.RED);
		status.setStyle("-fx-font-weight: bold;");
		sho = new Shooter(root,status);
		ip = new InvaderPane(status,sho);
		
		//オブサーバー登録
		sm.addObserver(ufo);
		sm.addObserver(ip);
		//背景の作成
		root.setStyle("-fx-background-color: #000000;");
//		Image img = new Image(Paths.get("images/black.png").toUri().toString());
//		BackgroundImage bimg = new BackgroundImage(img, null, null, null, null);
//		Background bg1 = new Background(bimg);
//		root.setBackground(bg1);
		//上下左右の余白
		root.setPadding(new Insets(10,10,10,10));
		//rootに部品を載せる
		root.getChildren().addAll(sho,ufo,sm,Score.scoreLabel,ip,status);
		//root載せたScene生成
		Scene sc = new Scene(root);
		setScene(sc);
		show();
		//キー入力を登録
		sc.setOnKeyPressed(event -> onKeyPressed(event));
		//×押したら終了
		this.setOnCloseRequest(event -> {
			System.out.println("終了します...");
			System.exit(0);
		});
	}
	
	//---GAME内キーEVENT----------------------------------------
	void onKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) { //左右キー
			sho.move(event.getCode()); //砲台move
		}
		if (event.getCode() == KeyCode.SPACE) { //スペースキー
			if (sm != null) sm.move(sho.getX());
		}
	}
}
