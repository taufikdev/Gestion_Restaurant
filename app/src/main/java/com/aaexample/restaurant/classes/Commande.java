package com.aaexample.restaurant.classes;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Commande {
    private String id;
  //  @Setter private Composition[] comps;
    @Setter private String user;
    @Setter private String table;
    @Setter private String etat;
    @Setter private LocalDateTime date;
    @Setter private float montant;
}
