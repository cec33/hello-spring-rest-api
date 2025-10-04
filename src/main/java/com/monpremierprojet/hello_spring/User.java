package com.monpremierprojet.hello_spring;

import jakarta.persistence.*;

@Entity
@Table(name = "T_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1. Laisse la BD générer l'ID
    private Long id; // <-- CORRIGÉ : Utiliser Long pour un ID généré automatiquement
    private String name;

    // Constructeur sans argument obligatoire pour JPA
    public User(){}

    // Ancien constructeur pour la simulation n'est plus utile avec JPA
    // public User(String name) { this.name = name; }

    public Long getId() {return id;    }

    public void setId(Long id) {this.id = id;    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
