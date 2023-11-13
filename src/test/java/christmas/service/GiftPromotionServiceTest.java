package christmas.service;

import christmas.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GiftPromotionServiceTest {
    GiftPromotionService service = new GiftPromotionService();
    Map<Dish, Integer> dishes = new HashMap<>();

    @BeforeEach
    void cleanUp() {
        dishes.clear();
    }

    @Test
    void 조건적용_성공_최소금액도달() {
        dishes.put(Appetizer.CAESAR_SALAD, 15);
        Order order = new Order(1, dishes);

        assertThat(service.support(order)).isTrue();
    }

    @Test
    void 조건적용_실패_최소금액미만() {
        dishes.put(MainDish.T_BONE_STEAK, 2);
        dishes.put(Beverage.ZERO_COKE, 3);
        Order order = new Order(1, dishes);

        assertThat(service.support(order)).isFalse();
    }

    @Test
    void 할인적용_성공() {
        dishes.put(Appetizer.CAESAR_SALAD, 15);
        Order order = new Order(1, dishes);
        DiscountDetail discountDetail = new DiscountDetail();
        int result = Beverage.CHAMPAGNE.getPrice();

        service.apply(order, discountDetail);

        assertThat(discountDetail.getTotalDiscount()).isEqualTo(result);
    }

}