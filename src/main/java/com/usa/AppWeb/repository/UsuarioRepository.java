package com.usa.AppWeb.repository;

import com.usa.AppWeb.model.Usuario;
import com.usa.AppWeb.repository.crud.UsuarioCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {
	@Autowired
	private UsuarioCRUDRepository usuarioCRUDRepository;
	
	public List<Usuario> getAll(){
		return (List<Usuario>) usuarioCRUDRepository.findAll();
	}
	
	public Optional<Usuario> getById(int id){
		return usuarioCRUDRepository.findById(id);
	}
	
	public Usuario save(Usuario user) {
		return usuarioCRUDRepository.save(user);
	}
	
	public void delete(Usuario user) {
		usuarioCRUDRepository.delete(user);
	}


}
