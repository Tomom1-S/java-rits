package ch21.ex04;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ShortStrings implements Iterator<String> {
    Iterator<String> strings;   // 元の文字列
    String nextShort;   // 次が不明ならば null
    final int maxLen;   // この長さ以下の文字列だけを返す

    public ShortStrings(Iterator<String> strings, int maxLen) {
        this.strings = strings;
        this.maxLen = maxLen;
        nextShort = null;
    }

    @Override
    public boolean hasNext() {
        if (nextShort != null) {    // すでに見つけている
            return true;
        }
        while (strings.hasNext()) {
            nextShort = strings.next();
            if (nextShort.length() <= maxLen) {
                return true;
            }
        }
        nextShort = null;   // 見つけられなかった
        return false;
    }

    @Override
    public String next() {
        if (nextShort == null && !hasNext()) {
            throw new NoSuchElementException();
        }
        final String n = nextShort;   // nextShort を記録する
        nextShort = null;
        return n;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
