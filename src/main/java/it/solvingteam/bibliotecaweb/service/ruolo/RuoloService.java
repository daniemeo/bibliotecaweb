package it.solvingteam.bibliotecaweb.service.ruolo;

import java.util.Set;

import it.solvingteam.bibliotecaweb.dao.ruolo.RuoloDAO;
import it.solvingteam.bibliotecaweb.model.ruolo.Ruolo;


public interface RuoloService {
	
//	public Set<Ruolo> setAll() throws Exception;

	public Ruolo get(Long id) throws Exception;

	public void setRuoloDAO(RuoloDAO ruoloDAO);

	public Set<Ruolo> listAll() throws Exception;
}
