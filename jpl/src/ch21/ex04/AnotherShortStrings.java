package ch21.ex04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class AnotherShortStrings implements ListIterator<String> {
    private ListIterator<String> strings;   // 元の文字列
    private String nextShort;   // 次が不明ならば null
    private String prevShort;   // 前が不明ならば null
    final int maxLen;   // この長さ以下の文字列だけを返す
    private boolean removed;    // remove が呼ばれたか

    public AnotherShortStrings(ListIterator<String> strings, int maxLen) {
        this.strings = strings;
        this.maxLen = maxLen;
        nextShort = null;
        prevShort = null;
        removed = false;
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
        removed = false;
        final String n = nextShort;   // nextShort を記録する
        nextShort = null;
        return n;
    }

    @Override
    public boolean hasPrevious() {
        if (prevShort != null) {    // すでに見つけている
            return true;
        }
        while (strings.hasPrevious()) {
            prevShort = strings.previous();
            if (prevShort.length() <= maxLen) {
                return true;
            }
        }
        prevShort = null;   // 見つけられなかった
        return false;
    }

    @Override
    public String previous() {
        if (prevShort == null && !hasPrevious()) {
            throw new NoSuchElementException();
        }
        removed = false;
        final String n = prevShort;   // prevShort を記録する
        prevShort = null;
        return n;
    }

    @Override
    public int nextIndex() {
        return strings.nextIndex();
    }

    @Override
    public int previousIndex() {
        return strings.previousIndex();
    }

    @Override
    public void remove() {
        if (removed) {
            return; // すでに remove されていたら何もしない
        }

        strings.remove();
        removed = true;
    }

    @Override
    public void set(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(String s) {
        throw new UnsupportedOperationException();
    }
}
