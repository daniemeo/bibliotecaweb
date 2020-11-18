package it.solvingteam.bibliotecaweb.dao.utente;

import it.solvingteam.bibliotecaweb.dao.IBaseDAO;
import it.solvingteam.bibliotecaweb.model.utente.Utente;

public interface UtenteDAO extends IBaseDAO<Utente>{

	Utente findByUsernamePassword(String username, String password) throws Exception;

}
