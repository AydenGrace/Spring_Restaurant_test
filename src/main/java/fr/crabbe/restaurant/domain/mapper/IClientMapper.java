package fr.crabbe.restaurant.domain.mapper;

import fr.crabbe.restaurant.domain.entity.Client;
import fr.crabbe.restaurant.domain.dto.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IClientMapper {
    IClientMapper INSTANCE = Mappers.getMapper(IClientMapper.class);

    ClientDto clientToClientDto(Client client);
    Client clientDtoToClient(ClientDto dto);
}
