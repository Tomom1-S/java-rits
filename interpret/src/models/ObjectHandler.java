package models;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectHandler<T> {
    private Class cls;
    private Object obj;
    private T[] array;

    public Object callConstructor()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {
        obj = cls.getConstructor().newInstance();
        return obj;
    }

    // TODO: Integer を int に渡せるように（wrapper -> primitive）
    public Object callConstructor(Object... args) throws NoSuchMethodException {
        final int numOfParams = args.length;

        Class<?>[] params = new Class[numOfParams];
        for (int i = 0; i < numOfParams; i++) {
            params[i] = args[i].getClass();
        }

        Constructor constructor = cls.getDeclaredConstructor(params);
        try {
            return obj = constructor.newInstance(args);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getCause());
        }
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

    // TODO 多次元配列を作成する
    public T[] createArray(final Class<T> type, final int size) {
        if (!isPositive(size)) {
            throw new IllegalArgumentException("size should be positive.");
        }
        array = (T[]) Array.newInstance(type, size);
        return array;
    }

    public Object createObject(String type)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        cls = Class.forName(type);
        obj = callConstructor();
        return obj;
    }

    public List<Type> getConstructorParameterTypes(Constructor constructor) {
        List<Type> typeList = new ArrayList<>();

        Type[] types = constructor.getGenericParameterTypes();
        for (Type type : types) {
            typeList.add(type);
        }

        return typeList;
    }

    public List<Constructor> getConstructors() {
        List<Constructor> constructorList = new ArrayList<>();

        Constructor[] constructors = cls.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            constructorList.add(constructor);
        }

        return constructorList;
    }

    public T getArrayElement(int index) {
        if (!isValidIndex(index)) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return array[index];
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

    public List<Type> getMethodParameterTypes(Method method) {
        List<Type> typeList = new ArrayList<>();

        Type[] types = method.getGenericParameterTypes();
        for (Type type : types) {
            typeList.add(type);
        }

        return typeList;
    }

    public List<Method> getMethods() {
        List<Method> methodList = new ArrayList<>();

        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            methodList.add(method);
        }

        return methodList;
    }

    public T[] getThisArray() {
        return this.array;
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

    private boolean isValidIndex(final int index) {
        if (index >= 0 && index < array.length) {
            return true;
        } else {
            return false;
        }
    }

    public T setArrayElement(int index, T value) {
        if (!isValidIndex(index)) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        T oldValue = array[index];
        array[index] = value;
        return oldValue;
    }
}
