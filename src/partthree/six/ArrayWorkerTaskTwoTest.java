package partthree.six;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

public class ArrayWorkerTaskTwoTest {

    @ParameterizedTest
    @MethodSource("arrayOneAndFourParamsProvider")
    void shouldCheckOneAndFourInArraySuccessfully(int[] source, boolean expected) {
        Assertions.assertEquals(expected, ArrayWorker.checkOneAndFourInArray(source));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionWhenArrayIsEmpty(int[] source) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArrayWorker.checkOneAndFourInArray(source));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenArrayContainsOtherOneOrFourValues() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArrayWorker.checkOneAndFourInArray(new int[]{1, 2, 3, 4, 5}));
    }

    static Stream<Arguments> arrayOneAndFourParamsProvider() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 1, 4, 4, 1, 4, 1, 4, 1}, true),
                Arguments.arguments(new int[]{4, 1}, true),
                Arguments.arguments(new int[]{1, 1, 1}, false),
                Arguments.arguments(new int[]{4, 4, 4}, false)
        );
    }

}
