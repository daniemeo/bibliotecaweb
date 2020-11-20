package it.solvingteam.bibliotecaweb.service.utente;

import java.util.Set;

import it.solvingteam.bibliotecaweb.dao.utente.UtenteDAO;
import it.solvingteam.bibliotecaweb.model.ruolo.Ruolo;
import it.solvingteam.bibliotecaweb.model.utente.Utente;

public interface UtenteService {

	public Set<Utente> setAll() throws Exception;

	public Utente get(Long id) throws Exception;

	public boolean aggiorna(Utente utenteInstance) throws Exception;

	public boolean inserisciNuovo(Utente utenteInstance) throws Exception;

	public boolean rimuovi(Long id) throws Exception;
	
	public boolean aggiungiRuolo(Utente UtenteEsistente,  Ruolo ruoloInstance) throws Exception;

	public void setUtenteDAO(UtenteDAO utenteDAO);

	public Utente cercaPerUsernameEPassword(String username, String password) throws Exception;

	public Set<Utente> findByUtente(Utente utente);
}
