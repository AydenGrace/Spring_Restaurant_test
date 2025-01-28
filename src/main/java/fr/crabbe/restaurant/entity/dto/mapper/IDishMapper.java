package fr.crabbe.restaurant.entity.dto.mapper;

import fr.crabbe.restaurant.entity.Dish;
import fr.crabbe.restaurant.entity.dto.DishDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IDishMapper {
    IDishMapper INSTANCE = Mappers.getMapper(IDishMapper.class);

    DishDto dishToDishDto(Dish dish);
    Dish dishDtoToDish(DishDto dto);
}
