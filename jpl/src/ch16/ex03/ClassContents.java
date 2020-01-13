package ch16.ex03;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.HashSet;

public class ClassContents {
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

    protected static Member[] deduplicate(Member[] mems1, Member[] mems2) {
        Member[] array = ArrayUtils.addAll(mems1, mems2);

        return new HashSet<Member>(Arrays.asList(array)).toArray(new Member[0]);
    }

    protected static void printMembers(Member[] mems) {
        for (Member m : mems) {
            if(m.getDeclaringClass() == Object.class) {
                continue;
            }
            String decl = m.toString();
            System.out.print(" ");
            System.out.println(strip(decl, "java.lang."));
        }
    }

    protected static String strip(String str, String stripChars) {
        if (str == null) {
            return null;
        }
        return str.replaceFirst(stripChars, "");
    }
}
