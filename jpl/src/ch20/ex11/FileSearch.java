package ch20.ex11;

import java.io.File;
import java.io.FilenameFilter;

public class FileSearch implements FilenameFilter {
    static String suffix;

    @Override
    public boolean accept(File dir, String name) {
        File file = new File(dir, name);

        if (file.isFile() && file.getName().endsWith(suffix)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Directory path & suffix are needed!");
        }

        final String path = args[0];
        final File dir = new File(path);
        suffix = args[1];

        if (!dir.exists()) {
            throw new IllegalArgumentException("Not found: " + path);
        } else if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Not directory: " + path);
        }

        String[] files = dir.list(new FileSearch());
        if (files.length == 0) {
            System.out.println("No files.");
        }
        for (String file : files) {
            System.out.println(file);
        }

    }
}
