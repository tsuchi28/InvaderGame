package games;

import java.nio.file.Paths;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class InvaderMissile extends Subject {
	
	private boolean moveFlg = false;
	static Image img = new Image(Paths.get(Screen.IM_imgPATH).toUri().toString());
	Timeline timeline;
	Invader inv;
	
	//コンストラクタ
	InvaderMissile(Invader inv){
		super(img);
		this.inv = inv;
		
		//位置設定
		this.setX(Screen.IM_X);
		this.setY(Screen.IM_Y);
		
		//タイムライン（スレッド）
		timeline = new Timeline(new KeyFrame(Duration.millis(300), event -> {run();}));
		timeline.setCycleCount(Timeline.INDEFINITE); //永久に
		timeline.play();
	}
	
	//-------------------
	//スレッド実行中(ミサイル移動)
	//-------------------
	public void run() {
		if(this.getY() >= Screen.HEIGHT) { //画面外に消えたら
			setX(-100);
			moveFlg = false;
		}
		if (moveFlg) {
//			System.out.printf("【敵ミサイル位置】(%d,%d)\n", (int)getX(), (int)getY());
			setY(getY() + Screen.IM_SPEED);
			
			//観測者に通知(Shooterに)
			notifyObservers();
		}
	}
	
	//-------------------
	//ミサイル発射
	//-------------------
	void move(double ix,double iy) { 
		if(moveFlg == false && inv.getLife() == true) { //invが生きていたら
			setX(ix + Screen.I_W / 2); //実際にそのポジションへ
			setY(iy);
			moveFlg = true; //動くフラグON
			
//			System.out.printf("【発射時敵ミサイル位置】(%d,%d)\n", (int)getX(), (int)getY());
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
