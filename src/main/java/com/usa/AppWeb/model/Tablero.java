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
    private Set<Casilla> casillas;

    public Set<Casilla> getCasillas() {
        return casillas;
    }

    public void setCasillas(Set<Casilla> casillas) {
        this.casillas = casillas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
