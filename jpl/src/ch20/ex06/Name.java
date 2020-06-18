package ch20.ex06;

public enum Name {
    X("X"),
    Y("Y"),
    Z("Z"),
    ;

    private String text;

    Name(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static boolean contains(String test) {
        for (Name n : Name.values()) {
            if (n.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
