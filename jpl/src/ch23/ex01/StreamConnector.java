package ch23.ex01;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class StreamConnector {
    public static Process userProg(String cmd) throws IOException {
        Process proc = Runtime.getRuntime().exec(cmd);
        plugTogether(System.in, proc.getOutputStream());
        plugTogether(System.out, proc.getInputStream());
        plugTogether(System.err, proc.getErrorStream());
        return proc;
    }

    private static void plugTogether(InputStream input, OutputStream output) throws IOException {
        IOUtils.copy(input, output);


    }

    private static void plugTogether(OutputStream output, InputStream input) {


    }

    private class InputStreamThread extends Thread {
        private final BufferedReader br;

        InputStreamThread(InputStream input) {
            br = new BufferedReader(new InputStreamReader(input));
        }

        @Override
        public void run() {
            try {
                for (; ; ) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
//                    list.add(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
