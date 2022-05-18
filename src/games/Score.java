package games;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Score {
	
	static int score = 0;
	static Label scoreLabel = null;
	
	public Score() {
		scoreLabel = new Label("0");
		scoreLabel.setLayoutX(Screen.SCORE_X);
		scoreLabel.setLayoutY(Screen.SCORE_Y);
		Font f = new Font(36);
		scoreLabel.setFont(f);
		scoreLabel.setTextFill(Color.RED);
	}
	
	public static void scoreUp(int up) {
		score += up;
		if (scoreLabel != null) {
			scoreLabel.setText(String.valueOf(score));
		}
	}

}
