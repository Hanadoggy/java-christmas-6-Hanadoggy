package christmas.service;

import christmas.common.Range;
import christmas.entity.DiscountDetail;
import christmas.entity.Dish;
import christmas.entity.MainDish;
import christmas.entity.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class WeekendPromotionService implements PromotionService {
    private final String promotionName;
    private final List<Integer> duration;
    private final List<Dish> targetDishes;

    public WeekendPromotionService() {
        promotionName = "주말 할인";
        duration = createDuration();
        targetDishes = new ArrayList<>(Arrays.asList(MainDish.values()));
    }

    private List<Integer> createDuration() {
        List<Integer> duration = new ArrayList<>();

        for (int i = 1; i <= 31; i++) {
            if (i % 7 == 1 || i % 7 == 2) {
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
        for (Dish targetDish : targetDishes) {
            if (orderedDishes.contains(targetDish)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void apply(Order order, DiscountDetail discountDetail) {
        int basicDiscount = 2_023;

        int totalDiscount = 0;
        for (Dish targetDish : targetDishes) {
            totalDiscount += order.getDishNumber(targetDish) * basicDiscount;
        }
        discountDetail.addDetail(promotionName, totalDiscount);
    }

}
