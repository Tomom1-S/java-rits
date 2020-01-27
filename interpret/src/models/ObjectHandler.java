package models;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectHandler {
    Class cls;
    Object obj;
    
    public Object callMethod(String name) throws NoSuchMethodException {
        Method method = cls.getDeclaredMethod(name);
        try {
            return method.invoke(obj);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    // TODO: Integer を int に渡せるように（wrapper -> primitive）
    // TODO: オーバーロードされている場合（引数の個数が未知）
    public Object callMethod(String name, ArrayList args) throws NoSuchMethodException {
        Method method;
        Object result = new Object();
        switch (args.size()) {
            case 1:
                method = cls.getDeclaredMethod(name, args.get(0).getClass());
                try {
                    result = method.invoke(obj, args.get(0));
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException(e.getCause());
                }
                break;
            case 2:
                method = cls.getDeclaredMethod(name, args.get(0).getClass(), args.get(1).getClass());
                try {
                    result = method.invoke(obj, args.get(0), args.get(1));
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException(e.getCause());
                }
                break;
            default:
                break;
        }
        return result;
    }

    public void changeField(String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = cls.getDeclaredField(name);

        // TODO: `private final` のインスタンスフィールドの書き換えもできること
//        if (Modifier.isFinal(field.getModifiers())) {
//            field.setAccessible(true);
//            Field modifiersField = Field.class.getDeclaredField("modifiers");
//            modifiersField.setAccessible(true);
//            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
//            System.out.println("Try to final field");
//
//            field.set(obj, value);
//
//            return;
//        }

        // フィールドにアクセスできないときアクセス制御する
        if (Modifier.isPrivate(field.getModifiers())) {
            field.setAccessible(true);
        }
        field.set(obj, value);
    }

    public Object createObject(String type)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        cls = Class.forName(type);
        obj = cls.getConstructor().newInstance();
        return obj;
    }

    public List<Field> getFields() {
        List<Field> fieldList = new ArrayList<>();

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            fieldList.add(field);
        }

        return fieldList;
    }

    public List<Method> getMethods() {
        List<Method> methodList = new ArrayList<>();

        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            methodList.add(method);
        }

        return methodList;
    }
}
