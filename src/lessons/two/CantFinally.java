package lessons.two;

public class CantFinally {

    public static void main(String[] args) {
        System.out.println(notFinallyTwo());
        notFinallyOne();
    }

    /**
     * при System.exit(0) finally не срабатывает
     *
     */
    public static void notFinallyOne() {
        try {
            System.exit(0);
        } finally {
            System.out.println("finally one");
        }
    }

    /**
     * return в finally убивает return в основном коде
     *
     */
    public static String notFinallyTwo() {
        try {
            return "correct";
        } finally {
            return "Not correct";
        }
    }
}
