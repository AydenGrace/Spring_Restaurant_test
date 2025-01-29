package fr.crabbe.restaurant.domain.mapper;

import fr.crabbe.restaurant.domain.entity.Dish;
import fr.crabbe.restaurant.domain.entity.Order;
import fr.crabbe.restaurant.domain.dto.DishDto;
import fr.crabbe.restaurant.domain.dto.OrderDto;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setUuid(order.getUuid());
        dto.setOrderDate(order.getOrderDate());
        dto.setClient(ClientMapper.toDto(order.getClient()));
        List<DishDto> dishes = new ArrayList<>();
        for (Dish dish : order.getDishes()) {
            dishes.add(DishMapper.toDto(dish));
        }
        dto.setDishes(dishes);

        return dto;
    }

    public static Order toEntity(OrderDto dto) {
        Order order = new Order();
        order.setUuid(dto.getUuid());
        order.setOrderDate(dto.getOrderDate());
        order.setClient(ClientMapper.toEntity(dto.getClient()));
        List<Dish> dishes = new ArrayList<>();
        for (DishDto dish : dto.getDishes()) {
            dishes.add(DishMapper.toEntity(dish));
        }
        order.setDishes(dishes);

        return order;
    }
}
