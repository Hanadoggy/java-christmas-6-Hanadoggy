package christmas.common;

public enum Message {
    RESERVATION_DATE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)"),
    RESERVATION_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ORDER_DETAIL("12월 cond일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
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

}
