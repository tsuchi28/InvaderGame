package games;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class InvaderPane extends Pane implements Observer{
	
	Timeline timeline;
	int allowFlg = 0; //インベーダーの向き
	int inv_alive = 15; //生きているインベーダーの数
	Label status; //ゲームオーバー表示
	Random r = new Random();
	int rnum; //敵ミサイル発射ランダム変数
	Pane root; //mainのroot
	Shooter sho;
	
	//リストでインベーダーを使う
	ArrayList<Invader> inv = new ArrayList<Invader>();
	
	//コンストラクタ
	InvaderPane(Label status, Shooter sho){
		this.status = status;
		this.root = sho.root; //shooter引数にあるrootを参照
		this.sho = sho;
		
		//初期値セット
		this.setLayoutX(0);
		this.setLayoutY(Screen.IP_Y);
		this.setWidth(Screen.IP_W);
		this.setHeight(Screen.IP_H);
		
		//デバッグ用背景
//		setStyle("-fx-background-color: #00FF00;");
		
		//3段、5列
		int count = 0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<5; j++) {
				//個体インベーダーの生成、登録
				inv.add(count, new Invader(j*(Screen.I_W+Screen.IP_INTERVAL),i*(Screen.I_H+Screen.IP_INTERVAL)));
				root.getChildren().add(inv.get(count).getMissile()); //invaderをmainのrootに貼る
				getChildren().add(inv.get(count));
				//個体インベーダーの弾のオブザーバ登録
				inv.get(count).getMissile().addObserver(this.sho);
				//カウントアップ
				count++;
			}
		}
		
		//タイムラインの準備・実行
		timeline = new Timeline(new KeyFrame(Duration.millis(500), event->{run();}));
		timeline.setCycleCount(Timeline.INDEFINITE); //永久に
		timeline.play(); //スタート
	}
	
	//スレッドの実行
	public void run() {
		
		//壁についたら下へ移動
		if(getLayoutX()+Screen.IP_W+20 >= Screen.WIDTH && allowFlg == 0) {
			setLayoutY(getLayoutY() + 30);
			allowFlg = 1;
			return;
		}else if(getLayoutX() <= 0 && allowFlg == 1) {
			setLayoutY(getLayoutY() + 30);
			allowFlg = 0;
			return;
		}
		
		//横移動処理
		if(allowFlg == 0) { //右に進む
			setLayoutX(getLayoutX() + 10);
		}else { //左に進む
			setLayoutX(getLayoutX() - 10);
		}
		
		System.out.printf("【InvaderPane】(%d,%d)\n",(int)getLayoutX(),(int)getLayoutY());
		
		//ゲームオーバー
		if(getLayoutY()+Screen.IP_H >= 500) {
			status.setText("ゲームオーバー");
			timeline.stop();
		}
		
		//敵ミサイル invaderがひとりひとり持つ
		for(Invader i : inv) {
			rnum = r.nextInt(5);
			if (rnum == 0) i.getMissile().move(i.getX()+this.getLayoutX(), i.getY()+this.getLayoutY());
		}
	}
	
	public void update(Subject s) {
		int mx = (int)((ShooterMissile)s).getX();
		int my = (int)((ShooterMissile)s).getY();
		
		//当たったか判定
		for(Invader i : inv) {
			if(i.getLife() == true && Screen.hitCheck(mx,my,(int)i.getX() + (int)this.getLayoutX(),(int)i.getY() + (int)this.getLayoutY(),Screen.I_W,Screen.I_H) == true){
				System.out.println("【インベーダ】どっかーん");
				
				//SE破壊音再生
				Screen.SE4.resetPlay();
				
				//画像変更
				i.setIconBreak();
				
				//スコアアップ
				Score.scoreUp(20);
				
				//そのインベーダlifeがfalse
				i.setLife(false);
				inv_alive -= 1;
				
				//ミサイルストップ
				((ShooterMissile)s).stop();
				
				//ゲームクリア
				if(inv_alive == 0) {
					status.setText("ゲームクリア！");
					timeline.stop();
				}
				
			}
		}
	}
	
	//タイムライン終了
	public void timeline_stop() {
		timeline.stop();
	}
}
