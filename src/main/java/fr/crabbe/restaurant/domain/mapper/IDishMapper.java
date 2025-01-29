package fr.crabbe.restaurant.domain.mapper;

import fr.crabbe.restaurant.domain.entity.Dish;
import fr.crabbe.restaurant.domain.dto.DishDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IDishMapper {
    IDishMapper INSTANCE = Mappers.getMapper(IDishMapper.class);

    DishDto dishToDishDto(Dish dish);
    Dish dishDtoToDish(DishDto dto);
}
