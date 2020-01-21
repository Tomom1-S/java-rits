package model;

public enum TypeName {
    BOOLEAN("boolean"),
    CHAR("char"),
    BYTE("byte"),
    SHORT("short"),
    INT("int"),
    LONG("long"),
    FLOAT("float"),
    DOUBLE("double");

    private String name;

    TypeName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
