package ch03.ex07;

import java.util.Comparator;

public class Sample {
    /**
     * @param reverseOrder 逆順にする
     * @param ignoreCase 大文字小文字を区別する
     * @param excludeSpace 空白を除外する
     * @return Comparator を返す
     */
    public static Comparator<String> createComparator(
            final boolean reverseOrder,
            final boolean ignoreCase,
            final boolean excludeSpace
    ) {
        return (o1, o2) -> {
            // 普通の順序/逆順
            final int order = reverseOrder ? -1 : 1;

            // 大文字小文字を区別する/しない
            if (ignoreCase) {
                o1 = o1.toLowerCase();
                o2 = o2.toLowerCase();
            }

            // 空白を埋める/除外する
            if (excludeSpace) {
                o1 = o1.replaceAll("\\s+", "");
                o2 = o2.replaceAll("\\s+", "");
            }
            return order * o1.compareTo(o2);
        };
    }
}
