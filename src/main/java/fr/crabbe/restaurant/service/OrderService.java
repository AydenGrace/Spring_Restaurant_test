package fr.crabbe.restaurant.service;

import fr.crabbe.restaurant.entity.Dish;
import fr.crabbe.restaurant.entity.Order;
import fr.crabbe.restaurant.entity.dto.OrderDto;
import fr.crabbe.restaurant.entity.dto.mapper.OrderMapper;
import fr.crabbe.restaurant.exception.ClientNotFoundException;
import fr.crabbe.restaurant.exception.DishNotFoundException;
import fr.crabbe.restaurant.exception.OrderNotFoundException;
import fr.crabbe.restaurant.repository.IClientRepository;
import fr.crabbe.restaurant.repository.IDishRepository;
import fr.crabbe.restaurant.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final IDishRepository dishRepository;
    private final IClientRepository clientRepository;
    private final IOrderRepository orderRepository;

    public List<OrderDto> getAll() {
        List<OrderDto> orders = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            orders.add(OrderMapper.toDto(order));
        }
        return orders;
    }

    public OrderDto getByUuid(UUID uuid) throws OrderNotFoundException {
        return OrderMapper.toDto(orderRepository.findByUuid(uuid).orElseThrow(OrderNotFoundException::new));
    }

    public List<OrderDto> getClientOrders(UUID uuid) {
        List<OrderDto> orders = new ArrayList<>();
        for (Order order : orderRepository.findByClient_Uuid(uuid)) {
            orders.add(OrderMapper.toDto(order));
        }
        return orders;
    }

    public void create(OrderDto dto) throws ClientNotFoundException, DishNotFoundException {
        if (dto.getClient() == null) throw new ClientNotFoundException("An order must have a client");
        if (dto.getDishes() == null || dto.getDishes().isEmpty())
            throw new DishNotFoundException("An order must have at least one dish");
        if (dto.getOrderDate() == null) dto.setOrderDate(LocalDate.now());
        Order order = OrderMapper.toEntity(dto);

        clientRepository.findByUuid(order.getClient().getUuid()).ifPresentOrElse(order::setClient, () -> {
            throw new ClientNotFoundException("Client with UUID " + order.getClient().getUuid() + " not found.");
        });

        for (Dish dish : order.getDishes()) {
            dishRepository.findByUuid(dish.getUuid()).ifPresentOrElse(dishFound->{
                dish.setId(dishFound.getId());
            },()->{
                throw new DishNotFoundException("Dish with UUID " + dish.getUuid() + " not found.");
            });
        }

        orderRepository.save(order);
    }


}
