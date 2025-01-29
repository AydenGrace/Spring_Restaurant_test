package fr.crabbe.restaurant.controller;

import fr.crabbe.restaurant.domain.dto.ClientDto;
import fr.crabbe.restaurant.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

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
    public ResponseEntity<String> create(@RequestBody @Valid ClientDto dto) {
        System.out.println("[CLIENT][POST] New Client : " + dto.getName());
        clientService.create(dto);
        System.out.println("[CLIENT][POST] Success");
        return ResponseEntity.status(HttpStatus.CREATED).body("Client created");
    }

    @PatchMapping("/update/{uuid}")
    public ResponseEntity<String> patch(@PathVariable UUID uuid, @RequestBody ClientDto dto) {
        System.out.println("[CLIENT][PATCH] UUID : " + uuid);
        clientService.update(uuid, dto);
        System.out.println("[CLIENT][PATCH] Success");
        return ResponseEntity.ok("Client modified");
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<String> delete(@PathVariable UUID uuid) {
        System.out.println("[CLIENT][DELETE] UUID : " + uuid);
        clientService.delete(uuid);
        System.out.println("[CLIENT][DELETE] Success");
        return ResponseEntity.ok("Client deleted");
    }
}
