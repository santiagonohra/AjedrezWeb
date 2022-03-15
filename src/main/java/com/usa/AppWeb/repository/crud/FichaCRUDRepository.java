package com.usa.AppWeb.repository.crud;

import com.usa.AppWeb.model.Casilla;
import com.usa.AppWeb.model.Ficha;
import org.springframework.data.repository.CrudRepository;

public interface FichaCRUDRepository extends CrudRepository<Ficha, Integer> {
}
