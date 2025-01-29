package fr.crabbe.restaurant.controller;

import fr.crabbe.restaurant.domain.dto.DishDto;
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
    private DishService dishService;

    public DishController(DishService dishService){
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<List<DishDto>> getAll() {
        System.out.println("[DISH][GET] All");
        return ResponseEntity.ok(dishService.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DishDto> getById(@PathVariable UUID uuid) {
        System.out.println("[DISH][GET] UUID : " + uuid);
        return ResponseEntity.ok(dishService.getByUuid(uuid));
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
        System.out.println("[DISH][PATCH] UUID : " + uuid);
        dishService.update(uuid, dto);
        System.out.println("[DISH][PATCH] Success");
        return ResponseEntity.ok("Dish modified");
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<String> delete(@PathVariable UUID uuid) {
        System.out.println("[DISH][DELETE] UUID : " + uuid);
        dishService.delete(uuid);
        System.out.println("[DISH][DELETE] Success");
        return ResponseEntity.ok("Dish deleted");
    }
}
