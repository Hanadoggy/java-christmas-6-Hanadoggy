package christmas.service;

import christmas.entity.Dish;
import christmas.entity.OrderStatement;

import java.util.List;
import java.util.Map;

public class RestaurantService {
    private final List<PromotionService> promotionsInProgress;

    public RestaurantService(List<PromotionService> promotionsInProgress) {
        this.promotionsInProgress = promotionsInProgress;
    }

    public OrderStatement performOrder(Map<Dish, Integer> order, int reservationDay) {
        OrderStatement orderStatement = new OrderStatement(order, reservationDay);
        applyPromotions(orderStatement);
        return orderStatement;
    }

    private void applyPromotions(OrderStatement order) {
        for (PromotionService promotion : promotionsInProgress) {
            if (promotion.support(order)) {
                order.addDiscount(promotion.getName(), promotion.discount(order));
            }
        }
    }

}
