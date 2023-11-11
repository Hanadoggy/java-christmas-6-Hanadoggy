package christmas.view;

import christmas.common.EventBadge;
import christmas.entity.Dish;
import christmas.entity.OrderStatement;

import java.text.NumberFormat;
import java.util.Locale;

import static christmas.common.Message.*;

public class OutputView {

    public void printOrderDetails(OrderStatement orderStatement) {
        System.out.println(ORDER_DETAIL.getMessage(orderStatement.getReservationDay()));
        System.out.println("\n");
        printOrderedDishes(orderStatement);
        printOriginalPrice(orderStatement);
        printPromotionItems(orderStatement);
        printPromotionDetails(orderStatement);
        printTotalPromotionPrice(orderStatement);
        printPurchasePrice(orderStatement);
        printEventBadge(orderStatement);
    }

    private void printOrderedDishes(OrderStatement order) {
        StringBuilder detail = new StringBuilder(ORDER_DISHES.getMessage());
        for (Dish orderedDish : order.getOrderedDishes()) {
            detail.append(String.format("%s %d개\n",
                    orderedDish.getName(),
                    order.getNumberOf(orderedDish)));
        }
        detail.append("\n");
        System.out.println(detail);
    }

    private void printOriginalPrice(OrderStatement order) {
        System.out.println(ORDER_ORIGINAL_PRICE.getMessage() +
                convertFormat(order.getOriginalPrice()) +
                "원\n\n");
    }

    private void printPromotionItems(OrderStatement order) {
        if (order.getPromotionDishes().isEmpty()) {
            System.out.println(ORDER_PROMOTION_ITEM.getMessage() + EMPTY.getMessage() + "\n");
            return;
        }
        StringBuilder detail = new StringBuilder(ORDER_PROMOTION_ITEM.getMessage());
        for (Dish dish : order.getPromotionDishes()) {
            detail.append(String.format("%s %d개\n", dish.getName(), order.getNumberOfPromotion(dish)));
        }
        detail.append("\n");
        System.out.println(detail);
    }

    private void printPromotionDetails(OrderStatement order) {
        if (order.getPromotionDetails().isEmpty()) {
            System.out.println(ORDER_PROMOTION_DETAIL.getMessage() + EMPTY.getMessage() + "\n");
            return;
        }
        StringBuilder detail = new StringBuilder(ORDER_PROMOTION_DETAIL.getMessage());
        for (String event : order.getPromotionDetails()) {
            detail.append(String.format("%s: -%s원\n", event, convertFormat(order.getPriceOf(event))));
        }
        detail.append("\n");
        System.out.println(detail);
    }

    private void printTotalPromotionPrice(OrderStatement order) {
        int totalDiscount = order.getDiscountPrice();
        StringBuilder detail = new StringBuilder(ORDER_TOTAL_DISCOUNT.getMessage());

        if (totalDiscount != 0) {
            detail.append("-");
        }
        detail.append(convertFormat(totalDiscount)).append("원\n\n");
        System.out.println(detail);
    }

    private void printPurchasePrice(OrderStatement order) {
        int purchasePrice = order.getOriginalPrice() - order.getDiscountPrice();
        System.out.println(ORDER_PURCHASE_PRICE.getMessage() +
                convertFormat(purchasePrice) +
                "원\n\n");
    }

    private void printEventBadge(OrderStatement order) {
        System.out.println(EVENT_BADGE.getMessage() + EventBadge.getBadge(order.getDiscountPrice()));

    }

    private String convertFormat(int price) {
        return NumberFormat.getNumberInstance(Locale.US).format(price);
    }

}
