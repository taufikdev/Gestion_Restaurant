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
public class Iteme {

    private String id;
    @Setter private String name;
    @Setter private float price;
    @Setter private String Category;
    @Setter private String img;
}
