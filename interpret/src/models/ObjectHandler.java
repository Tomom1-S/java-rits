package models;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ObjectHandler {
    Class cls;
    Object obj;

    public Object createObject(String type) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
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
}
