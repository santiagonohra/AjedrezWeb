package com.usa.AppWeb.model;

//import jdk.incubator.vector.VectorOperators;

import javax.persistence.Embeddable;

@Embeddable
public class TestCasilla {
    private int posX;
    private int posY;
    private boolean occupied=false;

    public TestCasilla(){

    }

    public TestCasilla(int posX, int posY, boolean occupied){
        this.posX=posX;
        this.posY=posY;
        this.occupied=occupied;
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

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean equalCoords(int posX, int posY) {
        return (this.posX == posX && this.posY == posY);
    }
}
