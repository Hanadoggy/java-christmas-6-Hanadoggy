package christmas.view;

import christmas.common.Message;
import christmas.common.RestaurantValidator;
import christmas.dto.OrderDto;

import static christmas.common.Message.RESERVATION_DATE;
import static christmas.common.Message.RESERVATION_ORDER;

public class InputViewStub extends InputView {
    public String input;

    public int readDate() {
        System.out.println(RESERVATION_DATE.getMessage());
        while (true) {
            try {
                int date = convertInteger(input);
                RestaurantValidator.checkReservationDate(date);
                return date;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public OrderDto readOrder(int reservationDate, Message message) {
        System.out.println(message.getMessage());
        while (true) {
            try {
                return createOrderDto(reservationDate, input);
            } catch (IllegalArgumentException | NullPointerException | IndexOutOfBoundsException e) {
                throw new IllegalArgumentException();
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
