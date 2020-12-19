package ch01.ex14;

public class Walkman {
	private int terminal; // 端子のID
	private boolean isPlaying; // 再生中か否か

	public void setTerminal(int terminal) {
		// 端子のIDを設定
		this.terminal = terminal;
	}

	public int getTerminal() {
		// 端子のIDを取得
		return this.terminal;
	}

	public void play() {
		// テープを再生する処理

		isPlaying = true;
	};

	public void stop() {
		// テープを停止する処理

		isPlaying = false;
	};

}
