package partthree.seven;

import java.lang.reflect.Method;
import java.util.Objects;

public class TestingMethod implements Comparable<TestingMethod> {

    private final int priority;
    private final Method method;

    public TestingMethod(int priority, Method method) {
        this.priority = priority;
        this.method = method;
    }

    public int getPriority() {
        return priority;
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestingMethod method1 = (TestingMethod) o;
        return priority == method1.priority && Objects.equals(method, method1.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priority, method);
    }

    @Override
    public int compareTo(TestingMethod o) {
        return Integer.compare(this.getPriority(), o.getPriority());
    }
}
