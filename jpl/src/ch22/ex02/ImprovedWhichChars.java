package ch22.ex02;

import java.util.*;

public class ImprovedWhichChars {
    private Set<Character> used = new HashSet<>();

    public ImprovedWhichChars(String str) {
        for (int i = 0; i < str.length(); i++) {
            used.add(str.charAt(i));
        }
    }

    public List<Character> sort(Set<Character> set) {
        final List<Character> list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }

    @Override
    public String toString() {
        String desc = "[";
        final List<Character> list = sort(used);
        for (final Character ch : list) {
            desc += ch;
        }
        return desc + "]";
    }
}
