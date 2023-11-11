package christmas.entity;

import christmas.service.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class OrderStatementTest {
    static Map<Dish, Integer> basicDishes = new HashMap<>();
    static List<PromotionService> promotions = new ArrayList<>();
    OrderStatement orderStatement;

    @BeforeAll
    static void init() {
        basicDishes.put(Appetizer.MUSHROOM_SOUP, 1);
        basicDishes.put(MainDish.T_BONE_STEAK, 1);
        basicDishes.put(Beverage.RED_WINE, 1);
        basicDishes.put(Dessert.ICE_CREAM, 1);
        promotions.add(new SpecialPromotionService());
        promotions.add(new WeekendPromotionService());
        promotions.add(new WeekdayPromotionService());
        promotions.add(new GiftPromotionService());
        promotions.add(new ChristmasDDayPromotionService());
    }

    @BeforeEach
    void cleanUpReceipt() {
        orderStatement = new OrderStatement(basicDishes, 1);
    }

    @Test
    void 주문이_있는_영수증_주문내역() {
        List<Dish> result = List.of(Appetizer.MUSHROOM_SOUP, MainDish.T_BONE_STEAK,
                Beverage.RED_WINE, Dessert.ICE_CREAM);

        assertThat(orderStatement.getOrderedDishes())
                .containsExactlyInAnyOrderElementsOf(result);
    }

    @Test
    void 주문이_없는_영수증_주문내역() {
        OrderStatement testOrderStatement = new OrderStatement(new HashMap<>(), 1);
        int result = 0;

        assertThat(testOrderStatement.getOrderedDishes())
                .hasSize(result);
    }

    @Test
    void 주문한_요리개수_요청_1개() {
        MainDish input = MainDish.T_BONE_STEAK;
        int result = 1;

        assertThat(orderStatement.getNumberOf(input))
                .isEqualTo(result);
    }

    @Test
    void 주문하지않은_요리개수_요청_0개() {
        MainDish input = MainDish.BBQ_RIBS;
        int result = 0;

        assertThat(orderStatement.getNumberOf(input))
                .isEqualTo(result);
    }

    @Test
    void 주문이있는_할인전_주문금액_확인_성공() {
        int result = 126_000;

        assertThat(orderStatement.getOriginalPrice())
                .isEqualTo(result);
    }

    @Test
    void 주문이없는_할인전_주문금액_확인_0원() {
        OrderStatement testOrderStatement = new OrderStatement(new HashMap<>(), 1);
        int result = 0;

        assertThat(testOrderStatement.getOriginalPrice())
                .isEqualTo(result);
    }

}