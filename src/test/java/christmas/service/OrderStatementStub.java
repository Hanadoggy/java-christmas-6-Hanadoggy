package christmas.service;

import christmas.entity.Dish;
import christmas.entity.OrderStatement;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OrderStatementStub extends OrderStatement {
    Map<Dish, Integer> testOrderedDishes = new HashMap<>();
    private int price;

    public OrderStatementStub() {
        super(null);
        price = 0;
    }

    public Set<Dish> getOrderedDishes() {
        return Set.copyOf(testOrderedDishes.keySet());
    }

    public void addDish(Dish dish, int number) {
        testOrderedDishes.put(dish, number);
    }

    public int getNumberOfDish(Dish dish) {
        return testOrderedDishes.getOrDefault(dish, 0);
    }

    public void setReturnPrice(int cond) {
        this.price = cond;
    }

    public int getOriginalPrice() {
        return price;
    }

    public int getDiscountPrice() {
        return price;
    }

    public void addPromotionItem(Dish dish, int count) {
    }

    public void addDiscount(String promotion, int discount) {
    }

}
