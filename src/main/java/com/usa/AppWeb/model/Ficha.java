package com.usa.AppWeb.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ficha")
public class Ficha implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private TipoFicha tipo;
    private Integer posX, posY;
    private EquipoFicha equipo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tablero_id")
    private Tablero tablero;

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }
}
