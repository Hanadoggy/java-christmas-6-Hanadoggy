package christmas.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OrderStatement {
    private final Map<Dish, Integer> orderedDishes;
    private final int reservationDay;
    private final Map<Dish, Integer> promotionDishes;
    private final Map<String, Integer> promotionDetails;

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

    public int getNumberOf(Dish dish) {
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

    public int getPriceOf(String promotion) {
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
        int discountPrice = 0;

        for (Dish dish : promotionDishes.keySet()) {
            discountPrice += promotionDishes.get(dish) * dish.getPrice();
        }
        for (String promotion : promotionDetails.keySet()) {
            discountPrice += promotionDetails.get(promotion);
        }
        return discountPrice;
    }

    public void addPromotionItem(Dish dish, int count) {
        promotionDishes.put(dish, promotionDishes.getOrDefault(dish, 0) + count);
    }

    public void addDiscount(String promotion, int discount) {
        promotionDetails.put(promotion, promotionDetails.getOrDefault(promotion, 0) + discount);
    }

}
