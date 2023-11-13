package christmas.common;

import christmas.dto.OrderDto;

public class RestaurantValidator {

    public static void checkReservationDate(int date) {
        if (date < Range.START_DAY.getValue() || date > Range.END_DAY.getValue()) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkDishNumber(OrderDto orderDto) {
        int totalDishes = 0;
        int maxDishes = Range.MAX_MENU.getValue();

        for (String dishName : orderDto.getOrders()) {
            totalDishes += orderDto.getNumber(dishName);
            if (totalDishes > maxDishes) {
                throw new IllegalArgumentException();
            }
        }
    }

}
