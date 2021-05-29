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
    @Setter private String notes;

    public Iteme(String name,float price,String notes){
        this.name = name;
        this.price = price;
        this.notes = notes;
    }
}
