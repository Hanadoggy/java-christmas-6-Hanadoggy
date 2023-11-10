package christmas.controller;

import christmas.common.Range;
import christmas.entity.*;

import java.util.*;
import java.util.regex.PatternSyntaxException;

public class RestaurantValidator {
    Map<String, Dish> allDishes;

    public RestaurantValidator() {
        allDishes = new HashMap<>();
        init();
    }

    private void init() {
        addAllDishes(Appetizer.values());
        addAllDishes(Beverage.values());
        addAllDishes(Dessert.values());
        addAllDishes(MainDish.values());
    }

    private void addAllDishes(Dish[] dishes) {
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
            checkMaxOrder(orders.values());
            checkMinimumDish(orders.keySet());
            return orders;
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Map<Dish, Integer> getOrder(String[] split) {
        try {
            Map<Dish, Integer> orders = new HashMap<>();
            for (String word : split) {
                String[] order = word.split("-");
                orders.put(allDishes.get(order[0]), convertInteger(order[1]));
            }
            return orders;
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void checkMaxOrder(Collection<Integer> orderedDishesNumber) {
        int count = 0;
        for (int number : orderedDishesNumber) {
            count += number;
            if (count > Range.MAX_MENU.getValue()) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkMinimumDish(Set<Dish> orderedDishes) {
        Set<Dish> copyOfAllDishes = new HashSet<>(allDishes.values());
        for (Dish dish : Beverage.values()) {
            copyOfAllDishes.remove(dish);
        }
        for (Dish dish : copyOfAllDishes) {
            if (orderedDishes.contains(dish)) {
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
