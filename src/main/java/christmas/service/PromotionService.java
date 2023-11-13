package christmas.service;

import christmas.entity.DiscountDetail;
import christmas.entity.Order;

public interface PromotionService {
    boolean support(Order order);
    void apply(Order order, DiscountDetail discountDetail);
}
