package christmas.menu;

public enum Dessert implements Dish {
    CHOCOLATE_CAKE("초코케이크",15000),
    ICE_CREAM("아이스크림", 5000);

    private static final String type = "디저트";
    private final String name;
    private final int price;

    Dessert(String name, int price) {
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
