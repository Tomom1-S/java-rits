package ch16.ex09;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TypeDeclaration {
    public static void main(final String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("Only one argument is needed!");
        }

        TypeDeclaration td = new TypeDeclaration();
        for (String name : args) {
            Class<?> cls;
            try {
                cls = Class.forName(name);
            } catch (ClassNotFoundException e) {
                throw new Exception(e);
            }

            td.printDeclaration(cls);
        }
    }

    private void printDeclaration(final Class<?> cls) {
        final String modifiers = String.join(" ", removeEmptyAndNull(getModifiersString(cls.getModifiers())));
        final String classType = getClassType(cls);

        System.out.printf("%s %s %s {\n", modifiers, classType, cls.getSimpleName());

        printFieldsDeclaration(cls);
        System.out.println();

        printConstructorsDeclaration(cls);
        System.out.println();

        printMethodsDeclaration(cls);

        System.out.println("}");
    }

    private void printFieldsDeclaration(final Class<?> cls) {
        final Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            final Class<?> type = f.getType();
            final List<String> mList = removeEmptyAndNull(getModifiersString(f.getModifiers()));
            mList.removeAll(Arrays.asList("abstract"));
            final String modifiers = String.join(" ", mList);

            System.out.printf("    %s %s %s;\n", modifiers, type.getSimpleName(), f.getName());
        }
    }

    private void printConstructorsDeclaration(final Class<?> cls) {
        final Constructor[] constructors = cls.getDeclaredConstructors();
        for (Constructor c : constructors) {
            final List<String> mList = removeEmptyAndNull(getModifiersString(c.getModifiers()));
            mList.removeAll(Arrays.asList("abstract"));
            final String modifiers = String.join(" ", mList);

            final Parameter[] params = c.getParameters();
            final String parameters = String.join(", ", getParametersString(params));

            if (modifiers.isEmpty()) {
                System.out.printf("    %s(%s) {...};\n", cls.getSimpleName(), parameters);
            } else {
                System.out.printf("    %s %s(%s) {...};\n", modifiers, cls.getSimpleName(), parameters);
            }
        }
    }

    private void printMethodsDeclaration(final Class<?> cls) {
        final Method[] methods = cls.getDeclaredMethods();
        for (Method m : methods) {
            final List<String> mList = removeEmptyAndNull(getModifiersString(m.getModifiers()));
            mList.removeAll(Arrays.asList("abstract"));
            final String modifiers = String.join(" ", mList);

            final Parameter[] params = m.getParameters();
            final String parameters = String.join(", ", getParametersString(params));

            if (modifiers.isEmpty()) {
                System.out.printf("    %s %s(%s) {...};\n", m.getReturnType().getSimpleName(), m.getName(), parameters);
            } else {
                System.out.printf("    %s %s %s(%s) {...};\n", modifiers, m.getReturnType().getSimpleName(), m.getName(), parameters);
            }
        }
    }

    // 空文字と null が入っていたら除外
    // https://stackoverflow.com/questions/5520693/in-java-remove-empty-elements-from-a-list-of-strings
    private List<String> removeEmptyAndNull(final List<String> list) {
        List<String> in = list;
        in.removeAll(Arrays.asList("", null));
        return in;
    }

    // Modifiers
    private List<String> getModifiersString(final int modifier) {
        List<String> modifierList = new ArrayList<>();
        modifierList.add(getAccessModifier(modifier));
        modifierList.add(getAbstractModifier(modifier));
        modifierList.add(getStaticModifier(modifier));
        modifierList.add(getFinalModifier(modifier));
        modifierList.add(getTransientModifier(modifier));
        modifierList.add(getVolatileModifier(modifier));
        modifierList.add(getSynchronizedModifier(modifier));
        modifierList.add(getNativeModifier(modifier));
        modifierList.add(getStrictfpModifier(modifier));

        return modifierList;
    }

    private String getAccessModifier(final int modifier) {
        if (Modifier.isPublic(modifier)) {
            return "public";
        } else if (Modifier.isProtected(modifier)) {
            return "private";
        } else if (Modifier.isPrivate(modifier)) {
            return "private";
        } else {
            return "";  // アクセス修飾子がないときは空文字を返す
        }
    }

    private String getAbstractModifier(final int modifier) {
        if (Modifier.isAbstract(modifier)) {
            return "abstract";
        } else {
            return "";  // アクセス修飾子がないときは空文字を返す
        }
    }

    private String getStaticModifier(final int modifier) {
        if (Modifier.isStatic(modifier)) {
            return "static";
        } else {
            return "";  // アクセス修飾子がないときは空文字を返す
        }
    }

    private String getFinalModifier(final int modifier) {
        if (Modifier.isFinal(modifier)) {
            return "final";
        } else {
            return "";  // アクセス修飾子がないときは空文字を返す
        }
    }

    private String getTransientModifier(final int modifier) {
        if (Modifier.isTransient(modifier)) {
            return "transient";
        } else {
            return "";  // アクセス修飾子がないときは空文字を返す
        }
    }

    private String getVolatileModifier(final int modifier) {
        if (Modifier.isVolatile(modifier)) {
            return "volatile";
        } else {
            return "";  // アクセス修飾子がないときは空文字を返す
        }
    }

    private String getSynchronizedModifier(final int modifier) {
        if (Modifier.isSynchronized(modifier)) {
            return "synchronized";
        } else {
            return "";  // アクセス修飾子がないときは空文字を返す
        }
    }

    private String getNativeModifier(final int modifier) {
        if (Modifier.isNative(modifier)) {
            return "native";
        } else {
            return "";  // アクセス修飾子がないときは空文字を返す
        }
    }

    private String getStrictfpModifier(final int modifier) {
        if (Modifier.isStrict(modifier)) {
            return "strictfp";
        } else {
            return "";  // アクセス修飾子がないときは空文字を返す
        }
    }
    // Modifiers ends

    // Class type
    private String getClassType(final Class<?> cls) {
        if (cls.isEnum()) {
            return "enum";
        } else if (cls.isInterface()) {
            return "interface";
        } else if (cls.isAnnotation()) {
            return "@interface";
        } else {
            return "class";
        }
    }
    // Class type ends

    // Parameters
    private String getParametersString(Parameter[] parameters) {
        final List<String> pList = getParametersStringList(parameters);
        return String.join(", ", pList);
    }

    private List<String> getParametersStringList(final Parameter[] params) {
        if (params.length == 0) {
            return Collections.emptyList();
        }

        final List<String> pList = new ArrayList<>();
        for (Parameter p : params) {
            if (Modifier.isFinal(p.getModifiers())) {
                pList.add("final" + p.getType().getSimpleName() + " " + p.getName());
            } else {
                pList.add(p.getType().getSimpleName() + " " + p.getName());
            }
        }
        return pList;
    }
    // Parameters ends
}
