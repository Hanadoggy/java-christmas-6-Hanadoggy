package christmas.common;

public enum Range {
    MIN_PRICE(10_000),
    START_DAY(1),
    END_DAY(31),
    MAX_MENU(20);

    private final int value;

    Range(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
