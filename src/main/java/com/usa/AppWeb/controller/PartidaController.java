package com.usa.AppWeb.controller;

import com.usa.AppWeb.model.Partida;
import com.usa.AppWeb.model.Tablero;
import com.usa.AppWeb.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.usa.AppWeb.service.PartidaService;

import java.util.List;

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
    @ResponseStatus(HttpStatus.CREATED)
    public Partida cargarPartida(@RequestBody Usuario usuario, int id){
        return partidaService.cargarPartida(usuario, id);
    }

}
