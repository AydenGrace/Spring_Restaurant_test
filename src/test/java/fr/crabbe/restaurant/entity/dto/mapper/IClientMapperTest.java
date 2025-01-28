package fr.crabbe.restaurant.entity.dto.mapper;

import fr.crabbe.restaurant.entity.Client;
import fr.crabbe.restaurant.entity.dto.ClientDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class IClientMapperTest {

    @Test
    void clientToClientDto() {
        UUID uuid = UUID.randomUUID();
        String name = "TEST Test";
        Client client = new Client(10598L, uuid,name,new ArrayList<>());

        ClientDto dto = IClientMapper.INSTANCE.clientToClientDto(client);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(uuid,dto.getUuid());
        Assertions.assertEquals(name,dto.getName());
    }

    @Test
    void clientDtoToClient() {
        UUID uuid = UUID.randomUUID();
        String name = "TEST Test";
        ClientDto dto = new ClientDto(uuid,name);

        Client client = IClientMapper.INSTANCE.clientDtoToClient(dto);

        Assertions.assertNotNull(client);
        Assertions.assertEquals(uuid,client.getUuid());
        Assertions.assertEquals(name,client.getName());
    }
}