package it.solvingteam.bibliotecaweb.dao.libro;

import java.util.Set;

import it.solvingteam.bibliotecaweb.dao.IBaseDAO;
import it.solvingteam.bibliotecaweb.model.libro.Libro;

public interface LibroDAO extends IBaseDAO<Libro>{

	public Set<Libro> ricercaLibro(Libro libro);

}
