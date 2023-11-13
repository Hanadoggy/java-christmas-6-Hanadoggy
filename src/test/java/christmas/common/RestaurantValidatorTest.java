package christmas.common;

import christmas.dto.OrderDto;
import christmas.entity.Appetizer;
import christmas.entity.Beverage;
import christmas.entity.Dessert;
import christmas.entity.MainDish;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RestaurantValidatorTest {

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
        OrderDto orderDto = new OrderDto(1);
        orderDto.addOrder(Beverage.CHAMPAGNE.getName(), 1);
        orderDto.addOrder(MainDish.BBQ_RIBS.getName(), 1);
        orderDto.addOrder(Dessert.ICE_CREAM.getName(), 1);

        RestaurantValidator.checkDishNumber(orderDto);
    }

    @Test
    void 개수를_초과한_주문_단일품목() {
        OrderDto orderDto = new OrderDto(1);
        orderDto.addOrder(MainDish.CHRISTMAS_PASTA.getName(), 21);

        assertThatThrownBy(() -> RestaurantValidator.checkDishNumber(orderDto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 개수를_초과한_주문_다중품목() {
        OrderDto orderDto = new OrderDto(1);
        orderDto.addOrder(Appetizer.CAESAR_SALAD.getName(), 5);
        orderDto.addOrder(Dessert.CHOCOLATE_CAKE.getName(), 5);
        orderDto.addOrder(MainDish.CHRISTMAS_PASTA.getName(), 11);

        assertThatThrownBy(() -> RestaurantValidator.checkDishNumber(orderDto))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
