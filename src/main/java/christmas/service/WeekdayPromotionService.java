package christmas.service;

import christmas.common.Range;
import christmas.entity.Dessert;
import christmas.entity.DiscountDetail;
import christmas.entity.Dish;
import christmas.entity.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class WeekdayPromotionService implements PromotionService {
    private final String promotionName;
    private final List<Integer> duration;
    private final List<Dish> targetDishes;

    public WeekdayPromotionService() {
        promotionName = "평일 할인";
        duration = createDuration();
        targetDishes = new ArrayList<>(Arrays.asList(Dessert.values()));
    }

    private List<Integer> createDuration() {
        List<Integer> duration = new ArrayList<>();

        for (int i = 1; i <= 31; i++) {
            if (i % 7 == 0 || i % 7 > 2) {
                duration.add(i);
            }
        }
        return duration;
    }

    @Override
    public boolean support(Order order) {
        if (!duration.contains(order.getReservationDay()) ||
                order.getTotalPrice() < Range.MIN_PRICE.getValue()) {
            return false;
        }
        Set<Dish> orderedDishes = order.getDishes();
        for (Dish dish : targetDishes) {
            if (orderedDishes.contains(dish)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void apply(Order order, DiscountDetail discountDetail) {
        int basicDiscount = 2_023;

        int totalDiscount = 0;
        for (Dish dish : targetDishes) {
            totalDiscount += order.getDishNumber(dish) * basicDiscount;
        }
        discountDetail.addDetail(promotionName, totalDiscount);
    }
}
