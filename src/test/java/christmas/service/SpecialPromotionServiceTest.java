package christmas.service;

import christmas.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialPromotionServiceTest {
    SpecialPromotionService service = new SpecialPromotionService();
    Map<Dish, Integer> dishes = new HashMap<>();

    @BeforeEach
    void cleanUp() {
        dishes.clear();
    }

    @ValueSource(ints = {3,10,24,25,31})
    @ParameterizedTest
    void 날짜적용_성공_일요일과_크리스마스(int today) {
        dishes.put(MainDish.T_BONE_STEAK, 1);
        dishes.put(Appetizer.CAESAR_SALAD, 1);
        Order order = new Order(today, dishes);

        assertThat(service.support(order)).isTrue();
    }

    @ValueSource(ints = {-10,0,2,4,32})
    @ParameterizedTest
    void 날짜적용_실패_범위초과(int today) {
        dishes.put(MainDish.T_BONE_STEAK, 1);
        dishes.put(Appetizer.CAESAR_SALAD, 1);
        Order order = new Order(today, dishes);

        assertThat(service.support(order)).isFalse();
    }

    @Test
    void 조건적용_성공_최소금액도달() {
        dishes.put(Dessert.ICE_CREAM, 2);
        Order order = new Order(25, dishes);

        assertThat(service.support(order)).isTrue();
    }

    @Test
    void 조건적용_실패_범위미만() {
        dishes.put(Appetizer.MUSHROOM_SOUP, 1);
        dishes.put(Beverage.ZERO_COKE, 1);
        Order order = new Order(25, dishes);

        assertThat(service.support(order)).isFalse();
    }

    @Test
    void 할인적용_성공() {
        dishes.put(MainDish.T_BONE_STEAK, 1);
        dishes.put(Appetizer.CAESAR_SALAD, 1);
        Order order = new Order(25, dishes);
        DiscountDetail discountDetail = new DiscountDetail();
        int result = 1_000;

        service.apply(order, discountDetail);

        assertThat(discountDetail.getTotalDiscount()).isEqualTo(result);
    }

}
