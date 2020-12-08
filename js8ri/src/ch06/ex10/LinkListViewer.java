package ch06.ex10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.*;

public class LinkListViewer {
    public static void main(final String[] args) throws MalformedURLException {
        final URL url = new URL(args[0]);

        CompletableFuture
                .supplyAsync(() -> blockingReadPage(url))
                .thenApply(Parser::getLinks)
                .thenAccept((links) -> {
                    for (final URL link : links) {
                        System.out.println(link);
                    }
                });

        ForkJoinPool.commonPool().
                awaitQuiescence(10, TimeUnit.SECONDS);

    }

    public static String blockingReadPage(final URL url) {
        final StringBuilder builder = new StringBuilder();
        try {
            final URLConnection connection = url.openConnection();

            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + System.lineSeparator());
            }
            reader.close();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
