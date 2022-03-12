package com.raisetech.springtestsample.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void 足し算が正しくできること() {
        int actual = calculator.add(1, 2);
        Assertions.assertThat(actual).isEqualTo(3);
    }

    @Test
    void 引き算が正しくできること() {
        int actual = calculator.subtract(1, 1);
        Assertions.assertThat(actual).isEqualTo(0);
    }

    @Test
    void 掛け算が正しくできること() {
        int actual = calculator.multiple(2, 4);
        Assertions.assertThat(actual).isEqualTo(8);
    }

    @Test
    void 割り算が正しくできること() throws Exception {
        int actual = calculator.divide(6, 3);
        Assertions.assertThat(actual).isEqualTo(2);
    }

    @Test
    void 割り算で0で割るとエラーになること() {
        Assertions.assertThatThrownBy(() -> calculator.divide(1, 0))
                .isInstanceOf(Exception.class)
                .hasMessage("0で割ることはできません");
    }
}
