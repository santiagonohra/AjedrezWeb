package com.usa.AppWeb.service;

import com.usa.AppWeb.model.Partida;
import com.usa.AppWeb.model.Tablero;
import com.usa.AppWeb.model.Usuario;
import com.usa.AppWeb.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PartidaService {
    @Autowired
    private PartidaRepository partidaRepository;

    public Partida save(Partida partida) {
        return partidaRepository.save(partida);
    }

    public boolean delete(Partida partida){
        List<Partida> allMatches = findAll();
        for(Partida p : allMatches){
            if(p.equals(partida)){
                partidaRepository.delete(p);
                return true;
            }
        }
        return false;
    }

    public List<Partida> findAll(){
        return (List<Partida>) partidaRepository.getAll();
    }

    public Partida cargarPartida(Usuario user, int id){

        Optional<Partida> partidaCargada = partidaRepository.getById(id);
        return partidaCargada.get();
        /*
        Set<Usuario> usuariosPartida = partidaCargada.get().getUser();
        if(usuariosPartida.size()<=2){
            for(Usuario users : usuariosPartida){
                if(user.getUsername().equals(users.getUsername())){
                    return partidaCargada.get();
                }
            }
        }
        return null;
        */
    }
}