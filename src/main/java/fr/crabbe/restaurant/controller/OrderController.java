package fr.crabbe.restaurant.controller;

import fr.crabbe.restaurant.entity.dto.ClientDto;
import fr.crabbe.restaurant.entity.dto.DishDto;
import fr.crabbe.restaurant.entity.dto.OrderDto;
import fr.crabbe.restaurant.exception.ClientNotFoundException;
import fr.crabbe.restaurant.exception.DishNotFoundException;
import fr.crabbe.restaurant.exception.OrderNotFoundException;
import fr.crabbe.restaurant.service.DishService;
import fr.crabbe.restaurant.service.OrderService;
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
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        System.out.println("[ORDER][GET] All");
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrderDto> getById(@PathVariable UUID uuid) {
        try {
            System.out.println("[ORDER][GET] UUID : "+uuid);
            return ResponseEntity.ok(orderService.getByUuid(uuid));
        } catch (OrderNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/client")
    public ResponseEntity<List<OrderDto>> getByClient(@RequestBody ClientDto client) {
        System.out.println("[ORDER][GET] Client UUID : "+client.getUuid());
        return ResponseEntity.ok(orderService.getClientOrders(client.getUuid()));
    }

    @PostMapping("/add")
    public ResponseEntity<String> create(@RequestBody @Valid OrderDto dto) {
        try{
            System.out.println("[ORDER][POST] New Order for Client : "+dto.getClient().getUuid());
            orderService.create(dto);
            System.out.println("[ORDER][POST] Success");
            return ResponseEntity.status(HttpStatus.CREATED).body("order created");
        }catch (ClientNotFoundException | DishNotFoundException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
