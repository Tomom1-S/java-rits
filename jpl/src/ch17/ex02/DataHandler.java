package ch17.ex02;

import com.oracle.tools.packager.IOUtils;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.file.Files;

public class DataHandler {
    private WeakReference<File> lastFile;  // 最後に読んだファイル
    private WeakReference<byte[]> lastData; // （おそらく）最後のデータ

    byte[] readFile(File file) throws IOException {
        byte[] data;

        // データを記憶しているか調べる
        if (file.equals(lastFile)) {
            data = lastData.get();
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

    private byte[] readBytesFromFile(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }
}
