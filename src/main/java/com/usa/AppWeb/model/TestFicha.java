package com.usa.AppWeb.model;

import javax.persistence.Embeddable;

@Embeddable
public class TestFicha {
    private TipoFicha tipo;
    private EquipoFicha equipo;
    private int posX;
    private int posY;

    public TestFicha(){

    }

    public TestFicha(int posX, int posY, TipoFicha tipo, EquipoFicha equipo){
        this.posX=posX;
        this.posY=posY;
        this.tipo=tipo;
        this.equipo=equipo;
    }

    public TipoFicha getTipo() {
        return tipo;
    }

    public void setTipo(TipoFicha tipo) {
        this.tipo = tipo;
    }

    public EquipoFicha getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoFicha equipo) {
        this.equipo = equipo;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
