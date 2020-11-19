package it.solvingteam.bibliotecaweb.dao.utente;

import java.util.Set;

import it.solvingteam.bibliotecaweb.dao.IBaseDAO;
import it.solvingteam.bibliotecaweb.model.utente.Utente;

public interface UtenteDAO extends IBaseDAO<Utente>{

	public Utente findByUsernamePassword(String username, String password) throws Exception;

	public Set<Utente> ricercaUtente(Utente utente);

}
