package christmas.view;

import christmas.entity.Beverage;
import christmas.entity.Dessert;
import christmas.entity.Dish;
import christmas.entity.MainDish;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RestaurantValidatorTest {
    RestaurantValidator validator = new RestaurantValidator();

    @ValueSource(strings = {"1", "10", "30", "31"})
    @ParameterizedTest
    void 예약날짜_성공(String day) {
        assertThat(validator.convertReservationDate(day))
                .isEqualTo(Integer.parseInt(day));
    }

    @ValueSource(strings = {"-10", "0", "32", "99"})
    @ParameterizedTest
    void 예약날짜_실패_입력범위가아님(String day) {
        assertThatThrownBy(() -> validator.convertReservationDate(day))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ValueSource(strings = {"!@", "", ".,|", "yujin"})
    @ParameterizedTest
    void 예약날짜_실패_숫자가아님(String day) {
        assertThatThrownBy(() -> validator.convertReservationDate(day))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문입력_성공() {
        // given
        String input = "샴페인-1,바비큐립-1,아이스크림-1";
        Map<Dish, Integer> expectedResult = new HashMap<>();
        expectedResult.put(Beverage.CHAMPAGNE, 1);
        expectedResult.put(MainDish.BBQ_RIBS, 1);
        expectedResult.put(Dessert.ICE_CREAM, 1);

        // when
        Map<Dish, Integer> result = validator.convertOrder(input);

        // then
        assertThat(expectedResult.keySet())
                .containsExactlyInAnyOrderElementsOf(result.keySet());
        assertThat(expectedResult.values())
                .containsExactlyInAnyOrderElementsOf(result.values());
    }

    @ValueSource(strings = {
            "샴페인-1 , 바비큐립-2 , 아이스크림-1",
            "제로콜라:1,바비큐립=1",
            "샴페인 - 1,아이스크림 - 2",
            "",
            "레드와인-2,바비큐립-3,타파스--2"
    })
    @ParameterizedTest
    void 주문입력_실패_틀린_입력형식(String input) {
        assertThatThrownBy(() -> validator.convertOrder(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ValueSource(strings = {
            "샴페인-1,제로콜라-3",
            "샴페인-10,타파스-5,양송이수프-5,아이스크림-5",
            "샴페인-20,제로콜라-20,레드와인-20"
    })
    @ParameterizedTest
    void 주문입력_실패_조건에_맞지않는_주문(String input) {
        assertThatThrownBy(() -> validator.convertOrder(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
