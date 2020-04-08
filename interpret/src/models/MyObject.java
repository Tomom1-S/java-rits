package models;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class MyObject<T> {
    private Class cls;
    private T obj;

    public MyObject(Class cls, T obj) {
        this.cls = cls;
        this.obj = obj;
    }

    public Object callMethod(String name) throws NoSuchMethodException {
        Method method = cls.getDeclaredMethod(name);
        try {
            return method.invoke(obj);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    // TODO: Integer を int に渡せるように（wrapper -> primitive）
    public Object callMethod(String name, Object... args) throws NoSuchMethodException {
        final int numOfParams = args.length;

        Class<?>[] params = new Class[numOfParams];
        for (int i = 0; i < numOfParams; i++) {
            params[i] = args[i].getClass();
        }

        Method method = cls.getDeclaredMethod(name, params);
        try {
            return method.invoke(obj, args);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public void changeField(String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = cls.getDeclaredField(name);

        if (Modifier.isPrivate(field.getModifiers())) {
            field.setAccessible(true);
        }

        if (Modifier.isFinal(field.getModifiers())) {
            System.out.println("final: " + field.getModifiers());
            field.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        }

        field.set(obj, value);
    }

    public Object getField(String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = cls.getDeclaredField(name);
        field.setAccessible(true);
        return field.get(obj);
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

    public Class getThisClass() {
        return this.cls;
    }

    public Object getThisObject() {
        return this.obj;
    }

    private boolean isPositive(final int value) {
        if (value > 0) {
            return true;
        } else {
            return false;
        }
    }

}
