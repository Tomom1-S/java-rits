package ch12.ex01;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException() {
        super("No such data found");
    }
}
