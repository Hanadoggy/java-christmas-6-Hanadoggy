package christmas.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OrderStatement {
    private final Map<Dish, Integer> numberOfOrderedDishes;
    private Map<Dish, Integer> promotionDishes;
    private Map<String, Integer> promotionDetails;

    public OrderStatement(Map<Dish, Integer> numberOfOrderedDishes) {
        this.numberOfOrderedDishes = numberOfOrderedDishes;
        promotionDishes = new HashMap<>();
        promotionDetails = new HashMap<>();
    }

    public Set<Dish> getOrderedDishes() {
        return Set.copyOf(numberOfOrderedDishes.keySet());
    }

    public int getNumberOfDish(Dish dish) {
        return numberOfOrderedDishes.getOrDefault(dish, 0);
    }

    public int getOriginalPrice() {
        int originalPrice = 0;

        for (Dish dish : numberOfOrderedDishes.keySet()) {
            originalPrice += numberOfOrderedDishes.get(dish) * dish.getPrice();
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
