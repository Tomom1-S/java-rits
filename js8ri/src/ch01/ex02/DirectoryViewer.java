package ch01.ex02;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectoryViewer {
    public void showDirectories(final String path) {
        File file = new File(path);
        System.out.println(file.getName() + "'s subdirectories:");

        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }
        });
        for (final File f : files) {
            System.out.println(f.getName());
        }
    }

    public void showDirectoriesWithLambda(final String path) {
        File file = new File(path);
        System.out.println(file.getName() + "'s subdirectories:");

        List<File> files = Arrays.asList(file.listFiles(f -> f.isDirectory()));
        files.forEach(f -> {
            System.out.println(f.getName());
        });
    }

    public void showDirectoriesWithMethodReference(final String path) {
        File file = new File(path);
        System.out.println(file.getName() + "'s subdirectories:");

        List<File> files = Arrays.asList(file.listFiles(File::isDirectory));
        List<String> names = new ArrayList<>();
        files.forEach(f -> {
            names.add(f.getName());
        });
        names.forEach(System.out::println);
    }

}
