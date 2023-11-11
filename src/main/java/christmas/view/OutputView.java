package christmas.view;

import christmas.common.EventBadge;
import christmas.common.Message;
import christmas.entity.Dish;
import christmas.entity.OrderStatement;

import static christmas.common.Message.*;

public class OutputView {

    public void printOrderDetails(OrderStatement order) {
        System.out.println(ORDER_DETAIL.getMessage(order.getReservationDay()));
        printOrderedDishes(order);
        printMessage(ORDER_ORIGINAL_PRICE, priceFormat(order.getOriginalPrice()));
        printPromotionItems(order);
        printPromotionDetails(order);
        printMessage(ORDER_TOTAL_DISCOUNT, priceFormat(-order.getDiscountPrice()));
        printMessage(ORDER_PURCHASE_PRICE, priceFormat(order.getPurchasePrice()));
        printMessage(EVENT_BADGE, EventBadge.getBadge(order.getDiscountPrice()));
    }

    private void printOrderedDishes(OrderStatement order) {
        StringBuilder detail = new StringBuilder(ORDER_DISHES.getMessage());

        for (Dish dish : order.getOrderedDishes()) {
            detail.append(dishFormat(dish, order.getNumber(dish)));
        }
        System.out.println(detail);
    }

    private void printPromotionItems(OrderStatement order) {
        if (order.getPromotionDishes().isEmpty()) {
            printMessage(ORDER_PROMOTION_ITEM, EMPTY);
            return;
        }
        StringBuilder detail = new StringBuilder(ORDER_PROMOTION_ITEM.getMessage());

        for (Dish dish : order.getPromotionDishes()) {
            detail.append(dishFormat(dish, order.getNumberOfPromotion(dish)));
        }
        System.out.println(detail);
    }

    private void printPromotionDetails(OrderStatement order) {
        if (order.getPromotionDetails().isEmpty()) {
            printMessage(ORDER_PROMOTION_DETAIL, EMPTY);
            return;
        }
        StringBuilder detail = new StringBuilder(ORDER_PROMOTION_DETAIL.getMessage());

        for (String promotion : order.getPromotionDetails()) {
            detail.append(detailFormat(promotion, -order.getPromotionPrice(promotion)));
        }
        System.out.println(detail);
    }

    private void printMessage(Message message, String added) {
        System.out.println(message.getMessage() + added);
    }

    private void printMessage(Message message1, Message message2) {
        printMessage(message1, message2.getMessage());
    }

}
