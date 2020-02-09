package ch14.ex09;

public class ThreadNotifier implements Runnable {
    private final int delay = 50;
    private final ThreadGroup group;

    public ThreadNotifier(ThreadGroup group) {
        this.group = group;
    }

    public void show() {
        final String groupName = group.getName();
        final int groupNum = group.activeCount();
        System.out.println(groupName + " has " + groupNum + " threads.");

        final int arrLength = groupNum + 5; // activeCount は正確な値ではないため、余裕を持たせる
        Thread[] threads = new Thread[arrLength];
        group.enumerate(threads);
        for (int i = 0; i < arrLength; i++) {
            Thread thread = threads[i];
            if (thread == null) {
                break;
            }
            System.out.println(thread);
        }

        ThreadGroup parent = group;
        while (parent != group.getParent()) {
            parent = group.getParent();
            System.out.println("  Parent: " + parent.getName());
        }
    }

    @Override
    public void run() {
        try {
            for (;;) {
                show();
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
