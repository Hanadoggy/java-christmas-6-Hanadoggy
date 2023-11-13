package christmas.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderDto {
    private final int reservationDate;
    private final Map<String, Integer> dishes;

    public OrderDto(int reservationDate) {
        this.reservationDate = reservationDate;
        dishes = new HashMap<>();
    }

    public void addOrder(String dish, int number) {
        dishes.put(dish, dishes.getOrDefault(dish, 0) + number);
    }

    public Set<String> getOrders() {
        return new HashSet<>(Set.copyOf(dishes.keySet()));
    }

    public int getNumber(String dishName) {
        return dishes.getOrDefault(dishName, 0);
    }

    public int getReservationDate() {
        return reservationDate;
    }

}
