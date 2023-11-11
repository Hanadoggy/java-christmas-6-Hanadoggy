package christmas.controller;

import christmas.entity.Dish;
import christmas.entity.OrderStatement;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class RestaurantController {
    private final InputView inputView;
    private final OutputView outputView;

    public RestaurantController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public int getReservationDate() {
        return inputView.getReservationDate();
    }

    public Map<Dish, Integer> getOrder() {
        return inputView.getOrder();
    }

    public void printResult(OrderStatement orderStatement) {
        outputView.printOrderDetails(orderStatement);
    }

}
