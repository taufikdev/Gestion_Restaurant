package com.aaexample.restaurant.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Ingredient {

    private String id;
    @Setter private String name;
    @Setter private int img;
}
