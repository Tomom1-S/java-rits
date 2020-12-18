package ch06.ex11;

import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ActionRepeater {
    public static void main(final String[] args) {
        final Scanner in = new Scanner(System.in);
        final CompletableFuture<PasswordAuthentication> future = ActionRepeater.repeat(
                () -> {
                    System.out.print("User Name: ");
                    final String userName = in.nextLine();

                    System.out.print("Password: ");
                    final String password = in.nextLine();
                    return new PasswordAuthentication(userName, password.toCharArray());
                },
                auth -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return Arrays.equals(auth.getPassword(), "secret".toCharArray());
                }
        );
        future.thenAccept(auth ->
                System.out.println("Welcome, " + auth.getUserName() + "!")
        );

        ForkJoinPool.commonPool().awaitQuiescence(60, TimeUnit.SECONDS);
    }

    public static <T> CompletableFuture<T> repeat(
            final Supplier<T> action, final Predicate<T> until) {
        return CompletableFuture.supplyAsync(action).thenComposeAsync(
                t -> until.test(t)
                        ? CompletableFuture.completedFuture(t)
                        : repeat(action, until));
    }
}
