package com.usa.AppWeb.controller;

import com.usa.AppWeb.model.Casilla;
import com.usa.AppWeb.model.MovimientoParam;
import com.usa.AppWeb.model.Tablero;
import com.usa.AppWeb.model.TestFicha;
import com.usa.AppWeb.service.FichaService;
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
    public Tablero save(){
        return tableroService.armarTablero();
    }

    @PostMapping("/esValido")
    public boolean movimientoValido(@RequestBody MovimientoParam movimiento){

        FichaService fichaService = new FichaService();
       if(fichaService.esMovValido(movimiento.getPosX(), movimiento.getPosY(), movimiento.getFicha()))
        {

            return tableroService.moverFicha(movimiento.getFicha().getPosX(), movimiento.getFicha().getPosY(), 1, movimiento.getPosX(), movimiento.getPosY());

        }
        return false;

    }

}
