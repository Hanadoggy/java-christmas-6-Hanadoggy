package christmas.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OrderStatement {
    private final Map<Dish, Integer> orderedDishes;
    private final Map<Dish, Integer> promotionDishes;
    private final Map<String, Integer> promotionDetails;
    private final int reservationDay;

    public OrderStatement(Map<Dish, Integer> orderedDishes, int reservationDay) {
        this.orderedDishes = orderedDishes;
        this.reservationDay = reservationDay;
        promotionDishes = new HashMap<>();
        promotionDetails = new HashMap<>();
    }

    public int getReservationDay() {
        return reservationDay;
    }

    public Set<Dish> getOrderedDishes() {
        return Set.copyOf(orderedDishes.keySet());
    }

    public int getNumber(Dish dish) {
        return orderedDishes.getOrDefault(dish, 0);
    }

    public Set<Dish> getPromotionDishes() {
        return Set.copyOf(promotionDishes.keySet());
    }

    public int getNumberOfPromotion(Dish dish) {
        return promotionDishes.getOrDefault(dish, 0);
    }

    public Set<String> getPromotionDetails() {
        return Set.copyOf(promotionDetails.keySet());
    }

    public int getPromotionPrice(String promotion) {
        return promotionDetails.getOrDefault(promotion, 0);
    }

    public int getOriginalPrice() {
        int originalPrice = 0;

        for (Dish dish : orderedDishes.keySet()) {
            originalPrice += orderedDishes.get(dish) * dish.getPrice();
        }
        return originalPrice;
    }

    public int getDiscountPrice() {
        int totalDiscount = 0;

        for (int discount : promotionDetails.values()) {
            totalDiscount += discount;
        }
        return totalDiscount;
    }

    public int getPurchasePrice() {
        return getOriginalPrice() - getDiscountPrice() + getPromotionItemPrice();
    }

    public int getPromotionItemPrice() {
        int promotionItemPrice = 0;

        for (Dish dish : promotionDishes.keySet()) {
            promotionItemPrice += promotionDishes.get(dish) * dish.getPrice();
        }
        return promotionItemPrice;
    }

    public void addPromotionItem(Dish dish, int count) {
        promotionDishes.put(dish, count);
    }

    public void addDiscount(String promotion, int discount) {
        promotionDetails.put(promotion, discount);
    }

}
