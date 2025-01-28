package fr.crabbe.restaurant.controller;

import fr.crabbe.restaurant.entity.dto.DishDto;
import fr.crabbe.restaurant.exception.DishInOrderException;
import fr.crabbe.restaurant.exception.DishNotFoundException;
import fr.crabbe.restaurant.exception.DishNotModifiedException;
import fr.crabbe.restaurant.service.DishService;
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
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;

    @GetMapping
    public ResponseEntity<List<DishDto>> getAll() {
        System.out.println("[DISH][GET] All");
        return ResponseEntity.ok(dishService.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DishDto> getById(@PathVariable UUID uuid) {
        try {
            System.out.println("[DISH][GET] UUID : " + uuid);
            return ResponseEntity.ok(dishService.getByUuid(uuid));
        } catch (DishNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> create(@RequestBody @Valid DishDto dto) {
        System.out.println("[DISH][POST] New Dish : " + dto.getName());
        dishService.create(dto);
        System.out.println("[DISH][POST] Success");
        return ResponseEntity.status(HttpStatus.CREATED).body("Dish created");
    }

    @PatchMapping("/update/{uuid}")
    public ResponseEntity<String> patch(@PathVariable UUID uuid, @RequestBody DishDto dto) {
        try {
            System.out.println("[DISH][PATCH] UUID : "+uuid);
            dishService.update(uuid, dto);
            System.out.println("[DISH][PATCH] Success");
            return ResponseEntity.ok("Dish modified");
        } catch (DishNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dish with UUID : " + uuid + " not found.");
        } catch (DishNotModifiedException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Dish with UUID : " + uuid + " not modified.");
        }
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<String> delete(@PathVariable UUID uuid) {
        try {
            System.out.println("[DISH][DELETE] UUID : " + uuid);
            dishService.delete(uuid);
            System.out.println("[DISH][DELETE] Success");

            return ResponseEntity.ok("Dish deleted");
        } catch (DishNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dish with UUID : " + uuid + " not found.");
        } catch (DishInOrderException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Dish with UUID : " + uuid + " already in an order.");
        }
    }
}
