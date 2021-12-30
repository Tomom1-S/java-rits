package ch20.ex08;

import java.io.*;

public class EntryReader {
    final File inFile;

    EntryReader(final File inFile) {
        this.inFile  = inFile;
    }

    public void readEntries() {
        try (RandomAccessFile raf = new RandomAccessFile(inFile, "r")) {
            raf.seek(0);

            long pos = 0;
            while (pos < raf.length()) {
                String result = raf.readUTF();
                pos = raf.getFilePointer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try (BufferedReader br = new BufferedReader(new FileReader(inFile))) {
//            String line;
//            int i = 0;
//            while ((line = br.readLine()) != null) {
//
//                i++;
//                if (line.startsWith("%%")) {
//                    System.out.println("New line: " + line);
//                    continue;
//                }
//
//                System.out.println(line);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
