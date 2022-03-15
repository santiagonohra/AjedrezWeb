package com.usa.AppWeb.model;

import javax.persistence.*;

@Entity
@Table(name="ficha")
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private TipoFicha tipo;
    private EquipoFicha equipo;

    public TipoFicha getTipo() {
        return tipo;
    }

    public EquipoFicha getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoFicha equipo) {
        this.equipo = equipo;
    }

    public void setTipo(TipoFicha tipo) {
        this.tipo = tipo;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    @OneToOne
    private Casilla casilla;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
