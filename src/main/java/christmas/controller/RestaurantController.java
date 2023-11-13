package christmas.controller;

import christmas.common.Message;
import christmas.dto.OrderDto;
import christmas.entity.Order;
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
        int reservationDate = inputView.readDate();
        OrderDto orderDto = inputView.readOrder(reservationDate);
        Order order = null;

        while (order == null) {
            try {
                order = restaurantService.performOrder(orderDto);
            } catch (IllegalArgumentException e) {
                orderDto = inputView.readOrder(reservationDate, Message.ERROR_INVALID_ORDER);
            }
        }
        outputView.printMenu(restaurantService.performOrder(orderDto));
    }

}
