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

    @OneToMany(mappedBy = "partida")
    private Set<Usuario> user;

    @OneToOne
    private Tablero tablero;

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Set<Usuario> getUser() {
        return user;
    }

    public void setUser(Set<Usuario> user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
