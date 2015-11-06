package Lesson6;

/**
 * Created by Rapid Blaze on 23.10.2015.
 */
public class LimitedStack {
    public static void main(String... args) {
        final NewSuperStack<String> stack = new NewSuperStack<>(5);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                for (; ; ) {
                    counter++;
                    try {
                        stack.push(String.valueOf(counter));
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Object value = stack.poll();
                    System.out.println("Value: " + value);
                }
            }
        });
        thread2.start();
        Thread thead3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("THREAD3");
                }
            }
        });
        thead3.start();
    }
}
