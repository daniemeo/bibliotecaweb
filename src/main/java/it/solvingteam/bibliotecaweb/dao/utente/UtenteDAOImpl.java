package it.solvingteam.bibliotecaweb.dao.utente;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solvingteam.bibliotecaweb.model.utente.Utente;

public class UtenteDAOImpl implements UtenteDAO {
    
	private EntityManager entityManager;
	

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}
	
	@Override
	public Set<Utente> list() throws Exception {
		return entityManager.createQuery("from Utente u join fetch u.ruoli ",Utente.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Utente get(Long id) throws Exception {
		if(id == null) {
			throw new Exception("Problema valore in input");
		}
		TypedQuery<Utente> query =  entityManager.createQuery("from Utente u join fetch u.ruoli where u.id = ?1",Utente.class);
		query.setParameter(1, id);
		return query.getSingleResult();
		
	}

	@Override
	public boolean update(Utente utenteInstance) throws Exception {
		boolean result;
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		} else {
		utenteInstance = entityManager.merge(utenteInstance);	
		result= true;
		}
		return result;
		
	}

	@Override
	public boolean insert(Utente utenteInstance) throws Exception {
		boolean result;
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		} else {
		entityManager.persist(utenteInstance);
		result= true;
		}
		return result;
	}
	
//
//   @Override
//   public boolean delete(Utente o) throws Exception {
//	// TODO Auto-generated method stub
//	return false;
//}

	@Override 
	public boolean delete(Utente utenteInstance) throws Exception {
		boolean result= false;
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		} else {
		entityManager.remove(entityManager.merge(utenteInstance));
		result = false;
		}
		return result;
	}
    
	@Override
	public Utente findByUsernamePassword(String username, String password) throws Exception {
		if (username == null ||password  == null) {
			throw new Exception("Problema valore in input");
		} else {
			TypedQuery<Utente> query = entityManager
					.createQuery("select u from Utente u join fetch u.ruoli where u.username=?1 and u.password=?2", Utente.class)
					.setParameter(1, username)
					.setParameter(2, password);
			return query.getResultStream().findFirst().orElse(null);
		}
	}
	
    @Override
	public Set<Utente> ricercaUtente (Utente utente){
		String query = "SELECT u FROM Utente u join fetch u.ruoli WHERE 1=1";
		if(utente.getNome() != null && !utente.getNome().isEmpty()) {
			query += " and u.nome = :nome";
		}
		if(utente.getCognome() != null && !utente.getCognome().isEmpty()) {
			query += " and u.cognome = :cognome";
		}
		if(utente.getUsername() != null && !utente.getUsername().isEmpty()) {
			query += " and u.username = :username";
		}
		if(utente.getStato() != null ) {
			query += " and u.stato = :stato";
		}
		
		TypedQuery<Utente> newquery = entityManager.createQuery(query, Utente.class);
		if(utente.getNome() != null && !utente.getNome().isEmpty()) {
			newquery.setParameter("nome", utente.getNome());
		}
		if(utente.getCognome() != null && !utente.getCognome().isEmpty()) {
			newquery.setParameter("cognome", utente.getCognome());
		}
		if(utente.getUsername() != null && !utente.getUsername().isEmpty()) {
			newquery.setParameter("username", utente.getUsername());
		}
		if(utente.getStato() != null ) {
			newquery.setParameter("stato", utente.getStato());
		}
		return newquery.getResultList().stream().collect(Collectors.toSet());
	}

	

}
