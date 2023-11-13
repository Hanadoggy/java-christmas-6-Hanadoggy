package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.Message;
import christmas.common.RestaurantValidator;
import christmas.dto.OrderDto;

import static christmas.common.Message.*;

public class InputView {

    public int readDate() {
        System.out.println(RESERVATION_DATE.getMessage());
        while (true) {
            try {
                int date = convertInteger(Console.readLine());
                RestaurantValidator.checkReservationDate(date);
                return date;
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_INVALID_DATE.getMessage());
            }
        }
    }

    public OrderDto readOrder(int reservationDate, Message message) {
        System.out.println(message.getMessage());
        while (true) {
            try {
                return createOrderDto(reservationDate, Console.readLine());
            } catch (IllegalArgumentException | NullPointerException | IndexOutOfBoundsException e) {
                System.out.println(ERROR_INVALID_ORDER.getMessage());
            }
        }
    }

    public OrderDto readOrder(int reservationDate) {
        return readOrder(reservationDate, RESERVATION_ORDER);
    }

    private OrderDto createOrderDto(int reservationDate, String input) {
        OrderDto newOrder = new OrderDto(reservationDate);
        for (String dish : input.split(",")) {
            String[] dishAndCount = dish.split("-", 2);
            newOrder.addOrder(dishAndCount[0], convertInteger(dishAndCount[1]));
        }
        RestaurantValidator.checkDishNumber(newOrder);
        return newOrder;
    }

    private int convertInteger(String input) {
        try {
            int number = Integer.parseInt(input);
            if (number < 1) {
                throw new IllegalArgumentException();
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
