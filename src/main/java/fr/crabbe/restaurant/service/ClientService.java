package fr.crabbe.restaurant.service;

import fr.crabbe.restaurant.entity.Client;
import fr.crabbe.restaurant.entity.dto.ClientDto;
import fr.crabbe.restaurant.entity.dto.mapper.ClientMapper;
import fr.crabbe.restaurant.exception.ClientNotFoundException;
import fr.crabbe.restaurant.exception.ClientNotModifiedException;
import fr.crabbe.restaurant.repository.IClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
            return ClientMapper.toDto(client.get());
        } else {
            throw new ClientNotFoundException();
        }
    }

    public void create(ClientDto dto){
        clientRepository.save(ClientMapper.toEntity(dto));
    }

    public void update(UUID uuid, ClientDto dto) throws ClientNotFoundException, ClientNotModifiedException {
        clientRepository.findByUuid(uuid).ifPresentOrElse(client -> {
            if (dto.getName() != null && !dto.getName().equals(client.getName())) {
                client.setName(dto.getName());
                clientRepository.save(client);
            } else {
                throw new ClientNotModifiedException();
            }
        }, () -> {
            throw new ClientNotFoundException();
        });
    }

    public void delete(UUID uuid) throws ClientNotFoundException{
        clientRepository.findByUuid(uuid).ifPresentOrElse(clientRepository::delete, () -> {
            throw new ClientNotFoundException();
        });
    }
}
