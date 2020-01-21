package model;

public class ObjectHandler {
    public Object createObject(TypeName type, String name) {
        switch (type) {
            case BOOLEAN:
                final boolean foo = true;
                return foo;
            default:
                return null;
        }
    }

    public void createObject(String type, String name) {
        System.out.println("type: " + type + ", name: " + name);
    }
}
