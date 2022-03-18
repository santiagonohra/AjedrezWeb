package com.usa.AppWeb.controller;

import com.usa.AppWeb.model.Usuario;
import com.usa.AppWeb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/Usuario")
@CrossOrigin
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
        Usuario u = new Usuario();
        u.setUsername(user.getUsername());
        u.setClave(user.getClave());
        u.setEmail(user.getEmail());
        return usuarioService.save(u);
    }

    @PostMapping("/iniciarSesion")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean iniciarSesion(@RequestBody Usuario u){
        System.out.println("Datos de llegada ->"+u.getUsername()+" "+u.getClave());
        return usuarioService.usuarioRegistrado(u);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean delete(@RequestBody Usuario user) {
        return usuarioService.delete(user);
    }
}

