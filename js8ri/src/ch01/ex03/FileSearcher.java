package ch01.ex03;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

public class FileSearcher {
    public void showFiles(final String path, final String extension) {
        File file = new File(path);
        System.out.println("Result:");

        File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File _f, String name) {
                return name.toLowerCase().endsWith(extension.toLowerCase());
            }
        });
        for (final File f : files) {
            System.out.println(f.getName());
        }
    }

    public void showFilesWithLambda(final String path, final String extension) {
        File file = new File(path);
        System.out.println("Result:");

        List<File> files = Arrays.asList(file.listFiles((_f, name) ->
                name.toLowerCase().endsWith(extension.toLowerCase()))
        );
        files.forEach(f -> {
            System.out.println(f.getName());
        });
    }
}
