package Lesson8;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class Test1 {
    private static LimitedStack limitedStack = new LimitedStack(20000);

    public static long exerciseOne() {
        for (int i = 0; i < 15000; i++) {
            limitedStack.push(i);
        }
        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            limitedStack.poll();
        }
        return System.currentTimeMillis() - timeStart;
    }

    public static void output() throws InvocationTargetException, IllegalAccessException {
        space();
        Class cv = LimitedStack.class;
        Method[] methods = cv.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            System.out.println(method.getReturnType());
            System.out.println(
                    Arrays.toString(method.getParameterTypes())
            );
            for (Annotation annotation : method.getAnnotations()) {
                Benchmark benchmark = (Benchmark) annotation;
                int warmCount = benchmark.warmCount();
                int iterationCount = benchmark.iterationCount();
                for (int i = 0; i < warmCount; i++) {
                    if (method.getParameterCount() == 0) {
                        Object[] args = {};
                        method.invoke(limitedStack, args);
                    }
                    if (method.getParameterCount() == 1) {
                        Object[] args = {1};
                        method.invoke(limitedStack, args);
                    }
                }
                long time = System.currentTimeMillis();
                for (int i = 0; i < iterationCount; i++) {
                    if (method.getParameterCount() == 0) {
                        Object[] args = {};
                        method.invoke(limitedStack, args);
                    }
                    if (method.getParameterCount() == 1) {
                        Object[] args = {1};
                        method.invoke(limitedStack, args);
                    }
                }
                System.out.println("Вызов метода " + method.getName() + " " + iterationCount + " раз занял " + (System.currentTimeMillis() - time));
                System.out.println(annotation);
            }
            space();
        }
    }

    public static void space() {
        for (int i = 0; i < 2; i++)
            System.out.println();
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        //System.out.println(exerciseOne());
        output();
    }
}
