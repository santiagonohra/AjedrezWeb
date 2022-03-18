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

    private String userName1;
    private String userName2;

    private EquipoFicha turno;

    private EquipoFicha equipoJugador1;
    private EquipoFicha equipoJugador2;

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

    public String getUserName2() {
        return userName2;
    }

    public void setUserName2(String userName2) {
        this.userName2 = userName2;
    }

    public EquipoFicha getEquipoJugador1() {
        return equipoJugador1;
    }

    public void setEquipoJugador1(EquipoFicha equipoJugador1) {
        this.equipoJugador1 = equipoJugador1;
    }

    public EquipoFicha getEquipoJugador2() {
        return equipoJugador2;
    }

    public void setEquipoJugador2(EquipoFicha equipoJugador2) {
        this.equipoJugador2 = equipoJugador2;
    }

    public EquipoFicha getTurno() {
        return turno;
    }

    public void setTurno(EquipoFicha turno) {
        this.turno = turno;
    }
}
