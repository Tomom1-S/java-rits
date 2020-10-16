package ch02.ex08;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sample {
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        final List<T> firstList = first.collect(Collectors.toList());
        final List<T> secondList = second.collect(Collectors.toList());

        final List<T> resultList = new ArrayList<>();
        for (int i = 0; i < Math.min(firstList.size(), secondList.size()); i++) {
            resultList.add(firstList.get(i));
            resultList.add(secondList.get(i));
        }
        return resultList.stream();
    }
}
