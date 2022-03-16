package com.usa.AppWeb.controller;

import com.usa.AppWeb.model.*;
import com.usa.AppWeb.service.FichaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private FichaService fichaService;

    @CrossOrigin
    @PostMapping(value = "/chess/is-correct-move")
    public ResponseEntity<Boolean> isCorrectMove(@RequestBody CasillaI casillaI) { //1_2
        TestCasilla casillFinal = new TestCasilla(Integer.parseInt(Character.toString(casillaI.getPosFinal().charAt(0))), Integer.parseInt(Character.toString(casillaI.getPosFinal().charAt(2))), true);
        TestFicha fichaL = new TestFicha(Integer.parseInt(Character.toString(casillaI.getPosInicial().charAt(0))), Integer.parseInt(Character.toString(casillaI.getPosInicial().charAt(2))),TipoFicha.ALFIL, EquipoFicha.NEGRO);

        return ResponseEntity.ok(fichaService.esMovValido(casillFinal, fichaL));
    }

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
