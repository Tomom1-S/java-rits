package ch13.ex04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeValueReader {
    public ArrayList<Object> convertTypeValue(String str) {
        ArrayList<Object> list = new ArrayList<>();

        List<String> lines = Arrays.asList(str.split("\n"));
        for (String line : lines) {
            String[] set = line.split("\\s+");
            if (set.length != 2) {
                throw new IllegalArgumentException("Each line should be 'type value'!");
            }
            String type = set[0];
            String value = set[1];
            list.add(convertObject(type, value));
        }

        return list;
    }

    private Object convertObject(String type, String value) {
        Object result;

        switch (type) {
            case "byte":
            case "Byte":
                result = Byte.valueOf(value);
                break;
            case "short":
            case "Short":
                result = Short.valueOf(value);
                break;
            case "integer":
            case "Integer":
                result = Integer.valueOf(value);
                break;
            case "long":
            case "Long":
                result = Long.valueOf(value);
                break;
            case "float":
            case "Float":
                result = Float.valueOf(value);
                break;
            case "double":
            case "Double":
                result = Double.valueOf(value);
                break;
            case "character":
            case "Character":
                result = value.charAt(0);
                break;
            case "boolean":
            case "Boolean":
                result = Boolean.valueOf(value);
                break;
            default:
                throw new IllegalArgumentException(type + " is invalid!");
        }

        return result;
    }
}
