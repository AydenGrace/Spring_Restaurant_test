package fr.crabbe.restaurant.repository;

import fr.crabbe.restaurant.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findByUuid(UUID uuid);
    List<Order> findByDishes_Id(Long id);
    List<Order> findByClient_Uuid(UUID uuid);
}
