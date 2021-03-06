package models;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClassSearch {
    public Object callConstructor(Constructor c, Object... args)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Parameter[] params = c.getParameters();
        List<Object> values = new ArrayList<>() {
        };
        int i = 0;
        for (Parameter p : params) {
            Class<?> cls = ReflectionUtils.getType(p.getType().getName());
            Object obj = args[i++];
            values.add(ReflectionUtils.castObject(cls, obj));
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
        final List<Constructor> list =  ReflectionUtils.combineArrayWithoutDuplicates(
                cls.getConstructors(), cls.getDeclaredConstructors()
        );
        return ReflectionUtils.sortByMemberName(list);
    }

    public Class searchClass(String name) throws ClassNotFoundException {
        return Class.forName(name);
    }
}
