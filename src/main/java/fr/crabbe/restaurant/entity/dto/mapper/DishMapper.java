package fr.crabbe.restaurant.entity.dto.mapper;

import fr.crabbe.restaurant.entity.Client;
import fr.crabbe.restaurant.entity.Dish;
import fr.crabbe.restaurant.entity.dto.ClientDto;
import fr.crabbe.restaurant.entity.dto.DishDto;

import java.util.ArrayList;

public class DishMapper {
    public static DishDto toDto(Dish dish) {
        return new DishDto(dish.getUuid(), dish.getName());
    }

    public static Dish toEntity(DishDto dto) {
        return new Dish(null, dto.getUuid(), dto.getName(), new ArrayList<>());
    }
}
