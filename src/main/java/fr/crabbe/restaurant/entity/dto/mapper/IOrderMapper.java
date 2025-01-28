package fr.crabbe.restaurant.entity.dto.mapper;

import fr.crabbe.restaurant.entity.Order;
import fr.crabbe.restaurant.entity.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IOrderMapper {
    IOrderMapper INSTANCE = Mappers.getMapper(IOrderMapper.class);

    OrderDto orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDto dto);
}
