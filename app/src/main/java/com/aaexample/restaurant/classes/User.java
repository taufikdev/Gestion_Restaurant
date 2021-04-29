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
public class User {

private String id;
@Setter private String name;
@Setter private String last_name;
@Setter private String function;
@Setter private String mot_de_passe;

}
