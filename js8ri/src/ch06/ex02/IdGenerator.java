package ch06.ex02;

import java.util.concurrent.atomic.LongAdder;

public class IdGenerator {
    final LongAdder longAdder = new LongAdder();
    public long generate() {
        longAdder.increment();
        return longAdder.sum(); //
    }
}
