package com.usa.AppWeb.controller;

import com.usa.AppWeb.model.*;
import com.usa.AppWeb.service.FichaService;
import com.usa.AppWeb.service.TableroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.usa.AppWeb.service.PartidaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Partida")
public class PartidaController {
    @Autowired
    private PartidaService partidaService;

    @GetMapping("/all")
    public List<Partida> getPartidas(){
        return partidaService.findAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Partida save(@RequestBody Partida partida){
        return partidaService.save(partida);
    }

    @PostMapping("/cargarPartida")
    public Partida cargarPartida(@RequestBody PartidaParam datos){
        return partidaService.cargarPartida(datos.getId());
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Partida crearPartida(String userName){
        return partidaService.crearPartida(userName);
    }

    @PostMapping("/unirsePartida")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean unirsePartida(@RequestBody PartidaParam datos){
        return partidaService.unirsePartida(datos.getUserName(), datos.getId());
    }

    @PostMapping("/esValido")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean movimientoValido(@RequestBody MovimientoParam movimiento){

        return partidaService.moverFicha(movimiento.getPosIX(), movimiento.getPosIY(), movimiento.getIdTablero(), movimiento.getPosX(), movimiento.getPosY());
    }

}
