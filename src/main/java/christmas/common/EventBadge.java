package christmas.common;

public enum EventBadge {
    STAR(5_000, "별"),
    TREE(10_000, "트리"),
    SANTA(20_000, "산타");

    private final int price;
    private final String name;

    EventBadge(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public static String getBadge(int price) {
        if (price >= SANTA.price) {
            return SANTA.name;
        }
        if (price >= TREE.price) {
            return TREE.name;
        }
        if (price >= STAR.price) {
            return STAR.name;
        }
        return Message.EMPTY.getMessage();
    }

}
