package fr.crabbe.restaurant.repository;

import fr.crabbe.restaurant.domain.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDishRepository extends JpaRepository<Dish,Long> {
    Optional<Dish> findByUuid(UUID uuid);
}
