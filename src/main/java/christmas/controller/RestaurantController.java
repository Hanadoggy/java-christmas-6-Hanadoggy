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
    private final RestaurantService restaurantService;

    public RestaurantController(List<PromotionService> promotionsInProgress) {
        restaurantService = new RestaurantService(promotionsInProgress);
    }

    public void acceptOrder() {
        int reservationDate = getReservationDate();
        OrderDto orderDto = getOrderDto(reservationDate);
        OutputView.printMenu(acceptOrder(reservationDate, orderDto));
    }
    
    private int getReservationDate() {
        Message message = Message.RESERVATION_DATE;
        while (true) {
            try {
                return InputView.readDate(message);
            } catch (IllegalArgumentException e) {
                message = Message.ERROR_INVALID_DATE;
            }
        }
    }

    private OrderDto getOrderDto(int reservationDate, Message message) {
        while (true) {
            try {
                return InputView.readOrder(reservationDate, message);
            } catch (IllegalArgumentException e) {
                message = Message.ERROR_INVALID_ORDER;
            }
        }
    }
    
    private OrderDto getOrderDto(int reservationDate) {
        return getOrderDto(reservationDate, Message.RESERVATION_ORDER);
    }
    
    private Order acceptOrder(int reservationDate, OrderDto orderDto) {
        while (true) {
            try {
                return restaurantService.performOrder(orderDto);
            } catch (IllegalArgumentException e) {
                orderDto = getOrderDto(reservationDate, Message.ERROR_INVALID_ORDER);
            }
        }   
    }

}
