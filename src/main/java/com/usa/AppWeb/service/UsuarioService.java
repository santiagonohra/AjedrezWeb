package com.usa.AppWeb.service;

import com.usa.AppWeb.model.Usuario;
import com.usa.AppWeb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario save(Usuario user) {
		List<Usuario> comparacion = findAll();
		for(Usuario usuario : comparacion){
			if(user.getUsername().equals(usuario.getUsername())){
				return null;
			}
		}
		return usuarioRepository.save(user);
	}

	public boolean delete(Usuario user){
		List<Usuario> allUsers = findAll();
		for(Usuario u : allUsers){
			if(u.equals(user)){
				usuarioRepository.delete(u);
				return true;
			}
		}
		return false;
	}

	public List<Usuario> findAll(){
		return (List<Usuario>) usuarioRepository.getAll();
	}
}