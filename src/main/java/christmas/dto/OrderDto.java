package christmas.dto;

import christmas.common.RestaurantValidator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderDto {
    private final int reservationDate;
    private final Map<String, Integer> menu;

    public OrderDto(int reservationDate, Map<String, Integer> menu) {
        this.reservationDate = reservationDate;
        this.menu = menu;
        RestaurantValidator.checkDishNumber(this);
    }

    public Set<String> getOrders() {
        return new HashSet<>(Set.copyOf(menu.keySet()));
    }

    public int getNumber(String dishName) {
        return menu.getOrDefault(dishName, 0);
    }

    public int getReservationDate() {
        return reservationDate;
    }

}
