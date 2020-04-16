package models;

import java.util.HashMap;
import java.util.Map;

public class ReflectionUtils {
    private static final Map<String, Class<?>> PRIMITIVE_TYPE_MAP = new HashMap<String, Class<?>>();

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

    private static void registerPrimitiveType(String typeName,
                                              Class<?> cls) {
        PRIMITIVE_TYPE_MAP.put(typeName, cls);
    }

    /**
     * クラス名から型を取得（Primitive 型にも対応）
     * 例："int" → java.lang.Integer.class
     * @param className
     *            型の取得対象となるクラス名
     * @return オブジェクトの型
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getType(String className) throws ClassNotFoundException{
        if (PRIMITIVE_TYPE_MAP.containsKey(className)) {
            return (Class<T>) PRIMITIVE_TYPE_MAP.get(className);
        }
        return (Class<T>) Class.forName(className);
    }

}
