package christmas.service;

import christmas.controller.RestaurantController;
import christmas.entity.Dish;
import christmas.entity.OrderStatement;

import java.util.List;
import java.util.Map;

public class RestaurantStarter {
    private final RestaurantController restaurantController;
    private final RestaurantService restaurantService;

    public RestaurantStarter() {
        restaurantController = new RestaurantController();
        restaurantService = new RestaurantService(injectPromotionsInProgress());
    }

    private List<PromotionService> injectPromotionsInProgress() {
        return List.of(new ChristmasDDayPromotionService(),
                new GiftPromotionService(),
                new SpecialPromotionService(),
                new WeekdayPromotionService(),
                new WeekendPromotionService());
    }

    public void run() {
        int reservationDate = restaurantController.getReservationDate();
        Map<Dish, Integer> order = restaurantController.getOrder();
        OrderStatement orderStatement = restaurantService.performOrder(order, reservationDate);
        restaurantController.printResult(orderStatement);
    }

}
