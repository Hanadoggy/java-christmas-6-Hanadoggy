package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.Message;
import christmas.dto.OrderDto;
import christmas.entity.Beverage;
import christmas.entity.MainDish;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest {
    final int VALID_DATE = 1;

    @AfterEach
    void setUpInput() {
        Console.close();
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @ValueSource(strings = {"1", "10", "20", "31"})
    @ParameterizedTest
    void 유효한_날짜_입력(String input) {
        setInput(input);
        int result = Integer.parseInt(input);

        assertThat(InputView.readDate(Message.RESERVATION_DATE))
                .isEqualTo(result);
    }

    @ValueSource(strings = {"-1","0","32","yujin","!@","  ","-","0a"})
    @ParameterizedTest
    void 범위를_벗어나거나_숫자가_아닌_문자_입력(String input) {
        setInput(input);

        assertThatThrownBy(() -> InputView.readDate(Message.RESERVATION_DATE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 유효한_주문_입력() {
        // given
        setInput("바비큐립-1,샴페인-1");
        int numberOfBBQ = 1;
        int numberOfChampagne = 1;

        // when
        OrderDto orderDto = InputView.readOrder(VALID_DATE, Message.RESERVATION_ORDER);

        // then
        assertThat(orderDto.getNumber(MainDish.BBQ_RIBS.getName())).isEqualTo(numberOfBBQ);
        assertThat(orderDto.getNumber(Beverage.CHAMPAGNE.getName())).isEqualTo(numberOfChampagne);
    }

    @ValueSource(strings = {
            "샴페인-1 , 바비큐립-2 , 아이스크림-1",
            "제로콜라:1,바비큐립=1",
            "샴페인 - 1,아이스크림 - 2",
            "    ",
            "레드와인-2,바비큐립-3,타파스--2",
            "제로콜라-1,바비큐립-2-"
    })
    @ParameterizedTest
    void 양식에_맞지않는_주문_입력(String input) {
        setInput(input);

        assertThatThrownBy(() -> InputView.readOrder(VALID_DATE, Message.RESERVATION_ORDER))
                .isInstanceOf(IllegalArgumentException.class);
    }

}