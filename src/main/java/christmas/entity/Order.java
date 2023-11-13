package christmas.entity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Order {
    private final Map<Dish, Integer> orderedDishes;
    private final int reservationDay;
    private DiscountDetail discountDetail;

    public Order(int reservationDay, Map<Dish, Integer> orderedDishes) {
        this.reservationDay = reservationDay;
        this.orderedDishes = orderedDishes;
    }

    public int getReservationDay() {
        return reservationDay;
    }

    public Set<Dish> getDishes() {
        return new HashSet<>(Set.copyOf(orderedDishes.keySet()));
    }

    public int getDishNumber(Dish dish) {
        return orderedDishes.getOrDefault(dish, 0);
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for (Dish dish : orderedDishes.keySet()) {
            totalPrice += orderedDishes.get(dish) * dish.getPrice();
        }
        return totalPrice;
    }

    public int getPurchasePrice() {
        return getTotalPrice() - discountDetail.getTotalDiscount() + discountDetail.getTotalGiftsPrice();
    }

    public void applyDiscount(DiscountDetail discountDetail) {
        this.discountDetail = discountDetail;
    }

    public DiscountDetail getDetails() {
        return discountDetail;
    }

}
