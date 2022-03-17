package com.usa.AppWeb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="tablero")
public class Tablero implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    //@OneToMany(mappedBy = "tablero")
    //private HashSet<Ficha> fichas;
    //Como sabemos que en el tablero, en la casilla que tiene posX=2 y posY=1, hay una ficha?
    @ElementCollection
    private List<TestFicha> fichas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TestFicha> getFichas() {
        return fichas;
    }

    public void setFichas(List<TestFicha> fichas) {
        this.fichas = fichas;
    }
}
