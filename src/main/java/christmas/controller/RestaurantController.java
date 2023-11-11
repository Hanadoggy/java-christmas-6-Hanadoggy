package christmas.controller;

import christmas.entity.OrderStatement;
import christmas.service.PromotionService;
import christmas.service.RestaurantService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class RestaurantController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RestaurantService restaurantService;

    public RestaurantController(List<PromotionService> promotionsInProgress) {
        inputView = new InputView();
        outputView = new OutputView();
        restaurantService = new RestaurantService(promotionsInProgress);
    }

    public void acceptOrder() {
        outputView.printOrderDetails(createOrderStatement());
    }

    private OrderStatement createOrderStatement() {
        return restaurantService.performOrder(
                inputView.getReservationDate(),
                inputView.getOrder()
        );
    }

}
