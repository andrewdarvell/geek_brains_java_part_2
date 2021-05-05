package partthree.six;

import java.util.Arrays;

public class ArrayWorker {

    public static int[] getPartOfArrayAfterFour(int[] sourceArray) {
        if (sourceArray == null || sourceArray.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int index = -1;
        for (int i = sourceArray.length - 1; i >= 0; i--) {
            if (sourceArray[i] == 4) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new RuntimeException("Fours not founded");
        } else {
            return Arrays.copyOfRange(sourceArray, index + 1, sourceArray.length);
        }
    }

    public static boolean checkOneAndFourInArray(int[] sourceArray) {
        if (sourceArray == null || sourceArray.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        if (Arrays.stream(sourceArray).filter(a -> a != 4 && a != 1).count() > 0) {
            throw new IllegalArgumentException("Elements of array must be 1 or 4");
        }

        return Arrays.stream(sourceArray).anyMatch(a -> a == 4) && Arrays.stream(sourceArray).anyMatch(a -> a == 1);

    }


}
