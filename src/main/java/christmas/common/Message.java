package christmas.common;

import christmas.entity.Dish;

import java.text.NumberFormat;
import java.util.Locale;

public enum Message {
    RESERVATION_DATE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)"),
    RESERVATION_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ORDER_DETAIL("12월 cond일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    ORDER_DISHES("<주문 메뉴>\n"),
    ORDER_ORIGINAL_PRICE("<할인 전 총주문 금액>\n"),
    ORDER_PROMOTION_ITEM("<증정 메뉴>\n"),
    ORDER_PROMOTION_DETAIL("<혜택 내역>\n"),
    ORDER_TOTAL_DISCOUNT("<총혜택 금액>\n"),
    ORDER_PURCHASE_PRICE("<할인 후 예상 결제 금액>\n"),
    EVENT_BADGE("<12월 이벤트 배지>\n"),
    EMPTY("없음\n"),
    ERROR_INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ERROR_INVALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(int condition) {
        return message.replace("cond", Integer.toString(condition));
    }

    public static String dishFormat(Dish dish, int count) {
        return String.format("%s %d개\n", dish.getName(), count);
    }

    public static String priceFormat(int price) {
        return NumberFormat.getNumberInstance(Locale.US).format(price) + "원\n";
    }

    public static String promotionFormat(String promotion, int price) {
        return String.format("%s: %s", promotion, priceFormat(price));
    }

}
