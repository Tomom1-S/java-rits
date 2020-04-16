package ch21.ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.WeakHashMap;

public class DataHandler {
    private File lastFile;  // 最後に読んだファイル
    private WeakHashMap<String, byte[]> lastMap = new WeakHashMap<>();    // （おそらく）最後のデータ
    private final String key = "data";

    byte[] readFile(File file) throws IOException {
        byte[] data;

        // データを記憶しているか調べる
        if (file.equals(lastFile)) {
            data = lastMap.get(key);
            if (data != null) {
                return data;
            }
        }

        // 記憶していないので、読み込む
        data = readBytesFromFile(file);
        lastFile = file;
        lastMap.put(key, data);
        return data;
    }

    private byte[] readBytesFromFile(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }
}
