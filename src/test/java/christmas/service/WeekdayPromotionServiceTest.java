package christmas.service;


import christmas.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WeekdayPromotionServiceTest {
    WeekdayPromotionService service = new WeekdayPromotionService();
    Map<Dish, Integer> dishes = new HashMap<>();
    int validaDate = 3;

    @BeforeEach
    void cleanUp() {
        dishes.clear();
    }

    @ValueSource(ints = {3,4,7,10,14,28,31})
    @ParameterizedTest
    void 날짜적용_성공_일_목요일(int today) {
        dishes.put(MainDish.T_BONE_STEAK, 1);
        dishes.put(Dessert.CHOCOLATE_CAKE, 1);
        Order order = new Order(today, dishes);

        assertThat(service.support(order)).isTrue();
    }

    @ValueSource(ints = {-10,0,1,2,8,29,30,32})
    @ParameterizedTest
    void 날짜적용_실패_범위초과(int today) {
        dishes.put(MainDish.T_BONE_STEAK, 1);
        dishes.put(Appetizer.CAESAR_SALAD, 1);
        Order order = new Order(today, dishes);

        assertThat(service.support(order)).isFalse();
    }

    @Test
    void 조건적용_성공_최소금액도달_조건에맞는음식() {
        dishes.put(MainDish.T_BONE_STEAK, 1);
        dishes.put(Dessert.CHOCOLATE_CAKE, 1);
        Order order = new Order(validaDate, dishes);

        assertThat(service.support(order)).isTrue();
    }

    @Test
    void 조건적용_실패_최소금액미만() {
        dishes.put(Appetizer.TAPAS, 1);
        dishes.put(Beverage.ZERO_COKE, 1);
        Order order = new Order(validaDate, dishes);

        assertThat(service.support(order)).isFalse();
    }

    @Test
    void 조건적용_실패_조건에맞는음식없음() {
        dishes.put(MainDish.T_BONE_STEAK, 1);
        dishes.put(Appetizer.CAESAR_SALAD, 1);
        Order order = new Order(validaDate, dishes);

        assertThat(service.support(order)).isFalse();
    }

    @Test
    void 할인적용_성공_단일품목() {
        dishes.put(MainDish.BBQ_RIBS, 1);
        dishes.put(Dessert.CHOCOLATE_CAKE, 1);
        Order order = new Order(validaDate, dishes);
        DiscountDetail discountDetail = new DiscountDetail();
        int result = 2_023;

        service.apply(order, discountDetail);

        assertThat(discountDetail.getTotalDiscount()).isEqualTo(result);
    }

    @Test
    void 할인적용_성공_단일품목_여러개() {
        dishes.put(MainDish.BBQ_RIBS, 1);
        dishes.put(Dessert.CHOCOLATE_CAKE, 10);
        Order order = new Order(validaDate, dishes);
        DiscountDetail discountDetail = new DiscountDetail();
        int result = 20_230;

        service.apply(order, discountDetail);

        assertThat(discountDetail.getTotalDiscount()).isEqualTo(result);
    }

    @Test
    void 할인적용_성공_다중품목_여러개() {
        dishes.put(MainDish.BBQ_RIBS, 1);
        dishes.put(Dessert.CHOCOLATE_CAKE, 5);
        dishes.put(Dessert.ICE_CREAM, 5);
        Order order = new Order(validaDate, dishes);
        DiscountDetail discountDetail = new DiscountDetail();
        int result = 20_230;

        service.apply(order, discountDetail);

        assertThat(discountDetail.getTotalDiscount()).isEqualTo(result);
    }

}
