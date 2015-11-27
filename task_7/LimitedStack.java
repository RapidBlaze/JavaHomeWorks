package Lesson8;

import java.util.Stack;

public class LimitedStack {

    private Stack<Object> stack = new Stack<>();
    private int maxCount;
    private final Object lock = new Object();

    public LimitedStack(int maxCount) {
        this.maxCount = maxCount;
    }

    public void pushNonSynchronized(final Object value) {
        if (stack.size() == maxCount) {
            System.out.println("[WARN] Limit exceeded!");
            return;
        }
        stack.push(value);
    }

    @Benchmark(warmCount = 5, iterationCount = 15000)
    public void push(final Object value) {
        synchronized (lock) {
            if (stack.size() == maxCount) {
                System.out.println("[WARN] Limit exceeded!");
                return;
            }
            stack.push(value);
        }
        System.out.println("[INFO] Added object");
    }

    public void pushWait(final Object value) {
        boolean isAdded = false;
        while (!isAdded) {
            synchronized (lock) {
                if (stack.size() == maxCount) {
                    System.out.println("[WARN] Limit exceeded!");
                } else {
                    stack.push(value);
                    isAdded = true;
                }
            }
            if (!isAdded) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("[INFO] Added object");
    }

    @Benchmark(warmCount = 5, iterationCount = 10000)
    public Object poll() {
        synchronized (lock) {
            if (stack.size() == 0) {
                System.out.println("[WARN ]No element found!");
            }
            System.out.println("[INFO] object returned");
            return stack.pop();
        }

    }
}
