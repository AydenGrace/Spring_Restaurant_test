package fr.crabbe.restaurant.entity.dto.mapper;

import fr.crabbe.restaurant.domain.entity.Client;
import fr.crabbe.restaurant.domain.entity.Dish;
import fr.crabbe.restaurant.domain.entity.Order;
import fr.crabbe.restaurant.domain.dto.ClientDto;
import fr.crabbe.restaurant.domain.dto.DishDto;
import fr.crabbe.restaurant.domain.dto.OrderDto;
import fr.crabbe.restaurant.domain.mapper.IOrderMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class IOrderMapperTest {

    @Test
    void orderToOrderDto() {
        UUID uuid = UUID.randomUUID();
        Client client = new Client(15647L,UUID.randomUUID(),"TEST Test",new ArrayList<>());
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish(23486L,UUID.randomUUID(),"Tartiflette",new ArrayList<>()));

        Order order = new Order(897615L,uuid, LocalDate.now(),client,dishes);

        OrderDto orderDto = IOrderMapper.INSTANCE.orderToOrderDto(order);

        Assertions.assertNotNull(orderDto);
        Assertions.assertEquals(uuid,orderDto.getUuid());
        Assertions.assertEquals(client.getUuid(),orderDto.getClient().getUuid());
        Assertions.assertEquals(dishes.get(0).getUuid(),orderDto.getDishes().get(0).getUuid());

    }

    @Test
    void orderDtoToOrder() {
        UUID uuid = UUID.randomUUID();
        ClientDto clientDto = new ClientDto(UUID.randomUUID(),"TEST Test");
        List<DishDto> dishesDto = new ArrayList<>();
        dishesDto.add(new DishDto(UUID.randomUUID(),"Tartiflette"));

        OrderDto dto = new OrderDto(uuid, LocalDate.now(),clientDto,dishesDto);

        Order Order = IOrderMapper.INSTANCE.orderDtoToOrder(dto);

        Assertions.assertNotNull(Order);
        Assertions.assertEquals(uuid,Order.getUuid());
        Assertions.assertEquals(clientDto.getUuid(),Order.getClient().getUuid());
        Assertions.assertEquals(dishesDto.get(0).getUuid(),Order.getDishes().get(0).getUuid());
    }
}