package christmas.menu;

public enum Appetizer implements Dish {
    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000);

    private static final String type = "에피타이저";
    private final String name;
    private final int price;

    Appetizer(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getType() {
        return type;
    }
}
