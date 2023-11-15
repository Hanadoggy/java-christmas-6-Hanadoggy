package christmas.common;

import christmas.dto.OrderDto;
import christmas.entity.Appetizer;
import christmas.entity.Beverage;
import christmas.entity.Dessert;
import christmas.entity.MainDish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RestaurantValidatorTest {
    Map<String, Integer> menu = new HashMap<>();
    final int VALID_DATE = 1;

    @BeforeEach
    void cleanUp() {
        menu.clear();
    }

    @ValueSource(ints = {1, 10, 30, 31})
    @ParameterizedTest
    void 유효한_날짜_입력(int day) {
        RestaurantValidator.checkReservationDate(day);
    }

    @ValueSource(ints = {-10, 0, 32, 400})
    @ParameterizedTest
    void 범위를_벗어난_날짜_입력(int day) {
        assertThatThrownBy(() -> RestaurantValidator.checkReservationDate(day))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 유효한_주문_입력() {
        menu.put(Beverage.CHAMPAGNE.getName(), 1);
        menu.put(MainDish.BBQ_RIBS.getName(), 1);
        menu.put(Dessert.ICE_CREAM.getName(), 1);

        assertThat(new OrderDto(VALID_DATE, menu).getReservationDate())
                .isEqualTo(VALID_DATE);
    }

    @Test
    void 개수를_초과한_주문_단일품목() {
        menu.put(MainDish.CHRISTMAS_PASTA.getName(), 21);

        assertThatThrownBy(() -> new OrderDto(VALID_DATE, menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 개수를_초과한_주문_다중품목() {
        menu.put(Appetizer.CAESAR_SALAD.getName(), 5);
        menu.put(Dessert.CHOCOLATE_CAKE.getName(), 5);
        menu.put(MainDish.CHRISTMAS_PASTA.getName(), 11);

        assertThatThrownBy(() -> new OrderDto(VALID_DATE, menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
