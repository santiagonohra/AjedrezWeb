package com.usa.AppWeb.controller;

import com.usa.AppWeb.model.*;
import com.usa.AppWeb.service.FichaService;
import com.usa.AppWeb.service.TableroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Tablero")
public class TableroController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TableroController.class);
    private String colorR;

    @Autowired
    private TableroService tableroService;

    @Autowired
    private FichaService fichaService;

    @CrossOrigin
    @PostMapping(value = "/chess/is-correct-move")
    public ResponseEntity<Boolean> isCorrectMove(@RequestBody String posIncial,String posFinal, String ficha, String color) {//1_1 2_2 'Alfil'
        LOGGER.info("*** move details : {}", posFinal,ficha);
        TestCasilla casillaFinal = new TestCasilla(Integer.parseInt(Character.toString(posFinal.charAt(0))), Integer.parseInt(Character.toString(posFinal.charAt(2))), true); //2_2
        TestFicha fichaf = new TestFicha(Integer.parseInt(Character.toString(posIncial.charAt(0))), Integer.parseInt(Character.toString(posIncial.charAt(2))), TipoFicha.ALFIL, EquipoFicha.NEGRO);


        return ResponseEntity.ok(fichaService.esMovValido(casillaFinal, fichaf));
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
