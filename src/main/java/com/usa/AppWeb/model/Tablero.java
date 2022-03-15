package com.usa.AppWeb.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="tablero")
public class Tablero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "tablero")
    private Set<Casilla> casilla;

    public Set<Casilla> getCasilla() {
        return casilla;
    }

    public void setCasilla(Set<Casilla> casilla) {
        this.casilla = casilla;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
