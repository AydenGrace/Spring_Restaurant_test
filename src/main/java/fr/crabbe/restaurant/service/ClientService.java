package fr.crabbe.restaurant.service;

import fr.crabbe.restaurant.domain.entity.Client;
import fr.crabbe.restaurant.domain.dto.ClientDto;
import fr.crabbe.restaurant.domain.mapper.ClientMapper;
import fr.crabbe.restaurant.domain.mapper.IClientMapper;
import fr.crabbe.restaurant.exception.ClientNotFoundException;
import fr.crabbe.restaurant.exception.ClientNotModifiedException;
import fr.crabbe.restaurant.repository.IClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final IClientRepository clientRepository;

    public List<ClientDto> getAll() {
        List<ClientDto> clients = new ArrayList<>();
        for (Client client : clientRepository.findAll()) {
            clients.add(ClientMapper.toDto(client));
        }
        return clients;
    }

    public ClientDto getById(Long id) throws ClientNotFoundException{
        try{
            Optional<Client> client =  clientRepository.findById(id);
            if(client.isPresent()){
                return ClientMapper.toDto(client.get());
            } else {
                throw new ClientNotFoundException();
            }
        }catch (ClientNotFoundException e){
            return null;
        }

    }

    public ClientDto getByUuid(UUID uuid) throws ClientNotFoundException{
        Optional<Client> client =  clientRepository.findByUuid(uuid);
        if(client.isPresent()){
            return IClientMapper.INSTANCE.clientToClientDto(client.get());
        } else {
            throw new ClientNotFoundException();
        }
    }

    public Client create(ClientDto dto){
        Client client = IClientMapper.INSTANCE.clientDtoToClient(dto);
        return clientRepository.save(client);
    }

    public ClientDto update(UUID uuid, ClientDto dto) throws ClientNotFoundException, ClientNotModifiedException {
        Optional<Client> clientFound = clientRepository.findByUuid(uuid);
        if(clientFound.isPresent()){
            if (dto.getName() != null && !dto.getName().equals(clientFound.get().getName())) {
                clientFound.get().setName(dto.getName());
                clientRepository.save(clientFound.get());
                return IClientMapper.INSTANCE.clientToClientDto(clientFound.get());
            } else {
                throw new ClientNotModifiedException();
            }
        } else {
            throw new ClientNotFoundException();
        }
    }

    public void delete(UUID uuid) throws ClientNotFoundException{
        clientRepository.findByUuid(uuid).ifPresentOrElse(clientRepository::delete, () -> {
            throw new ClientNotFoundException();
        });
    }
}
