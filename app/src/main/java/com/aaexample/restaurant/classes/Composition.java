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

public class Composition {

    private String id;
    @Setter
    Iteme iteme;
    @Setter private Ingredient[] ingreds;
    @Setter private int quantite;
}
