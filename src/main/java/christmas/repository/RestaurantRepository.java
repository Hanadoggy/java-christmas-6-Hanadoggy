package christmas.repository;

import christmas.entity.*;

import java.util.*;

public class RestaurantRepository {
    private final Map<String, Dish> allDishes;

    public RestaurantRepository() {
        allDishes = new HashMap<>();
        init();
    }

    private void init() {
        addDishes(Appetizer.values());
        addDishes(Beverage.values());
        addDishes(Dessert.values());
        addDishes(MainDish.values());
    }

    private void addDishes(Dish[] dishes) {
        for (Dish dish : dishes) {
            allDishes.put(dish.getName(), dish);
        }
    }

    public Dish findDish(String dishName) {
        if (allDishes.containsKey(dishName)) {
            return allDishes.get(dishName);
        }
        throw new IllegalArgumentException();
    }

    public Set<Dish> findUnessentialDishes() {
        return new HashSet<>(Arrays.asList(Beverage.values()));
    }

}
