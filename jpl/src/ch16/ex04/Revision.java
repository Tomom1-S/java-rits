package ch16.ex04;

/* JPL p.339 の例 */
public @interface Revision {
    int major() default 1;
    int minor() default 0;
}
