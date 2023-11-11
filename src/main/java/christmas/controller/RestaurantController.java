package christmas.controller;

import christmas.entity.Dish;
import christmas.view.InputView;

import java.util.Map;

public class RestaurantController {
    private final InputView inputView;

    public RestaurantController() {
        inputView = new InputView();
    }

    public int getReservationDate() {
        return inputView.getReservationDate();
    }

    public Map<Dish, Integer> getOrder() {
        return inputView.getOrder();
    }

}
