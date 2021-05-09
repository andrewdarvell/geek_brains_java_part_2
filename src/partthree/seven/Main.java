package partthree.seven;

import partthree.seven.examples.TestingClassOne;
import partthree.seven.examples.TestingTwoClass;

public class Main {

    public static void main(String[] args) {
        TestRunner.start("partthree.seven.examples.TestingClassOne");
        System.out.println("");
        System.out.println("next test");
        System.out.println("");
        try {
            TestRunner.start(TestingTwoClass.class);
        }catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
