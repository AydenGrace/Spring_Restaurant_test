package fr.crabbe.restaurant.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {
    private UUID uuid;


    @NotBlank(message = "A dish must have a name")
    @NotNull(message = "A dish must have a name")
    private String name;
}
