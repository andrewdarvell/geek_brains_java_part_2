package partthree.seven;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {

    public static void start(String className) {
        try {
            Class<?> testingClass = Class.forName(className);
            start(testingClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void start(Class<?> testingClass) throws RuntimeException {
        Method[] methods = testingClass.getMethods();
        Method beforeSuite = null;
        Method afterSuite = null;
        List<TestingMethod> tests = new LinkedList<>();


        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof BeforeSuite) {
                    if (beforeSuite == null) {
                        beforeSuite = method;
                    } else {
                        throw new RuntimeException("must be one BeforeSuite");
                    }
                }

                if (annotation instanceof AfterSuite) {
                    if (afterSuite == null) {
                        afterSuite = method;
                    } else {
                        throw new RuntimeException("must be one AfterSuite");
                    }
                }

                if (annotation instanceof Test) {
                    if (!method.equals(beforeSuite) && !method.equals(afterSuite)) {
                        Test testAnnotation = (Test) annotation;
                        tests.add(new TestingMethod(testAnnotation.priority(), method));
                    }
                }
            }
        }

        doTestProcess(testingClass, beforeSuite, afterSuite, tests);

    }

    public static void doTestProcess(Class<?> testingClass, Method beforeSuite, Method afterSuite, List<TestingMethod> otherTests) {
        try {
            Constructor<?> declaredConstructor = testingClass.getDeclaredConstructor();
            Object o = declaredConstructor.newInstance();
            if (beforeSuite != null) {
                runMethod(beforeSuite, o);
            }

            for (TestingMethod method : otherTests.stream().sorted().collect(Collectors.toList())) {
                runMethod(method.getMethod(), o);
            }

            if (afterSuite != null) {
                runMethod(afterSuite, o);
            }


        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void runMethod(Method method, Object o) {
        try {
            method.setAccessible(true);
            method.invoke(o);
            method.setAccessible(false);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
