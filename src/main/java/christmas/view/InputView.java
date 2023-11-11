package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.Message;
import christmas.entity.Dish;

import java.util.Map;

import static christmas.common.Message.*;

public class InputView {
    private final RestaurantValidator validator;

    public InputView() {
        this.validator = new RestaurantValidator();
    }

    public int getReservationDate() {
        int inputDate = 0;

        printMessage(RESERVATION_DATE);
        while (inputDate == 0) {
            inputDate = checkDate(Console.readLine());
        }
        return inputDate;
    }

    private int checkDate(String input) {
        try {
            return validator.convertReservationDate(input);
        } catch (IllegalArgumentException e) {
            printMessage(ERROR_INVALID_DATE);
            return 0;
        }
    }

    public Map<Dish, Integer> getOrder() {
        Map<Dish, Integer> order = null;

        printMessage(RESERVATION_ORDER);
        while (order == null) {
            order = checkOrder(Console.readLine());
        }
        return order;
    }

    private Map<Dish, Integer> checkOrder(String input) {
        try {
            return validator.convertOrder(input);
        } catch (IllegalArgumentException e) {
            printMessage(ERROR_INVALID_ORDER);
            return null;
        }
    }

    private void printMessage(Message message) {
        System.out.println(message.getMessage());
    }

}
