package it.solvingteam.bibliotecaweb.dao.autore;

import java.util.Set;

import it.solvingteam.bibliotecaweb.dao.IBaseDAO;
import it.solvingteam.bibliotecaweb.model.autore.Autore;

public interface AutoreDAO extends IBaseDAO<Autore> {

	public Set<Autore> ricercaAutore(Autore autore)throws Exception;

}
