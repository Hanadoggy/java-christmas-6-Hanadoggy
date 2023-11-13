package christmas.service;

import christmas.entity.Beverage;
import christmas.entity.DiscountDetail;
import christmas.entity.Dish;
import christmas.entity.Order;

public class GiftPromotionService implements PromotionService {
    private final String promotionName;
    private final Dish targetDish;
    private final int targetNumber;

    public GiftPromotionService() {
        promotionName = "증정 이벤트";
        targetDish = Beverage.CHAMPAGNE;
        targetNumber = 1;
    }

    @Override
    public boolean support(Order order) {
        return order.getTotalPrice() >= 120_000;
    }

    @Override
    public void apply(Order order, DiscountDetail discountDetail) {
        int discount = targetDish.getPrice() * targetNumber;
        discountDetail.addGift(targetDish, targetNumber);
        discountDetail.addDetail(promotionName, discount);
    }

}
