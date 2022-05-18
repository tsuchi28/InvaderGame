package games;

import java.nio.file.Paths;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Shooter extends ImageView implements Observer {
	
	Pane root; //mainのroot
	
	//ライフ
	private int life = Screen.S_LIFE;
	Label zanki; //残機ラベル
	private int lifeFlg;
	
	//画像
	static private Image img = new Image(Paths.get(Screen.S_imgPATH).toUri().toString());
	static private Image img2 = new Image(Paths.get(Screen.S_imgPATH2).toUri().toString());
	
	Label status;
	
	//-------------------
	//コンストラクタ
	//-------------------
	Shooter(Pane root, Label status){
		super(img);
		
		this.root = root;
		this.status = status;
		
		lifeFlg = 1; //生存フラグの設定
		zanki = new Label(String.valueOf(this.life)); //残機ラベル
		zanki.setFont(new Font(36));
		zanki.setTextFill(Color.GREEN);
		zanki.setStyle("-fx-font-weight: bold;");
		root.getChildren().add(zanki);
		
		//初期位置設定
		this.setX(0);
		this.setY(Screen.S_Y);
	}
	
	//-------------------
	//砲台移動
	//-------------------
	void move(KeyCode kc) {
		if(kc == KeyCode.RIGHT && lifeFlg == 1) { //→を押した
			if(this.getX()+Screen.S_W < Screen.WIDTH) { //右端でなかったら
				setX(getX() + 5);
				if(this.getX()+Screen.S_W > Screen.WIDTH) { //移動後右端を超えたら右端へ
					this.setX(Screen.WIDTH);
				}
			}
		}
		if(kc == KeyCode.LEFT && lifeFlg == 1) { //←を押した
			if(this.getX() > 0) {
				setX(getX() - 5);
				if(this.getX() < 0) {
					this.setX(0);
				}
			}
		}
		//System.out.printf("【砲台位置】(%d,%d)\n", (int)getX(), (int)getY());
	}
	
	public void update(Subject s) {
		int mx = (int)((InvaderMissile)s).getX();
		int my = (int)((InvaderMissile)s).getY();
		
		//当たったか判定
		if(this.life > 0 && Screen.hitCheck(mx,my,(int)this.getX(),(int)this.getY(),Screen.S_W,Screen.S_H) == true){
			System.out.println("【Shooter】どっかーん");
			
			//ミサイルストップ
			((InvaderMissile)s).stop();
			
			//lifeが減少
			this.life -= 1;
			zanki.setText(String.valueOf(this.life));
			
			//ゲームオーバー(残機ゼロ)
			if(life == 0) {
				//SE破壊音再生
				Screen.SE3.resetPlay();
				//ゲームオーバー
				this.status.setText("ゲームオーバー");
				//生存フラグ0
				lifeFlg = 0;
				//画像変更
				this.setImage(img2);
				System.out.println("GameOver:残機ゼロ");
				//BGM変更
				Screen.BGM2.stop();
				Screen.BGM3.loopPlay();
			}else {
				//SEダメージ音再生
				Screen.SE2.resetPlay();
			}
		}
	}
	
}
