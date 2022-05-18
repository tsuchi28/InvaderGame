package games;

import java.nio.file.Paths;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class ShooterMissile extends Subject {
	
	private boolean moveFlg = false;
	static Image img = new Image(Paths.get(Screen.SM_imgPATH).toUri().toString());
	Timeline timeline;
	
	//コンストラクタ
	ShooterMissile(){
		super(img);
		
		//位置設定
		this.setX(Screen.SM_X);
		this.setY(Screen.SM_Y);
		
		//タイムライン（スレッド）
		timeline = new Timeline(new KeyFrame(Duration.millis(300), event -> {run();}));
		timeline.setCycleCount(Timeline.INDEFINITE); //永久に
		timeline.play();
	}
	
	//-------------------
	//スレッド実行中(ミサイル移動)
	//-------------------
	public void run() {
		if(this.getY() <= 0) { //画面外に消えたら
			setX(-100);
			moveFlg = false;
		}
		if (moveFlg) {
			System.out.printf("【ミサイル位置】(%d,%d)\n", (int)getX(), (int)getY());
			setY(getY() - Screen.SM_SPEED);
			
			//観測者に通知(UFOなどに)
			notifyObservers();
		}
	}
	
	//-------------------
	//ミサイル発射
	//-------------------
	void move(double sx) {
		if(moveFlg == false) {
			setX(sx + Screen.S_W / 2); //実際にそのポジションへ
			setY((double)Screen.SM_Y);
			moveFlg = true; //動くフラグON
			Screen.SE1.resetPlay(); //SE発射音再生
			
//			System.out.printf("【発射時ミサイル位置】(%d,%d)\n", (int)getX(), (int)getY());
		}
	}
	
	//-------------------
	//ミサイル消える
	//-------------------
	void stop() {
		moveFlg = false;
		setX(-100);
	}

}
