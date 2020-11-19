package it.solvingteam.bibliotecaweb.service.utente;

import java.util.Set;

import javax.persistence.EntityManager;

import it.solvingteam.bibliotecaweb.dao.EntityManagerUtil;
import it.solvingteam.bibliotecaweb.dao.utente.UtenteDAO;
import it.solvingteam.bibliotecaweb.model.ruolo.Ruolo;
import it.solvingteam.bibliotecaweb.model.utente.Utente;


public class UtenteServiceImpl implements UtenteService{
	
private UtenteDAO utenteDAO;
	
	@Override
	public Set<Utente> setAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Utente get(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public boolean aggiorna(Utente utenteInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
        boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			// eseguo quello che realmente devo fare
			result = utenteDAO.update(utenteInstance);

			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return result;

		
	}

	@Override
	public boolean inserisciNuovo(Utente utenteInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
         boolean result= false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			if(utenteInstance.getRuoli().size()>= 0) {
		
				
				utenteDAO.insert(utenteInstance);
				result = true;
			}else {result = false;
			}
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			
		}
		return result;
		
	}

	@Override
	public boolean rimuovi(Utente utenteInstance) throws Exception {
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
            boolean result= false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			utenteDAO.delete(utenteInstance);
            result = true;
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@Override
	public boolean aggiungiRuolo(Utente utenteEsistente,  Ruolo ruoloInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
         boolean result= false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			boolean utenteAggiornato = utenteDAO.update(utenteEsistente);
			if(utenteAggiornato) {
			utenteEsistente = entityManager.merge(utenteEsistente);
			ruoloInstance = entityManager.merge(ruoloInstance);
		    utenteEsistente.getRuoli().add(ruoloInstance);
			entityManager.getTransaction().commit();
			result = true;
			
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	@Override
	public void setUtenteDAO(UtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
		
	}
	@Override
	public Utente cercaPerUsernameEPassword(String username, String password) throws Exception {
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
	
		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.findByUsernamePassword(username, password);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}
	
	@Override 
	public Set<Utente> findByUtente(Utente utente){
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.ricercaUtente(utente);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}

	
	}
	
	
}
