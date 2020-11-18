package it.solvingteam.bibliotecaweb.service.libro;

import java.util.Set;

import it.solvingteam.bibliotecaweb.dao.libro.LibroDAO;
import it.solvingteam.bibliotecaweb.model.autore.Autore;
import it.solvingteam.bibliotecaweb.model.libro.Libro;



public interface LibroService {
	
	public Set<Libro> setAll() throws Exception;

	public Libro get(Long id) throws Exception;

	public boolean aggiorna(Libro libroInstance) throws Exception;

	public boolean inserisciNuovo(Libro libroInstance) throws Exception;

	public boolean rimuovi(Libro libroInstance) throws Exception;

	public boolean aggiungiAutore(Libro libroEsistente,  Autore autoreInstance) throws Exception;
	
	//public boolean rimuoviAutore(Libro libroEsistente,  Autore autoreInstance) throws Exception;
	public Set<Libro> findByLibro(Libro libro);

	public void setLibroDAO(LibroDAO libroDAO);
}
