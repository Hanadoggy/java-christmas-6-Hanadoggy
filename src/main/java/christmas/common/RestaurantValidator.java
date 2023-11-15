package christmas.common;

import christmas.dto.OrderDto;

public class RestaurantValidator {

    public static void checkReservationDate(int date) {
        if (date < Range.START_DAY.getValue() || date > Range.END_DAY.getValue()) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkDishNumber(OrderDto orderDto) {
        int sumMenu = 0;
        int maxMenu = Range.MAX_MENU.getValue();

        for (String dishName : orderDto.getOrders()) {
            sumMenu += orderDto.getNumber(dishName);
            if (sumMenu > maxMenu) {
                throw new IllegalArgumentException();
            }
        }
    }

}
