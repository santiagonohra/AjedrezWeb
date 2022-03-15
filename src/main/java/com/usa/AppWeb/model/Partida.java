package com.usa.AppWeb.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="partida")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String userName1, tipoJugador1;
    private String userName2, tipoJugador2;

    @OneToOne
    private Tablero tablero;

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getTipoJugador1() {
        return tipoJugador1;
    }

    public void setTipoJugador1(String tipoJugador1) {
        this.tipoJugador1 = tipoJugador1;
    }

    public String getUserName2() {
        return userName2;
    }

    public void setUserName2(String userName2) {
        this.userName2 = userName2;
    }

    public String getTipoJugador2() {
        return tipoJugador2;
    }

    public void setTipoJugador2(String tipoJugador2) {
        this.tipoJugador2 = tipoJugador2;
    }
}
