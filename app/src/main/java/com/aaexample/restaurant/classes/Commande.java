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
  //  @Setter private Iteme[] items;
    @Setter private String user;
    @Setter private String table;
    @Setter private String etat;
    @Setter private String date;
    @Setter private float montant;



    public Commande (String id, String etat, String user, String table){
        this.id = id;
        this.etat = etat;
        this.user = user;
        this.table = table;
      //  this.items =new Iteme[10];
    }
}
