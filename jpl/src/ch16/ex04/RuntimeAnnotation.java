package ch16.ex04;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RuntimeAnnotation {
    String foo();
    String bar();
    Revision revision();
}
