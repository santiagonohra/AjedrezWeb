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
		return usuarioRepository.save(user);
	}
	
	public List<Usuario> findAll(){
		return (List<Usuario>) usuarioRepository.getAll();
	}
}
