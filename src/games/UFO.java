package games;

import java.nio.file.Paths;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class UFO extends ImageView implements Observer {
	
	//通常UFO画像
	static Image img1 = new Image(Paths.get(Screen.U_imgPATH).toUri().toString());
	//破壊UFO画像
	static Image img2= new Image(Paths.get(Screen.U_imgPATH2).toUri().toString());
	
	private boolean moveFlg;
	int count = 0;
	Timeline timeline;
	
	Random r = new Random();
	int rnum = 0; //ランダム
	
	//コンストラクタ
	UFO(){
		super(img1);
		
		//初期位置設定
		this.setX(0 - Screen.U_W); //見えない位置
		this.setY(Screen.U_Y);
		
		//タイムライン生成
		timeline = new Timeline(
			new KeyFrame(Duration.millis(300), event -> {run();})
		);
		timeline.setCycleCount(Timeline.INDEFINITE); //永久に
		timeline.play(); //スタート
	}
	
	//-------------------
	//スレッド実行中(ここでは1つのスレッドでUFOを動かす)
	//-------------------
	public void run() {
		if(moveFlg == false) { //次の出現まで、しばし無視する(ランダム値にしよう)
			if(count < 10) {
				count++;
				return;
			}else {
				count = 0;
			}
			moveFlg = true;
			rnum = r.nextInt(5) + 1; //ランダム値1~20(test用に1~5)
			setX(-100*rnum); //見えない間の距離をランダムに
			setImage(img1); //正常画像に変更
		}
		setX(getX() + 5);
		System.out.printf("【UFO】(%d,%d)\n",(int)getX(),(int)getY());
		//右端に着いたら
		if(getX() >= Screen.RIGHT) {
			moveFlg = false;
		}
	}
	
	public void update(Subject s) {
		int mx = (int)((ShooterMissile)s).getX(); //ミサイルの座標
		int my = (int)((ShooterMissile)s).getY();
		
		if(Screen.hitCheck2(mx,my,(int)getX(),(int)getY(),Screen.U_W,Screen.U_H) == true) {
			System.out.println("当たった！");
			//SE破壊音再生
			Screen.SE4.resetPlay();
			
			//スコアアップ
			Score.scoreUp(100);
			
			setImage(img2); //壊れ画像にする
			((ShooterMissile)s).stop();
			moveFlg = false;
			
		}
	}
	
}
