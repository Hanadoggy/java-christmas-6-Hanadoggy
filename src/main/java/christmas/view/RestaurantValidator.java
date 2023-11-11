package christmas.view;

import christmas.common.Range;
import christmas.entity.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

public class RestaurantValidator {
    Map<String, Dish> allDishes;

    public RestaurantValidator() {
        allDishes = new HashMap<>();
        addAllDishes();
    }

    private void addAllDishes() {
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

    public int convertReservationDate(String input) {
        int day = convertInteger(input);

        if (day < Range.START_DAY.getValue() || day > Range.END_DAY.getValue()) {
            throw new IllegalArgumentException();
        }
        return day;
    }

    public Map<Dish, Integer> convertOrder(String input) {
        try {
            Map<Dish, Integer> orders = getOrder(input.split(","));
            checkCondition(orders);
            return orders;
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Map<Dish, Integer> getOrder(String[] splitOrders) {
        try {
            Map<Dish, Integer> orders = new HashMap<>();
            for (String order : splitOrders) {
                String[] splitOrder = order.split("-");
                orders.put(allDishes.get(splitOrder[0]), convertInteger(splitOrder[1]));
            }
            return orders;
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void checkCondition(Map<Dish, Integer> orders) {
        int count = 0;

        for (int number : orders.values()) {
            count += number;
            if (count > Range.MAX_MENU.getValue()) {
                throw new IllegalArgumentException();
            }
        }
        checkMinimumDish(orders.keySet());
    }

    private void checkMinimumDish(Set<Dish> orderedDishes) {
        Set<Dish> copyOfAllDishes = new HashSet<>(allDishes.values());

        for (Dish dish : Beverage.values()) {
            copyOfAllDishes.remove(dish);
        }
        for (Dish orderedDish : orderedDishes) {
            if (copyOfAllDishes.contains(orderedDish)) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    private int convertInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
