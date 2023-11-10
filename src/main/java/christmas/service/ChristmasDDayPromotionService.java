package christmas.service;

import christmas.common.Range;
import christmas.entity.OrderStatement;

import java.util.List;
import java.util.stream.IntStream;

public class ChristmasDDayPromotionService implements PromotionService {
    private final String promotionName;
    private final List<Integer> duration;
    private int today;

    public ChristmasDDayPromotionService() {
        promotionName = "크리스마스 디데이 할인";
        duration = IntStream.rangeClosed(1, 25).boxed().toList();
    }

    @Override
    public boolean support(int day) {
        today = day;
        return duration.contains(day);
    }

    @Override
    public boolean support(OrderStatement orderStatement) {
        return (orderStatement.getOriginalPrice() >= Range.MIN_PRICE.getValue());
    }

    @Override
    public int discount(OrderStatement orderStatement) {
        int basicDiscount = 1_000;
        int addedDiscount = 100;

        int totalDiscount = basicDiscount + addedDiscount * (today - 1);
        orderStatement.addDiscount(promotionName, totalDiscount);
        return totalDiscount;
    }

    @Override
    public String getPromotionName() {
        return promotionName;
    }

}
