package com.usa.AppWeb.controller;

import com.usa.AppWeb.model.Tablero;
import com.usa.AppWeb.model.TestCasilla;
import com.usa.AppWeb.service.TableroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Tablero")
public class TableroController {
    @Autowired
    private TableroService tableroService;

    @GetMapping("/all")
    public List<Tablero> getTableros(){
        return tableroService.findAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Tablero save(Tablero tablero){
        return tableroService.save(tablero);
    }

    @GetMapping("/casilla")
    public TestCasilla getCasillaPorPos(int posX, int posY, int idTablero){
        return tableroService.getCasillaPorPos(posX, posY, idTablero);
    }


}
