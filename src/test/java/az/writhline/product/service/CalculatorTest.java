package az.writhline.product.service;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CalculatorTest {
    @InjectMocks
    private Calculator calculator;

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "3, 5, 8"
    })
    void givenTwoNumberWhenAddThenSuccess(int a, int b, int expected) {
        int result = calculator.add(a, b);
        assertThat(result).isEqualTo(expected);

    }

    @Test
    void givenTwoNumberWhenDivideThenSuccess() {
        int result = calculator.divide(20, 5);
        assertThat(result).isEqualTo(4);

    }

    @Test
    void givenTwoNumberWhenDivideToZeroThenException() {
        assertThatThrownBy(() -> calculator.divide(20, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Divide by zero is prohibited");
    }
}