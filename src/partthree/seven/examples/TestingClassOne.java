package partthree.seven.examples;

import partthree.seven.AfterSuite;
import partthree.seven.BeforeSuite;
import partthree.seven.Test;

public class TestingClassOne {

    @BeforeSuite
    @Test
    public void before() {
        System.out.println("run before");
    }

    @AfterSuite
    public void after() {
        System.out.println("run after");
    }

    @Test
    public void doActionOne() {
        System.out.println("do action one");
    }


    @Test(priority = 10)
    private void doActionTwo() {
        System.out.println("do action two");
    }

    @Test(priority = 2)
    private void doActionThree() {
        System.out.println("do action three");
    }

}
