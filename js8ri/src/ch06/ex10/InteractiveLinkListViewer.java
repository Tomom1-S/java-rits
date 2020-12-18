package ch06.ex10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class InteractiveLinkListViewer {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        final int timeout;
        if (args.length > 0 && args[0].equals("debug")) {
            timeout = 10;
        } else {
            timeout = 60;
        }

        CompletableFuture
                .supplyAsync(InteractiveLinkListViewer::getURLInput)
                .thenApply(InteractiveLinkListViewer::blockingReadPage)
                .thenApply(Parser::getLinks)
                .thenAccept((links) -> {
                    for (final URL link : links) {
                        System.out.println(link);
                    }
                });

        ForkJoinPool.commonPool().
                awaitQuiescence(timeout, TimeUnit.SECONDS);
    }

    public static URL getURLInput() {
        while (true) {
            System.out.print("Enter URL: ");
            try {
                return new URL(scanner.nextLine());
            } catch (MalformedURLException ex) {
                // 正しい URL を受け付けるまで何もしない
            }
        }
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
