package ch08.ex12;

import java.lang.reflect.Method;

public class TestCaseExecutor {
    public static void test(final String className) throws ClassNotFoundException {
        final Class<?> target = Class.forName(className);
        for (final Method method : target.getMethods()) {
            // 柴田さん：ここで getAnnotations で TestCases しか見ていないと、
            // TestCase をひとつだけ付けたときはテストが実行されない
            final TestCase[] testCases = method.getAnnotationsByType(TestCase.class);
            if (testCases == null) {
                continue;
            }
            for (final TestCase testCase : testCases) {
                final int params = testCase.params();
                final int expected = testCase.expected();
                final String msg = String.format(
                        "%s-----\nParameter: %d\nExpected value: %d",
                        method.getName(), params, expected);
                System.out.println(msg);

                try {
                    final int actual = (int) method.invoke(null, params);
                    if (actual == expected) {
                        System.out.println("Result: succeeded");
                    } else {
                        System.out.println("Result: failed (actual: " + actual + ")");
                    }
                } catch (final Exception e) {
                    System.out.println("Result: failed (exception: " + e + ")");
                }
                System.out.println();
            }
        }
    }

}
