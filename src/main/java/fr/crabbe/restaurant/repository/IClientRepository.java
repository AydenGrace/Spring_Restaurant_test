package fr.crabbe.restaurant.repository;

import fr.crabbe.restaurant.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByUuid(UUID uuid);
}
