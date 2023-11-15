package christmas.common;

import christmas.controller.RestaurantController;
import christmas.repository.RestaurantRepository;
import christmas.service.*;

import java.util.List;

public class AppConfig {

    public static RestaurantRepository getRestaurantRepository() {
        return new RestaurantRepository();
    }

    public static RestaurantService getRestaurantService() {
        return new RestaurantService(getPromotionsInProgress(), getRestaurantRepository());
    }

    public static RestaurantController getRestaurantController() {
        return new RestaurantController(getRestaurantService());
    }

    private static List<PromotionService> getPromotionsInProgress() {
        return List.of(
                new ChristmasDDayPromotionService(),
                new GiftPromotionService(),
                new SpecialPromotionService(),
                new WeekdayPromotionService(),
                new WeekendPromotionService()
        );
    }

}
