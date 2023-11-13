package christmas.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DiscountDetail {
    private final Map<Dish, Integer> gifts;
    private final Map<String, Integer> discounts;

    public DiscountDetail() {
        gifts = new HashMap<>();
        discounts = new HashMap<>();
    }

    public void addGift(Dish dish, int number) {
        gifts.put(dish, gifts.getOrDefault(dish, 0) + number);
    }

    public void addDetail(String promotion, int price) {
        discounts.put(promotion, discounts.getOrDefault(promotion, 0) + price);
    }

    public Set<Dish> getGifts() {
        return new HashSet<>(Set.copyOf(gifts.keySet()));
    }

    public int getGiftNumber(Dish dish) {
        return gifts.getOrDefault(dish, 0);
    }

    public Set<String> getDetails() {
        return new HashSet<>(Set.copyOf(discounts.keySet()));
    }

    public int getDiscount(String promotion) {
        return discounts.getOrDefault(promotion, 0);
    }

    public int getTotalDiscount() {
        int totalPrice = 0;

        for (String promotion : discounts.keySet()) {
            totalPrice += discounts.get(promotion);
        }
        return totalPrice;
    }

    public int getTotalGiftsPrice() {
        int totalPrice = 0;

        for (Dish gift : gifts.keySet()) {
            totalPrice += gifts.get(gift) * gift.getPrice();
        }
        return totalPrice;
    }

}
