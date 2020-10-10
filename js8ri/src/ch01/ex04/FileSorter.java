package ch01.ex04;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FileSorter {
    public File[] sortFiles(final File[] files) {
        File[] result = files;
        Arrays.sort(result, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile()) {
                    return -1;
                } else if (o1.isFile() && o2.isDirectory()) {
                    return 1;
                } else {
                    return o1.getAbsolutePath().compareToIgnoreCase(o2.getAbsolutePath());
                }
            }
        });
        return result;
    }

    public File[] sortFilesWithLambda(final File[] files) {
        File[] result = files;
        Arrays.sort(result, (o1, o2) -> {
            if (o1.isDirectory() && o2.isFile()) {
                return -1;
            } else if (o1.isFile() && o2.isDirectory()) {
                return 1;
            } else {
                return o1.getAbsolutePath().compareToIgnoreCase(o2.getAbsolutePath());
            }
        });
        return result;
    }
}
