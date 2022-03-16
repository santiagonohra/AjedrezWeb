package com.usa.AppWeb.controller;

import com.usa.AppWeb.model.Usuario;
import com.usa.AppWeb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/Usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/all")
    public List<Usuario> getPeople(){
        return usuarioService.findAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save(@RequestBody Usuario user) {
        return usuarioService.save(user);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean delete(@RequestBody Usuario user) {
        return usuarioService.delete(user);
    }

}