package ch08.ex11;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class WebBrowser {
    public static void readWithAuthorization(final String url, final String username, final String password)
            throws Exception {
        final InputStream inputStream;
        try {
            inputStream = authorize(new URL(url), username, password);
        } catch (final Exception e) {
            throw e;
        }

        final Scanner scanner = new Scanner(inputStream, "SHIFT-JIS");
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }

    private static InputStream authorize(final URL url, final String username, final String password) throws IOException {
        final Base64.Encoder encoder = Base64.getEncoder();
        final String original = username + ":" + password;
        final String authInfo = encoder.encodeToString(original.getBytes(StandardCharsets.UTF_8));

        final URLConnection connection = url.openConnection();
        connection.setRequestProperty("Authorization", "Basic " + authInfo);
        connection.connect();
        return connection.getInputStream();
    }
}
