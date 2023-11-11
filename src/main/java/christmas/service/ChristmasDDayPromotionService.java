package christmas.service;

import christmas.common.Range;
import christmas.entity.OrderStatement;

import java.util.List;
import java.util.stream.IntStream;

public class ChristmasDDayPromotionService implements PromotionService {
    private final String promotionName;
    private final List<Integer> duration;

    public ChristmasDDayPromotionService() {
        promotionName = "크리스마스 디데이 할인";
        duration = IntStream.rangeClosed(1, 25).boxed().toList();
    }

    @Override
    public boolean support(OrderStatement orderStatement) {
        return duration.contains(orderStatement.getReservationDay()) &&
                orderStatement.getOriginalPrice() >= Range.MIN_PRICE.getValue();
    }

    @Override
    public int discount(OrderStatement orderStatement) {
        int basicDiscount = 1_000;
        int addedDiscount = 100;

        return basicDiscount + addedDiscount * (orderStatement.getReservationDay() - 1);
    }

    @Override
    public String getName() {
        return promotionName;
    }

}
