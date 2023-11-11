package christmas.service;

import christmas.entity.OrderStatement;
import christmas.entity.Beverage;
import christmas.entity.Dish;

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
    public boolean support(OrderStatement orderStatement) {
        return orderStatement.getOriginalPrice() >= 120_000;
    }

    @Override
    public int discount(OrderStatement orderStatement) {
        return targetDish.getPrice() * targetNumber;
    }

    @Override
    public String getName() {
        return promotionName;
    }

}
