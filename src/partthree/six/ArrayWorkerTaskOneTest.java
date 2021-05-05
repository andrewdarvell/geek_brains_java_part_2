package partthree.six;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

public class ArrayWorkerTaskOneTest {

    @ParameterizedTest
    @MethodSource("arrayAfterFourParamsProvider")
    void shouldGetSubArraySuccessfully(int[] source, int[] expected) {
        Assertions.assertArrayEquals(expected, ArrayWorker.getPartOfArrayAfterFour(source));
    }

    @Test
    void shouldThrowRuntimeExceptionWhenArrayNotContainsFour() {
        Assertions.assertThrows(RuntimeException.class, () -> ArrayWorker.getPartOfArrayAfterFour(new int[]{1, 2, 3}));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionWhenArrayIsEmpty(int[] source) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArrayWorker.getPartOfArrayAfterFour(source));
    }

    static Stream<Arguments> arrayAfterFourParamsProvider() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}),
                Arguments.arguments(new int[]{4, 1}, new int[]{1}),
                Arguments.arguments(new int[]{1, 2, 4, 4, 2, 3, 1, 7}, new int[]{2, 3, 1, 7})
        );
    }
}
