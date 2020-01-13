package ch16.ex04;

/* JPL p.339 の例 */
public @interface ClassInfo {
    String created();
    String createdBy();
    String lastModified();
    String lastModifiedBy();
    Revision revision();
}
