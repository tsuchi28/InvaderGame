package games;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//音声管理クラス(Clip使用)
public class PlayClip {

	Clip clip=null;
	
	PlayClip(String filename){
		try {
			//ファイル名からClip情報の取得(詳細はAPIドキュメント等で)
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filename));
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//再生(巻き戻し再生)
	public void play() {
		clip.start();
		System.out.println("Playing...");
	}
	
	//リセットしてから再生
	public void resetPlay() {
		clip.setFramePosition(0);
		clip.start();
		System.out.println("ResetPlaying...");
	}
	
	//ループ再生
	public void loopPlay() {
		clip.setFramePosition(0);
		clip.loop(Clip.LOOP_CONTINUOUSLY); //無限ループ
		System.out.println("LoopPlaying...");
	}
	
	//ストップ(一時停止)
	public void stop() {
		clip.stop();
		System.out.println("Stopped.");
	}
	
	//リセット(停止)
	public void reset() {
		clip.setFramePosition(0);
		System.out.println("Reset.");
	}
	
	//10秒早送り
	public void forward() {
		//System.out.println(clip.getFramePosition()); //Debug
		clip.setFramePosition(clip.getFramePosition() + 476673);
		System.out.println("10sec Forward.");
	}
	
	//10秒巻き戻し
	public void backward() {
		//System.out.println(clip.getFramePosition()); //Debug
		if(clip.getFramePosition() >= 476673)
			clip.setFramePosition(clip.getFramePosition() - 476673);
		else
			clip.setFramePosition(0);
		System.out.println("10sec Backward.");
	}
	
	/*Debug*/
	
	//フレームの全部の長さを返す
	public int allFrame() {
		return clip.getFrameLength();
	}
	
	//現在位置のフレームを返す
	public int frame() {
		return clip.getFramePosition();
	}
	
}
