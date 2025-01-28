package fr.crabbe.restaurant.exosTests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeObject {
    private String name;
    private int age;

    public String name(){
        return name;
    }
}
