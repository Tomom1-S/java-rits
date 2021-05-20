import lombok.Getter;
import models.PlayerStatus;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SoundPlayer {
    private static final long JUMP_INTERVAL_MSEC = 10_000_000L; // 10 sec

    private Clip clip;
    private AudioInputStream audioStream;
    @Getter
    private PlayerStatus status = PlayerStatus.STOPPED;
    @Getter
    private Long currentFrame = 0L;
    private File file;

    public void loadFile(final File file) throws IOException,
            UnsupportedAudioFileException, LineUnavailableException {
        if (status != PlayerStatus.STOPPED) {
            clip.stop();
            clip.close();
            currentFrame = 0L;
        }

        this.file = file;
        audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        status = PlayerStatus.PLAYING;
    }

    public void playOrPause() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        switch (status) {
            case PAUSE:
            case STOPPED:
                clip.close();
                resetAudioStream();
                clip.setMicrosecondPosition(currentFrame);
                play();
                return;
            case PLAYING:
                currentFrame = clip.getMicrosecondPosition();
                clip.stop();
                status = PlayerStatus.PAUSE;
                return;
            default:
                return;
        }
    }

    public double back() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        jump(clip.getMicrosecondPosition() - JUMP_INTERVAL_MSEC);
        return calculatePositionRate();
    }

    public double forward() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        jump(clip.getMicrosecondPosition() + JUMP_INTERVAL_MSEC);
        return calculatePositionRate();
    }

    public double jumpWithGage(double percent) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        final long pos = (long) (clip.getMicrosecondLength() * percent);
        jump(pos);
        return calculatePositionRate();
    }

    private void play() {
        clip.start();
        status = PlayerStatus.PLAYING;
    }

    private void jump(final long c) throws IOException,
            UnsupportedAudioFileException, LineUnavailableException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = c <= 0 ? 0 :
                c >= clip.getMicrosecondLength() ? clip.getMicrosecondLength() - 1 : c;
        clip.setMicrosecondPosition(currentFrame);
        play();
        // TODO PAUSE しているときに jump すると自動で再生してしまう
    }

    private void resetAudioStream() throws IOException,
            UnsupportedAudioFileException, LineUnavailableException {
        audioStream = AudioSystem.getAudioInputStream(file);
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public double calculatePositionRate() {
        if (Objects.isNull(clip)) {
            return 0.0;
        }

        final double rate = ((double) clip.getMicrosecondPosition()) / ((double) clip.getMicrosecondLength());
        // 小数部を取り出す
        return rate - ((int) rate);
    }
}
