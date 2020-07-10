package models;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

public class ReflectionUtils {
    private static final Map<String, Class<?>> PRIMITIVE_TYPE_MAP = new HashMap<>();

    static {
        registerPrimitiveType("byte", java.lang.Byte.class);
        registerPrimitiveType("short", java.lang.Short.class);
        registerPrimitiveType("int", java.lang.Integer.class);
        registerPrimitiveType("long", java.lang.Long.class);
        registerPrimitiveType("boolean", java.lang.Boolean.class);
        registerPrimitiveType("float", java.lang.Float.class);
        registerPrimitiveType("double", java.lang.Double.class);
        registerPrimitiveType("char", java.lang.Character.class);
    }

    private static void registerPrimitiveType(String typeName, Class<?> cls) {
        PRIMITIVE_TYPE_MAP.put(typeName, cls);
    }

    /**
     * クラス名から型を取得（Primitive 型にも対応）
     * 例："int" → java.lang.Integer.class
     *
     * @param className 型の取得対象となるクラス名
     * @return オブジェクトの型
     */
    public static <T> Class<T> getType(String className) throws ClassNotFoundException {
        if (PRIMITIVE_TYPE_MAP.containsKey(className)) {
            return (Class<T>) PRIMITIVE_TYPE_MAP.get(className);
        }
        return (Class<T>) Class.forName(className);
    }

    /**
     * クラスを指定してキャスト
     *
     * @param cls    キャスト先の型
     * @param object キャストしたいオブジェクト
     * @return キャストした結果
     */
    static <T> T castObject(Class<T> cls, Object object)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (cls.isInstance(object)) {
            return cls.cast(object);
        } else {
            return cls.getConstructor(object.getClass()).newInstance(object);
        }
    }

    /**
     * 重複を排除して Member の配列を連結
     *
     * @param arr1 連結する配列
     * @param arr2 連結する配列
     * @return 連結した結果
     */
    static <T extends Member> List<T> combineArrayWithoutDuplicates(T[] arr1, T[] arr2) {
        final T[] array = ArrayUtils.addAll(arr1, arr2);
        final Set<T> set = new HashSet<>(Arrays.asList(array));
        final List<T> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    /**
     * Member のリストを名前順に並び替え
     *
     * @param list リスト
     * @return 並び替えた結果
     */
    static <T extends Member> List<T> sortByMemberName(List<T> list) {
        list.sort(Comparator.comparing(T::getName, String.CASE_INSENSITIVE_ORDER));
        return list;
    }
}
