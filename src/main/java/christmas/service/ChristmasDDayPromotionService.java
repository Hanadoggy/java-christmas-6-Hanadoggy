package christmas.service;

import christmas.common.Range;
import christmas.entity.DiscountDetail;
import christmas.entity.Order;

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
    public boolean support(Order order) {
        return duration.contains(order.getReservationDay()) &&
                order.getTotalPrice() >= Range.MIN_PRICE.getValue();
    }

    @Override
    public void apply(Order order, DiscountDetail discountDetail) {
        int basicDiscount = 1_000;
        int addedDiscount = 100;
        int totalDiscount = basicDiscount + addedDiscount * (order.getReservationDay() - 1);
        discountDetail.addDetail(promotionName, totalDiscount);
    }

}
