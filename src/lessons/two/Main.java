package lessons.two;

import lessons.two.exceptions.MyArrayDataException;
import lessons.two.exceptions.MyArraySizeException;

public class Main {
    public static void main(String[] args) {
        try {
            String[][] arr = new String[][]{
                    {"10", "20", "32", "22"}
            };
            System.out.println(convertAndSumArray(arr));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        System.out.println("next try");
        try {
            String[][] arr = new String[][]{
                    {"10", "20", "32", "22"},
                    {"10", "10", "ccc", "22"},
                    {"10", "20", "454", "22"},
                    {"10", "20", "32", "22"},
            };
            System.out.println(convertAndSumArray(arr));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        System.out.println("next try");
        try {
            String[][] arr = new String[][]{
                    {"10", "20", "32", "22"},
                    {"10", "30", "32", "22"},
                    {"10", "20", "11", "22"},
                    {"10", "20", "32", "22"},
            };
            System.out.println(convertAndSumArray(arr));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }

    public static int convertAndSumArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    try {
                        sum += Integer.parseInt(arr[i][j]);
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException(
                                String.format("Can't convert string to int in [%d][%d] = \"%s\"", i, j, arr[i][j])
                                , e
                        );
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MyArraySizeException("Array size must by 4X4", e);
        }
        return sum;
    }



}
