package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.RestaurantValidator;
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

        System.out.println(RESERVATION_DATE.getMessage());
        while (inputDate == 0) {
            inputDate = getValidatedDate(Console.readLine());
        }
        return inputDate;
    }

    public int getValidatedDate(String input) {
        try {
            return validator.convertReservationDate(input);
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_INVALID_DATE.getMessage());
            return 0;
        }
    }

    public Map<Dish, Integer> getOrder() {
        Map<Dish, Integer> order = null;

        System.out.println(RESERVATION_ORDER);
        while (order == null) {
            order = getValidatedOrder(Console.readLine());
        }
        return order;
    }

    private Map<Dish, Integer> getValidatedOrder(String input) {
        try {
            return validator.convertOrder(input);
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_INVALID_ORDER);
            return null;
        }
    }

}
