package partthree.generics;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //Task 1
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        changeElements(arr, 3, 7);
        for (Integer a: arr) {
            System.out.print(a);
            System.out.print(",");
        }


        //Task2
        System.out.println();
        ArrayList<Integer> arrayList = toArrayList(arr);
        System.out.println(arrayList.getClass());
        for (Integer a: arrayList) {
            System.out.println(a);
        }

    }

    /**
     * Task 1
     * @param array
     * @param positionOne
     * @param positionTwo
     * @param <T>
     */
    static <T> void changeElements(T[] array, int positionOne, int positionTwo) {
        if (positionOne < 0 || positionTwo < 0 || positionOne >= array.length || positionTwo >= array.length) {
            return;
        }
        T tmp;
        tmp = array[positionOne];
        array[positionOne] = array[positionTwo];
        array[positionTwo] = tmp;
    }

    /**
     * Task 2
     * @param array
     * @param <T>
     * @return
     */
    static <T> ArrayList<T> toArrayList(T[] array) {
        ArrayList<T> result = new ArrayList<>();
        for (T a: array) {
            result.add(a);
        }
        return result;
    }
}
