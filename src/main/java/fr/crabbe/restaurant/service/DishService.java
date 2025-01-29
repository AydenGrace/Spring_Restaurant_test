package fr.crabbe.restaurant.service;

import fr.crabbe.restaurant.domain.entity.Dish;
import fr.crabbe.restaurant.domain.dto.DishDto;
import fr.crabbe.restaurant.domain.mapper.DishMapper;
import fr.crabbe.restaurant.exception.DishInOrderException;
import fr.crabbe.restaurant.exception.DishNotFoundException;
import fr.crabbe.restaurant.exception.DishNotModifiedException;
import fr.crabbe.restaurant.repository.IDishRepository;
import fr.crabbe.restaurant.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishService {
    private final IDishRepository dishRepository;
    private final IOrderRepository orderRepository;


    public List<DishDto> getAll() {
        List<DishDto> dishes = new ArrayList<>();
        for (Dish dish : dishRepository.findAll()) {
            dishes.add(DishMapper.toDto(dish));
        }
        return dishes;
    }

    public DishDto getByUuid(UUID uuid) throws DishNotFoundException{
        Optional<Dish> dish =  dishRepository.findByUuid(uuid);
        if(dish.isPresent()){
            return DishMapper.toDto(dish.get());
        } else {
            throw new DishNotFoundException();
        }
    }

    public void create(DishDto dto){
        dishRepository.save(DishMapper.toEntity(dto));
    }

    public void update(UUID uuid, DishDto dto) throws DishNotFoundException, DishNotModifiedException {
        dishRepository.findByUuid(uuid).ifPresentOrElse(dish -> {
            if (dto.getName() != null && !dto.getName().equals(dish.getName())) {
                dish.setName(dto.getName());
                dishRepository.save(dish);
            } else {
                throw new DishNotModifiedException();
            }
        }, () -> {
            throw new DishNotFoundException();
        });
    }

    public void delete(UUID uuid) throws DishNotFoundException, DishInOrderException {
        dishRepository.findByUuid(uuid).ifPresentOrElse(dish -> {
            //Verify if in Order
            if (orderRepository.findByDishes_Id(dish.getId()).isEmpty()) {
                dishRepository.delete(dish);
            } else {
                throw new DishInOrderException();
            }
        }, () -> {
            throw new DishNotFoundException();
        });
    }
}
