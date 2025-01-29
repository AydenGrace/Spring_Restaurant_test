package fr.crabbe.restaurant.entity.dto.mapper;

import fr.crabbe.restaurant.domain.entity.Dish;
import fr.crabbe.restaurant.domain.dto.DishDto;
import fr.crabbe.restaurant.domain.mapper.IDishMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

class IDishMapperTest {

    @Test
    void dishToDishDto() {
        UUID uuid = UUID.randomUUID();
        String name = "Tartiflette";
        Dish Dish = new Dish(10598L, uuid,name,new ArrayList<>());

        DishDto dto = IDishMapper.INSTANCE.dishToDishDto(Dish);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(uuid,dto.getUuid());
        Assertions.assertEquals(name,dto.getName());
    }

    @Test
    void dishDtoToDish() {
        UUID uuid = UUID.randomUUID();
        String name = "Tartiflette";
        DishDto dto = new DishDto(uuid,name);

        Dish Dish = IDishMapper.INSTANCE.dishDtoToDish(dto);

        Assertions.assertNotNull(Dish);
        Assertions.assertEquals(uuid,Dish.getUuid());
        Assertions.assertEquals(name,Dish.getName());
    }
}