package christmas.service;

import christmas.common.Range;
import christmas.entity.OrderStatement;

import java.util.ArrayList;
import java.util.List;

public class SpecialPromotionService implements PromotionService {
    private final String promotionName;
    private final List<Integer> duration;

    public SpecialPromotionService() {
        promotionName = "특별 할인";
        duration = createDuration();
    }

    private List<Integer> createDuration() {
        List<Integer> duration = new ArrayList<>();

        for (int i = 1; i <= 31; i++) {
            if (i % 7 == 3) {
                duration.add(i);
            }
        }
        duration.add(25);
        return duration;
    }

    @Override
    public boolean support(int day) {
        return duration.contains(day);
    }

    @Override
    public boolean support(OrderStatement orderStatement) {
        return (orderStatement.getOriginalPrice() >= Range.MIN_PRICE.getValue());
    }

    @Override
    public int discount(OrderStatement orderStatement) {
        int basicDiscount = 1_000;

        orderStatement.addDiscount(promotionName, basicDiscount);
        return basicDiscount;
    }

    @Override
    public String getPromotionName() {
        return promotionName;
    }
}
