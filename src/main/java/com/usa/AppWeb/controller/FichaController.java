package com.usa.AppWeb.controller;

import com.usa.AppWeb.model.TestCasilla;
import com.usa.AppWeb.model.TestFicha;
import com.usa.AppWeb.service.FichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Ficha")
public class FichaController {
    //Aqui tenia pensado comunicar el Tablero controler para que lea movimientos y use, a traves
    //de este ficha controller, las reglas de movimientos que hay en FichaService (???????)
    @Autowired
    private FichaService fichaService;

    @GetMapping("/movimientoValido")
    public boolean movimientoValido(TestCasilla finalPosition, TestFicha ficha){
        return fichaService.esMovValido(finalPosition,ficha);
    }


}
