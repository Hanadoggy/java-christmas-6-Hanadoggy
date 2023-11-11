package christmas;

import christmas.service.RestaurantStarter;

public class Application {

    public static void main(String[] args) {
        RestaurantStarter restaurantStarter = new RestaurantStarter();
        restaurantStarter.run();
    }

}
