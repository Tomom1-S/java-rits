package models;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class MyObject<T> {
    private final Class cls;
    private final String name;
    private final T obj;
    private final int id;
    private static int nextId = 0;

    public MyObject(Class cls, T obj) {
        this.id = nextId++;
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

    public Class getCls() {
        return cls;
    }


    public Object getField(String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = cls.getDeclaredField(name);
        field.setAccessible(true);
        return field.get(obj);
    }

    public List<Field> getFields() {
        final List<Field> list = ReflectionUtils.combineArrayWithoutDuplicates(
                cls.getFields(), cls.getDeclaredFields()
        );
        return ReflectionUtils.sortByMemberName(list);
    }

    public int getId() {
        return id;
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
        final List<Method> list = ReflectionUtils.combineArrayWithoutDuplicates(
                cls.getMethods(), cls.getDeclaredMethods()
        );
        return ReflectionUtils.sortByMemberName(list);
    }

    public T getObj() {
        return obj;
    }
}
