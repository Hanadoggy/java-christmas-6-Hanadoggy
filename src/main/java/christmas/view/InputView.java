package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.Message;
import christmas.common.RestaurantValidator;
import christmas.dto.OrderDto;

public class InputView {

    public static int readDate(Message message) {
        System.out.println(message.getMessage());
        int date = convertInteger(Console.readLine());
        RestaurantValidator.checkReservationDate(date);
        return date;
    }

    public static OrderDto readOrder(int reservationDate, Message message) {
        System.out.println(message.getMessage());
        return createOrderDto(reservationDate, Console.readLine());
    }

    private static OrderDto createOrderDto(int reservationDate, String input) {
        try {
            OrderDto newOrder = new OrderDto(reservationDate);
            for (String dish : input.split(",")) {
                String[] dishAndCount = dish.split("-", 2);
                newOrder.addOrder(dishAndCount[0], convertInteger(dishAndCount[1]));
            }
            RestaurantValidator.checkDishNumber(newOrder);
            return newOrder;
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static int convertInteger(String input) {
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
