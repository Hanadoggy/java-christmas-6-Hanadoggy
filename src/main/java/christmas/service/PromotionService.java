package christmas.service;

import christmas.entity.OrderStatement;

public interface PromotionService {
    boolean support(int day);
    boolean support(OrderStatement orderStatement);
    int discount(OrderStatement orderStatement);
    String getPromotionName();
}
