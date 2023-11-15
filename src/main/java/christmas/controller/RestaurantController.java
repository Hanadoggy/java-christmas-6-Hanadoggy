package christmas.controller;

import christmas.common.Message;
import christmas.dto.OrderDto;
import christmas.entity.Order;
import christmas.service.RestaurantService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService service) {
        this.restaurantService = service;
    }

    public void acceptOrder() {
        int reservationDate = readValidDate();
        Order order = readAndReceiveOrder(reservationDate);
        OutputView.printMenu(order);
    }
    
    private int readValidDate() {
        Message message = Message.RESERVATION_DATE;
        while (true) {
            try {
                return InputView.readDate(message);
            } catch (IllegalArgumentException e) {
                message = Message.ERROR_INVALID_DATE;
            }
        }
    }

    private Order readAndReceiveOrder(int reservationDate) {
        Message message = Message.RESERVATION_ORDER;
        while (true) {
            try {
                OrderDto orderDto = InputView.readOrder(reservationDate, message);
                return restaurantService.performOrder(orderDto);
            } catch (IllegalArgumentException e) {
                message = Message.ERROR_INVALID_ORDER;
            }
        }
    }

}
