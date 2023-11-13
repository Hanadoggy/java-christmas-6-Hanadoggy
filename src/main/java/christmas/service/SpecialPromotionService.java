package christmas.service;

import christmas.common.Range;
import christmas.entity.DiscountDetail;
import christmas.entity.Order;

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
    public boolean support(Order order) {
        return duration.contains(order.getReservationDay()) &&
                order.getTotalPrice() >= Range.MIN_PRICE.getValue();
    }

    @Override
    public void apply(Order order, DiscountDetail discountDetail) {
        int discount = 1_000;
        discountDetail.addDetail(promotionName, discount);
    }

}
