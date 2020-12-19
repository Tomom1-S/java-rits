package ch16.ex04;

import java.lang.annotation.Annotation;

public class AnnotationDisplay {
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName(args[0]);
            System.out.println(c);
            printAnnotations(c.getAnnotations());
        } catch (ClassNotFoundException e) {
            System.out.println("unknown class: " + args[0]);
        }
    }

    private static void printAnnotations(Annotation[] anns) {
        // 柴田さん：アノテーションが一つもない場合に正しく動く？
        for (Annotation a : anns) {
            System.out.print(" ");
            System.out.println(a.toString());
        }
    }
}
