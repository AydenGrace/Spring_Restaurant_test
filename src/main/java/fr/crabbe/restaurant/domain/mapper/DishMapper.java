package fr.crabbe.restaurant.domain.mapper;

import fr.crabbe.restaurant.domain.entity.Dish;
import fr.crabbe.restaurant.domain.dto.DishDto;

import java.util.ArrayList;

public class DishMapper {
    public static DishDto toDto(Dish dish) {
        return new DishDto(dish.getUuid(), dish.getName());
    }

    public static Dish toEntity(DishDto dto) {
        return new Dish(null, dto.getUuid(), dto.getName(), new ArrayList<>());
    }
}
