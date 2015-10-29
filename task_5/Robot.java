package Lesson6;

/**
 * Created by Rapid Blaze on 29.10.2015.
 */
public class Robot {
    public static class RobotEx extends Thread {
        public static Object sLock = new Object();
        private String name;


        public RobotEx(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (; ; ) {
                synchronized (sLock) {
                    step();
                    sLock.notify();
                    try {
                        sLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void step() {
            System.out.println("Step: " + name);
        }
    }

    public static void main(String... args) {
        new Thread(new RobotEx("left")).start();
        new Thread(new RobotEx("right")).start();
    }

}
