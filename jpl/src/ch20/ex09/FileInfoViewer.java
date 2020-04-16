package ch20.ex09;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileInfoViewer {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IllegalArgumentException("File path is needed!");
        }

        for (String path : args) {
            File src = new File(path);
            if (!src.exists()) {
                throw new IllegalArgumentException("Not found: " + path);
            } else if (!src.isFile()) {
                throw new IllegalArgumentException("Not file: " + path);
            }

            System.out.println("<File Info>");
            System.out.println("Name: " + src.getName());
            System.out.println("Path: " + src.getPath());
            System.out.println("Absolute path: " + src.getAbsolutePath());
            System.out.println("Canonical path: " + src.getCanonicalPath());
            System.out.println("Parent: " + src.getParent());
            System.out.println("Last modified: " + new Date(src.lastModified()));
            System.out.println("Length: " + src.length() + " bytes");
        }

    }


}
