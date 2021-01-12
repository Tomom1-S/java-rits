package ch09.ex07;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class WebPageReader {
    public static void main(final String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("URL is needed");
        }

        final String urlStr = args[0];
        final String outPathname = args.length > 1 ? args[1] : "src/ch09/ex07/result/out.html";

        try {
            final URL url = new URL(urlStr);
            try (final InputStream in = url.openStream()) {
                final Path path = Paths.get(outPathname);
                // 指定されたパスが存在しないときはディレクトリを作成
                Files.createDirectories(path);
                Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
