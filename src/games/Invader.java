package games;

import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Invader extends ImageView {
	
	private boolean life = true; //生死
	private InvaderMissile im;
	
	static Image img1 = new Image(Paths.get(Screen.I_imgPATH).toUri().toString());
	static Image img2 = new Image(Paths.get(Screen.I_imgPATH2).toUri().toString());
	
	Invader(int x, int y){
		//画像セット
		setImage(img1);
		
		//初期位置
		setX(x);
		setY(y);
		
		//ミサイルを持つ
		im = new InvaderMissile(this);
	}
	
	//壊れ画像に変更
	void setIconBreak(){
		setImage(img2);
	}
	
	//lifeセット(Invaderのlifeはboolean型)
	void setLife(Boolean l) { //生死をset
		life = l;
	}
	
	boolean getLife() { //生死をget
		return this.life;
	}
	
	//ミサイルゲッター
	InvaderMissile getMissile() {
		return im;
	}

}
