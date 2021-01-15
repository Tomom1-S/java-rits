package ch09.ex02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class ExceptionHandler {
    public static void withoutTryWithResources() throws IOException {
        IOException openEx = null; // リソースのオープンに関する例外
        IOException readWriteEx = null; // 読み書きに関する例外
        IOException closeReaderEx = null; // リソースのクローズに関する例外
        IOException closeWriterEx = null; // リソースのクローズに関する例外

        try {
            final BufferedReader in;
            try {
                in = Files.newBufferedReader(Paths.get("/usr/share/dict/words"));
            } catch (final IOException e) {
                openEx = e;
                throw e;
            }

            try {
                final BufferedWriter out;
                try {
                    out = Files.newBufferedWriter(Paths.get("/tmp/out.txt"));
                } catch (final IOException e) {
                    openEx = e;
                    throw e;
                }

                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        out.write(line.toLowerCase());
                        out.newLine();
                    }
                } catch (final IOException e) {
                    readWriteEx = e;
                    throw e;
                } finally {
                    try {
                        out.close();
                    } catch (final IOException e) {
                        closeWriterEx = e;
                        throw e;
                    }
                }
            } finally {
                try {
                    in.close();
                } catch (final IOException e) {
                    closeReaderEx = e;
                    throw e;
                }
            }
        } catch (final IOException e) {
            if (!Objects.isNull(openEx)) {
                if (!Objects.isNull(closeReaderEx)) {
                    openEx.addSuppressed(closeReaderEx);
                    throw openEx;
                }
            }

            if (!Objects.isNull(readWriteEx)) {
                if (!Objects.isNull(closeReaderEx)) {
                    readWriteEx.addSuppressed(closeReaderEx);
                }
                if (!Objects.isNull(closeWriterEx)) {
                    readWriteEx.addSuppressed(closeWriterEx);
                }
                throw readWriteEx;
            }

            if (!Objects.isNull(closeReaderEx) && !Objects.isNull(closeWriterEx)) {
                closeReaderEx.addSuppressed(closeWriterEx);
                throw closeReaderEx;
            }
            throw e;
        }
    }
}
