package ch08.ex12;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TestCases.class)
public @interface TestCase {
    int params();

    int expected();
}

@Retention(RetentionPolicy.RUNTIME)
@interface TestCases {
    TestCase[] value();
}
