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
        for (Annotation a : anns) {
            System.out.print(" ");
            System.out.println(a.toString());
        }
    }
}
