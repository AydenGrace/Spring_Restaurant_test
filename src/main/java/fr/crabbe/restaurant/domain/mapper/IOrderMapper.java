package fr.crabbe.restaurant.domain.mapper;

import fr.crabbe.restaurant.domain.entity.Order;
import fr.crabbe.restaurant.domain.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IOrderMapper {
    IOrderMapper INSTANCE = Mappers.getMapper(IOrderMapper.class);

    OrderDto orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDto dto);
}
