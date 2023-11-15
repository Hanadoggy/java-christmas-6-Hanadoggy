package christmas.service;

import christmas.dto.OrderDto;
import christmas.entity.DiscountDetail;
import christmas.entity.Dish;
import christmas.entity.Order;
import christmas.repository.RestaurantRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RestaurantService {
    private final List<PromotionService> promotionsInProgress;
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(List<PromotionService> promotions, RestaurantRepository repository) {
        this.promotionsInProgress = promotions;
        this.restaurantRepository = repository;
    }

    public Order performOrder(OrderDto orderDto) {
        Order order = createOrder(orderDto);
        DiscountDetail discountDetail = new DiscountDetail();
        for (PromotionService promotion : promotionsInProgress) {
            if (promotion.support(order)) {
                promotion.apply(order, discountDetail);
            }
        }
        order.applyDiscount(discountDetail);
        return order;
    }

    private Order createOrder(OrderDto orderDto) {
        Map<Dish, Integer> orderDishes = new HashMap<>();

        for (String dishName : orderDto.getOrders()) {
            orderDishes.put(restaurantRepository.findDish(dishName), orderDto.getNumber(dishName));
        }
        Order order = new Order(orderDto.getReservationDate(), orderDishes);
        checkMinimumDish(order);
        return order;
    }

    private void checkMinimumDish(Order order) {
        Set<Dish> unessentialDishes = restaurantRepository.findUnessentialDishes();

        for (Dish dish : order.getDishes()) {
            if (!unessentialDishes.contains(dish)) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }

}
