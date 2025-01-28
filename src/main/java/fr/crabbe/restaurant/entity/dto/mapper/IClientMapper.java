package fr.crabbe.restaurant.entity.dto.mapper;

import fr.crabbe.restaurant.entity.Client;
import fr.crabbe.restaurant.entity.dto.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IClientMapper {
    IClientMapper INSTANCE = Mappers.getMapper(IClientMapper.class);

    ClientDto clientToClientDto(Client client);
    Client clientDtoToClient(ClientDto dto);
}
