package fr.crabbe.restaurant.domain.mapper;

import fr.crabbe.restaurant.domain.entity.Client;
import fr.crabbe.restaurant.domain.dto.ClientDto;

import java.util.ArrayList;

public class ClientMapper {
    public static ClientDto toDto(Client client) {
        return new ClientDto(client.getUuid(), client.getName());
    }

    public static Client toEntity(ClientDto dto) {
        return new Client(null, dto.getUuid(), dto.getName(), new ArrayList<>());
    }
}
