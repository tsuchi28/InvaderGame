package games;

import javafx.scene.text.Font;

public class Screen {
	
	//画面サイズ
	public static final int WIDTH = 1000; //幅
	public static final int HEIGHT = 600; //高さ
	public static final int RIGHT = 1000; //右
	public static final int LEFT = 0; //左
	public static final int TOP = 50; //天井
	public static final int BOTTOM = 600; //地面
	
	//よく使うフォント
	public static final Font f48 = new Font(48); //タイトル
	
	//背景画像ファイルパス
	public static final String BG_PATH1 = "images/bg1.png";
	
	//wavファイルパス
	public static final String BGM_PATH1 = "music/bgm_jodo.wav";
	public static final String BGM_PATH2 = "music/bgm_maoudamashii_8bit18.wav";
	public static final String BGM_PATH3 = "music/bgm_maoudamashii_8bit20.wav";
	public static final String SE_PATH1 = "music/se_shot.wav";
	public static final String SE_PATH2 = "music/se_hit.wav";
	public static final String SE_PATH3 = "music/se_maoudamashii_explosion03.wav";
	public static final String SE_PATH4 = "music/se_maoudamashii_explosion06.wav";
//	public static final String SE_PATH5 = "music/se_.wav";
//	public static final String SE_PATH6 = "music/se_.wav";
//	public static final String SE_PATH7 = "music/se_.wav";
//	public static final String SE_PATH8 = "music/se_.wav";
//	public static final String SE_PATH9 = "music/se_.wav";
//	public static final String SE_PATH10 = "music/se_.wav";
	
	//BGM
	public static final PlayClip BGM1 = new PlayClip(BGM_PATH1); //メインメニューBGM
	public static final PlayClip BGM2 = new PlayClip(BGM_PATH2); //ゲームBGM
	public static final PlayClip BGM3 = new PlayClip(BGM_PATH3); //ゲームオーバーBGM
	
	//SE
	public static final PlayClip SE1 = new PlayClip(SE_PATH1); //ミサイル発射音
	public static final PlayClip SE2 = new PlayClip(SE_PATH2); //シューターダメージ音
	public static final PlayClip SE3 = new PlayClip(SE_PATH3); //シューター破壊音
	public static final PlayClip SE4 = new PlayClip(SE_PATH4); //インベーダ破壊音
//	public static final PlayClip SE5 = new PlayClip(SE_PATH5);
//	public static final PlayClip SE6 = new PlayClip(SE_PATH6);
//	public static final PlayClip SE7 = new PlayClip(SE_PATH7);
//	public static final PlayClip SE8 = new PlayClip(SE_PATH8);
//	public static final PlayClip SE9 = new PlayClip(SE_PATH9);
//	public static final PlayClip SE10 = new PlayClip(SE_PATH10);
	
	//Score
	public static final int SCORE_X = 900; //X座標
	public static final int SCORE_Y = 0; //Y座標
	
	//Shooter
	public static final int S_X = 0; //X座標
	public static final int S_Y = 510; //Y座標
	public static final String S_imgPATH = "images/shooter.jpeg"; //画像ファイルパス
	public static final String S_imgPATH2 = "images/shooterB.png"; //壊れ画像ファイルパス
	public static final int S_W = 30; //画像幅
	public static final int S_H = 20; //画像高さ
	public static final int S_LIFE = 3; //残機
	
	//ShooterMissile
	public static final int SM_X = -100; //X座標
	public static final int SM_Y = 500; //Y座標
	public static final String SM_imgPATH = "images/Missile.png"; //画像ファイルパス
	public static final int SM_W = 3; //画像幅
	public static final int SM_H = 10; //画像高さ
	public static final int SM_SPEED = 20; //味方の弾の速さ
	
	//InvaderMissile
	public static final int IM_X = -100; //X座標
	public static final int IM_Y = 500; //Y座標
	public static final String IM_imgPATH = "images/Missile.png"; //画像ファイルパス
	public static final int IM_W = 3; //画像幅
	public static final int IM_H = 10; //画像高さ
	public static final int IM_SPEED = 10; //敵の弾の速さ
	
	//Invader
	public static final int I_X = 0; //X座標
	public static final int I_Y = 0; //Y座標
	public static final String I_imgPATH = "images/invarder1.png"; //画像ファイルパス
	public static final String I_imgPATH2 = "images/invaderToumei.png"; //壊れ画像ファイルパス
	public static final int I_W = 25; //画像幅
	public static final int I_H = 20; //画像高さ
	
	//InvaderPane
	public static final int IP_X = 0; //X座標
	public static final int IP_Y = 50; //Y座標
	public static final int IP_W = 245; //幅
	public static final int IP_H = 120; //高さ
	public static final int IP_INTERVAL = 30; //インベーダの間隔
	
	//Wall
	public static final int W_X = 0; //X座標
	public static final int W_Y = 0; //Y座標
	public static final String W_imgPATH = "images/wall.jpeg"; //画像ファイルパス
	public static final int W_W = 50; //画像幅
	public static final int W_H = 40; //画像高さ
	
	// UFO
	public static final int U_X = 0; //X座標
	public static final int U_Y = 0; //Y座標
	public static final String U_imgPATH = "images/ufo.jpeg"; //画像ファイルパス
	public static final String U_imgPATH2 = "images/ufoB.png"; //壊れ画像ファイルパス
	public static final int U_W = 45; //画像幅
	public static final int U_H = 20; //画像高さ
	
	//当たり判定の共通メソッド ミサイル(mx,my) 当たるモノ(x,y,w,h)
	static boolean hitCheck(int mx,int my, int x,int y, int w, int h) {
		if(x < mx && mx < x+w && y < my && my < y+h) {
			return true;
		}
		return false;
	}
	
	//当たり判定の共通メソッド ミサイル(mx,my) 当たるモノ(x,y,w,h)
	static boolean hitCheck2(int mx,int my, int x,int y, int w, int h) { //UFO用
		if(x < mx && mx < x+w && /*y < my &&*/ my < y+h) {
			return true;
		}
		return false;
	}
	
}
