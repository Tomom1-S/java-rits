package ch03.ex17;

@FunctionalInterface
public interface ThrowableRunnable {
    void run() throws Exception;
}
