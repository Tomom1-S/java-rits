package ch17.ex02;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.file.Files;

public class DataHandler {
    private WeakReference<File> lastFile;  // 最後に読んだファイル
    private WeakReference<byte[]> lastData; // （おそらく）最後のデータ

    byte[] readFile(File file) throws IOException {
        // データを記憶しているか調べる
        // 柴田さん：file と lastData の型が異なるので、ここはいつも false になる
//        if (file.equals(lastFile)) {
        byte[] data = lastData.get();
        if (file.equals(data)) {
            if (data != null) {
                return data;
            }
        }

        // 記憶していないので、読み込む
        data = readBytesFromFile(file);
        lastFile = new WeakReference<>(file);
        lastData = new WeakReference<>(data);
        return data;
    }

    private byte[] readBytesFromFile(File file) {
        throw new AssertionError("Not yet implemented");
    }
}
