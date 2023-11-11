package christmas.service;

import christmas.controller.RestaurantController;

import java.util.List;

public class RestaurantStarter {
    private final RestaurantController restaurantController;

    public RestaurantStarter() {
        this.restaurantController = new RestaurantController(injectPromotionsInProgress());
    }

    private List<PromotionService> injectPromotionsInProgress() {
        return List.of(new ChristmasDDayPromotionService(),
                new GiftPromotionService(),
                new SpecialPromotionService(),
                new WeekdayPromotionService(),
                new WeekendPromotionService());
    }

    public void run() {
        restaurantController.acceptOrder();
    }

}
