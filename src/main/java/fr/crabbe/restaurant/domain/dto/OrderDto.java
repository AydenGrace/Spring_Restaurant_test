package fr.crabbe.restaurant.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

    private UUID uuid;

    private LocalDate orderDate;

    @NotNull(message = "An order must have a client")
    private ClientDto client;

    @NotEmpty(message = "An order must have some dishes")
    @NotNull(message = "An order must have some dishes")
    private List<DishDto> dishes;
}
