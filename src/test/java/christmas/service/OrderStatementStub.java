package christmas.service;

import christmas.entity.Dish;
import christmas.entity.OrderStatement;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OrderStatementStub extends OrderStatement {
    Map<Dish, Integer> testOrderedDishes = new HashMap<>();
    private int price;
    private int day;

    public OrderStatementStub() {
        super(null, 1);
        price = 0;
        day = 0;
    }

    @Override
    public Set<Dish> getOrderedDishes() {
        return Set.copyOf(testOrderedDishes.keySet());
    }

    public void addDish(Dish dish, int number) {
        testOrderedDishes.put(dish, number);
    }

    public void clearDish() {
        testOrderedDishes.clear();
    }

    @Override
    public int getNumberOfDish(Dish dish) {
        return testOrderedDishes.getOrDefault(dish, 0);
    }

    public void setReturnPrice(int cond) {
        this.price = cond;
    }

    @Override
    public int getOriginalPrice() {
        return price;
    }

    @Override
    public int getDiscountPrice() {
        return price;
    }

    @Override
    public int getReservationDay() {
        return day;
    }

    public void setReservationDay(int day) {
        this.day = day;
    }

    @Override
    public void addPromotionItem(Dish dish, int count) {
    }

    @Override
    public void addDiscount(String promotion, int discount) {
    }

}
