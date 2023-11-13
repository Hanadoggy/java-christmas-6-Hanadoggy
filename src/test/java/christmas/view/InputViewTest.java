package christmas.view;

import christmas.dto.OrderDto;
import christmas.entity.Beverage;
import christmas.entity.MainDish;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest {
    InputViewStub inputView = new InputViewStub();

    @ValueSource(strings = {"1", "10", "20", "31"})
    @ParameterizedTest
    void 유효한_날짜_입력(String input) {
        inputView.input = input;

        assertThat(inputView.readDate())
                .isEqualTo(Integer.parseInt(input));
    }

    @ValueSource(strings = {"-1","0","32","yujin","!@","","-","0a"})
    @ParameterizedTest
    void 범위를_벗어나거나_숫자가_아닌_문자_입력(String input) {
        inputView.input = input;

        assertThatThrownBy(() -> inputView.readDate())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 유효한_주문_입력() {
        // given
        inputView.input = "바비큐립-1,샴페인-1";

        // when
        OrderDto orderDto = inputView.readOrder(1);

        // then
        assertThat(orderDto.getNumber(MainDish.BBQ_RIBS.getName())).isEqualTo(1);
        assertThat(orderDto.getNumber(Beverage.CHAMPAGNE.getName())).isEqualTo(1);
    }

    @ValueSource(strings = {
            "샴페인-1 , 바비큐립-2 , 아이스크림-1",
            "제로콜라:1,바비큐립=1",
            "샴페인 - 1,아이스크림 - 2",
            "",
            "레드와인-2,바비큐립-3,타파스--2",
            "제로콜라-1,바비큐립-2-"
    })
    @ParameterizedTest
    void 양식에_맞지않는_주문_입력(String input) {
        inputView.input = input;

        assertThatThrownBy(() -> inputView.readOrder(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

}