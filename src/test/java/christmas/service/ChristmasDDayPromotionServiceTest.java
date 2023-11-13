package christmas.service;

import christmas.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDDayPromotionServiceTest {
    ChristmasDDayPromotionService service = new ChristmasDDayPromotionService();
    Map<Dish, Integer> dishes = new HashMap<>();

    @BeforeEach
    void cleanUp() {
        dishes.clear();
    }

    @ValueSource(ints = {1,2,10,20,24,25})
    @ParameterizedTest
    void 날짜적용_성공_1일부터_25일까지(int today) {
        dishes.put(MainDish.BBQ_RIBS, 1);
        Order order = new Order(today, dishes);

        assertThat(service.support(order)).isTrue();
    }

    @ValueSource(ints = {0,26,27,30,31,40})
    @ParameterizedTest
    void 날짜적용_실패_범위초과(int today) {
        dishes.put(MainDish.BBQ_RIBS, 1);
        Order order = new Order(today, dishes);

        assertThat(service.support(order)).isFalse();
    }

    @Test
    void 조건적용_성공_최소금액도달() {
        dishes.put(Dessert.ICE_CREAM, 2);
        Order order = new Order(1, dishes);

        assertThat(service.support(order)).isTrue();
    }

    @Test
    void 조건적용_실패_최소금액미만() {
        dishes.put(Dessert.ICE_CREAM, 1);
        dishes.put(Beverage.ZERO_COKE, 1);
        Order order = new Order(1, dishes);

        assertThat(service.support(order)).isFalse();
    }

    @ValueSource(ints = {1,6,11,25})
    @ParameterizedTest
    void 할인적용_성공(int today) {
        dishes.put(MainDish.BBQ_RIBS, 1);
        Order order = new Order(today, dishes);
        DiscountDetail discountDetail = new DiscountDetail();
        int result = 1000 + (100 * (today - 1));

        service.apply(order, discountDetail);

        assertThat(discountDetail.getTotalDiscount())
                .isEqualTo(result);
    }

}
