package lessons.four;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {

    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        //Task1
        System.out.println("Task 1");
        doTask1();

        System.out.println();

        //Task2
        System.out.println("Task 2");
        Set<String> mySet = new HashSet<>(Arrays.asList("one", "two", "three"));
        forItem(mySet, System.out::println);

        System.out.println();

        //Task3
        System.out.println("Task 3");
        System.out.println(doubleUp(21, () -> 2));

        System.out.println();

        //Task 4
        System.out.println("Task 4");
        System.out.println(findAllChars("ss98ss91slkjls", 's')
                .orElse("Chars not found")
        );

        System.out.println(findAllChars("ccc", 's')
                .orElse("Char not found")
        );

    }

    public static void doTask1() {

        List<Integer> myList = new LinkedList<>(Arrays.asList(1, 3, 4, 5, 8, 12, 32, 435, 54));
        myList.forEach(System.out::println);

    }

    public static void forItem(Set<String> set, Consumer<String> consumer) {
        set.forEach(consumer);
    }

    public static int doubleUp(int i, Supplier<Integer> supplier) {
        return i * supplier.get();
    }

    public static Optional<String> findAllChars(String target, char toFind) {
        sb.setLength(0);
        for (char c : target.toCharArray()) {
            if (c == toFind) {
                sb.append(c);
            }
        }
        if (sb.length() != 0) {
            return Optional.of(sb.toString());
        }
        return Optional.empty();
    }

}
