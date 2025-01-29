package fr.crabbe.restaurant.controller;

import fr.crabbe.restaurant.domain.dto.ClientDto;
import fr.crabbe.restaurant.domain.entity.Client;
import fr.crabbe.restaurant.domain.mapper.ClientMapper;
import fr.crabbe.restaurant.domain.mapper.IClientMapper;
import fr.crabbe.restaurant.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAll() {
        System.out.println("[CLIENT][GET] All");
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ClientDto> getByUuid(@PathVariable UUID uuid) {
        System.out.println("[CLIENT][GET] UUID : " + uuid);
        return ResponseEntity.ok(clientService.getByUuid(uuid));
    }

    @PostMapping("/add")
    public ResponseEntity<ClientDto> create(@RequestBody @Valid ClientDto dto) {
        System.out.println("[CLIENT][POST] New Client : " + dto.getName());
        Client response = clientService.create(dto);
        System.out.println("[CLIENT][POST] Success");
        return ResponseEntity.status(HttpStatus.CREATED).body(IClientMapper.INSTANCE.clientToClientDto(response));

    }

    @PatchMapping("/update/{uuid}")
    public ResponseEntity<ClientDto> patch(@PathVariable UUID uuid, @RequestBody @Valid ClientDto dto) {
        System.out.println("[CLIENT][PATCH] UUID : " + uuid);
        ClientDto response = clientService.update(uuid, dto);
        System.out.println("[CLIENT][PATCH] Success");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<String> delete(@PathVariable UUID uuid) {
        System.out.println("[CLIENT][DELETE] UUID : " + uuid);
        clientService.delete(uuid);
        System.out.println("[CLIENT][DELETE] Success");
        return ResponseEntity.ok("Client deleted");
    }
}
