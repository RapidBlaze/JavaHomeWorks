package Lesson6;

import java.util.Stack;

/**
 * Created by Rapid Blaze on 29.10.2015.
 */
public class NewSuperStack<T> {
    private Stack<T> stack = new Stack<>();
    public int maxCount;

    public NewSuperStack(int maxCount) {
        this.maxCount = maxCount;
    }

    private final Object lock = new Object();

    public void push(T value) throws InterruptedException {
        synchronized (lock) {
            stack.push(value);
            System.out.println("[INFO] Added object");
            if (stack.size() == maxCount) {
                System.out.println("[WARN] Limit exceeded!");
                lock.wait();
            }
        }
    }

    public T poll() {
        synchronized (lock) {
            if (stack.size() == 0) {
                System.out.println("[WARN ]No element found!");
            }
            System.out.println("[INFO] object returned");
            T t = stack.pop();
            lock.notify();
            return t;
        }
    }
}
