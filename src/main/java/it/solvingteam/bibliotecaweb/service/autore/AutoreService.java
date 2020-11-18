package it.solvingteam.bibliotecaweb.service.autore;

import java.util.Set;

import it.solvingteam.bibliotecaweb.dao.autore.AutoreDAO;
import it.solvingteam.bibliotecaweb.model.autore.Autore;




public interface AutoreService {

	
	public Set<Autore> listAll() throws Exception;

	public Autore get(Long id) throws Exception;

	public boolean aggiorna(Autore autoreInstance) throws Exception;

	public boolean inserisciNuovo(Autore autoreInstance) throws Exception;

	public boolean rimuovi(Long id) throws Exception;
	
	public Set<Autore> findByAutore(Autore autore)throws Exception;
	
	// per injection
		public void setAutoreDAO(AutoreDAO autoreDAO)throws Exception;

}
