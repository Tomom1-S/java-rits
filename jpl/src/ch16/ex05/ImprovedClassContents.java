package ch16.ex05;

import ch16.ex03.ClassContents;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.HashSet;

public class ImprovedClassContents extends ClassContents {
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName(args[0]);
            System.out.println(c);
            printMembers(deduplicate(c.getFields(), c.getDeclaredFields()));
            printMembers(deduplicate(c.getConstructors(), c.getDeclaredConstructors()));
            printMembers(deduplicate(c.getMethods(), c.getDeclaredMethods()));
        } catch (ClassNotFoundException e) {
            System.out.println("unknown class: " + args[0]);
        }
    }

    protected static void printMembers(Member[] mems) {
        for (Member m : mems) {
            Class<?> cls = m.getDeclaringClass();
            if(cls == Object.class) {
                continue;
            }
            String decl = m.toString();
            System.out.print(" ");
            System.out.println(strip(decl, "java.lang."));

            printAnnotations(deduplicate(cls.getAnnotations(), cls.getAnnotations()));
        }
    }

    private static void printAnnotations(Annotation[] anns) {
        // 柴田さん：アノテーションが一つもない場合に正しく動く？
        for (Annotation a : anns) {
            System.out.print("  ");
            System.out.println(a.toString());
        }
    }

    protected static Annotation[] deduplicate(Annotation[] anns1, Annotation[] anns2) {
        Annotation[] array = ArrayUtils.addAll(anns1, anns2);

        return new HashSet<>(Arrays.asList(array)).toArray(new Annotation[0]);
    }
}
