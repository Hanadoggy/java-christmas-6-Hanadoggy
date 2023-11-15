package christmas;

import christmas.common.AppConfig;
import christmas.controller.RestaurantController;

public class Application {

    public static void main(String[] args) {
        RestaurantController restaurantController = AppConfig.getRestaurantController();
        restaurantController.acceptOrder();
    }

}
