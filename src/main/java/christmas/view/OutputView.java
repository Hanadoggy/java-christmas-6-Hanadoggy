package christmas.view;

import christmas.common.EventBadge;
import christmas.common.Message;
import christmas.entity.DiscountDetail;
import christmas.entity.Dish;
import christmas.entity.Order;

import static christmas.common.Message.*;

public class OutputView {

    public static void printMenu(Order order) {
        DiscountDetail discountDetail = order.getDetails();
        System.out.println(ORDER_DETAIL.getMessage(order.getReservationDay()));
        printOrderedDishes(order);
        printMessage(ORDER_ORIGINAL_PRICE, priceFormat(order.getTotalPrice()));
        printPromotionItems(discountDetail);
        printPromotionDetails(discountDetail);
        printMessage(ORDER_TOTAL_DISCOUNT, priceFormat(-discountDetail.getTotalDiscount()));
        printMessage(ORDER_PURCHASE_PRICE, priceFormat(order.getPurchasePrice()));
        printMessage(EVENT_BADGE, EventBadge.getBadge(discountDetail.getTotalDiscount()));
    }

    private static void printOrderedDishes(Order order) {
        StringBuilder detail = new StringBuilder(ORDER_DISHES.getMessage());

        for (Dish dish : order.getDishes()) {
            detail.append(dishFormat(dish, order.getDishNumber(dish)));
        }
        System.out.println(detail);
    }

    private static void printPromotionItems(DiscountDetail discountDetail) {
        if (discountDetail.getGifts().isEmpty()) {
            printMessage(ORDER_PROMOTION_ITEM, EMPTY);
            return;
        }
        StringBuilder message = new StringBuilder(ORDER_PROMOTION_ITEM.getMessage());

        for (Dish gift : discountDetail.getGifts()) {
            message.append(dishFormat(gift, discountDetail.getGiftNumber(gift)));
        }
        System.out.println(message);
    }

    private static void printPromotionDetails(DiscountDetail discountDetail) {
        if (discountDetail.getDetails().isEmpty()) {
            printMessage(ORDER_PROMOTION_DETAIL, EMPTY);
            return;
        }
        StringBuilder message = new StringBuilder(ORDER_PROMOTION_DETAIL.getMessage());

        for (String detail : discountDetail.getDetails()) {
            message.append(promotionFormat(detail, -discountDetail.getDiscount(detail)));
        }
        System.out.println(message);
    }

    private static void printMessage(Message message, String added) {
        System.out.println(message.getMessage() + added);
    }

    private static void printMessage(Message message1, Message message2) {
        printMessage(message1, message2.getMessage());
    }

}
