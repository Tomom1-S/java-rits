package models;

import java.lang.reflect.*;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class MyObject<T> {
    private Class cls;
    private String name;
    private T obj;

    public MyObject(Class cls, T obj) {
        this.cls = cls;
        this.name = cls.getName();
        this.obj = obj;
    }

    public Object callMethod(Method m, Object... args)
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        Parameter[] params = m.getParameters();
        List<Object> values = new ArrayList<>() {
        };
        int i = 0;
        for (Parameter p : params) {
            Class<?> cls = ReflectionUtils.getType(p.getType().getName());
            Object obj = args[i++];
            values.add(ReflectionUtils.castObject(cls, obj));
        }

        try {
            return m.invoke(obj, values.toArray());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public <E> void changeField(String name, E value)
            throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException {
        Field field = cls.getDeclaredField(name);

        if (Modifier.isPrivate(field.getModifiers())) {
            field.setAccessible(true);
        }

        if (Modifier.isFinal(field.getModifiers())) {
            field.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        }

        Class<?> fieldCls = ReflectionUtils.getType(field.getType().getName());

        field.set(obj, ReflectionUtils.castObject(fieldCls, value));
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

    public String getName() {
        return name;
    }

    public List<Parameter> getMethodParameters(Method method) {
        List<Parameter> paramList = new ArrayList<>();

        Parameter[] params = method.getParameters();
        for (Parameter param : params) {
            paramList.add(param);
        }

        return paramList;
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
