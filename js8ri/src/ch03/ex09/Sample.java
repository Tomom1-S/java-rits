package ch03.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;

public class Sample {
    // 柴田さん
    // T ではなくて Object でもよい
    public static <T> Comparator<T> lexicographicComparator(final String... fieldNames) {
        return (o1, o2) -> {
            for (final String fieldName : fieldNames) {
                final Object val1 = getFieldValue(o1, fieldName);
                final Object val2 = getFieldValue(o2, fieldName);
                if (val1.equals(val2)) {
                    continue;
                }
                return ((Comparable) val1).compareTo(val2);
            }
            return 0;
        };
    }

    static Object getFieldValue(final Object obj, final String fieldName) {
        // 柴田さん
        // ここでメソッド呼び出しの度にクラスを取得するのは少し無駄が多い
        Class<?> cls = obj.getClass();
        while (cls != null) {
            try {
                final Field field = cls.getDeclaredField(fieldName);
                field.setAccessible(true);  // public でないフィールドにもアクセスできるように
                return field.get(obj);
            } catch (NoSuchFieldException e) {
                cls = cls.getSuperclass();
            } catch (IllegalAccessException e) {
                // setAccessible(true) しているので、この例外にはならない
                e.printStackTrace();
            }
        }
        throw new RuntimeException(fieldName + " doesn't exist");
    }
}
