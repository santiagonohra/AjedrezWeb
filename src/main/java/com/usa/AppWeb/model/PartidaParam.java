package com.usa.AppWeb.model;

//Clase para los parametros que reciben los metodos de controlador partida //DTO
public class PartidaParam {
    private int id;
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
