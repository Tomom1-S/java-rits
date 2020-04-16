package models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClassSearch {
    public Object callConstructor(Constructor c, Object... args) throws ClassNotFoundException {
        Parameter[] params = c.getParameters();
        List<Object> values = new ArrayList<>() {};
        int i = 0;
        for (Parameter p : params) {
            Class<?> cls = ReflectionUtils.getType(p.getType().getName());
            Object obj = args[i++];
            values.add(cls.cast(obj));
        }

        try {
            return c.newInstance(values.toArray());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public List<Type> getConstructorParameterTypes(Constructor constructor) {
        Type[] types = constructor.getGenericParameterTypes();
        List<Type> typeList = new ArrayList<>();
        for (Type type : types) {
            typeList.add(type);
        }
        return typeList;
    }

    public List<Parameter> getConstructorParameters(Constructor constructor) {
        Parameter[] params = constructor.getParameters();
        List<Parameter> paramList = new ArrayList<>();
        for (Parameter param : params) {
            paramList.add(param);
        }
        return paramList;
    }

    public List<Constructor> getConstructors(Class cls) {
        Constructor[] constructors = cls.getDeclaredConstructors();
        List<Constructor> constructorList = new ArrayList<>();
        for (Constructor constructor : constructors) {
            constructorList.add(constructor);
        }
        return constructorList;
    }

    public Class searchClass(String name) throws ClassNotFoundException {
        return Class.forName(name);
    }
}
