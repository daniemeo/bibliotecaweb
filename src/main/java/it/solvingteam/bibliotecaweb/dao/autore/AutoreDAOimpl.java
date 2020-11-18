package it.solvingteam.bibliotecaweb.dao.autore;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solvingteam.bibliotecaweb.model.autore.Autore;

public class AutoreDAOimpl implements AutoreDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	@Override
	public Set<Autore> list() throws Exception {
		return entityManager.createQuery("from Autore", Autore.class).getResultList().stream()
				.collect(Collectors.toSet());
	}

	@Override
	public Autore get(Long id) throws Exception {
		return entityManager.find(Autore.class, id);
	}

	@Override
	public boolean update(Autore autoreInstance) throws Exception {
		boolean result = false;
		if (autoreInstance == null) {
			throw new Exception("Problema valore in input");
		} else {
			autoreInstance = entityManager.merge(autoreInstance);
			result = true;
		}
		return result;

	}

	@Override
	public boolean insert(Autore autoreInstance) throws Exception {
		boolean result = false;
		if (autoreInstance == null) {
			throw new Exception("Problema valore in input");
		} else {
			entityManager.persist(autoreInstance);
			result = true;
		}
		return result;

	}

	@Override
	public boolean delete(Autore autoreInstance) throws Exception {
		boolean result = false;
		if (autoreInstance == null) {
			throw new Exception("Problema valore in input");
		} else {
			entityManager.remove(entityManager.merge(autoreInstance));
			result = true;
		}
		return result;

	}

	@Override
	public Set<Autore> ricercaAutore(Autore autore) throws Exception {
		String query = "SELECT a FROM Autore a WHERE 1=1";

		if (autore.getNome()!= null && !autore.getNome().isEmpty()) {
			query += " and a.nome = :nome";
		}
		if (autore.getCognome()!= null && !autore.getCognome().isEmpty()) {
			query += " and a.cognome = :cognome";
		}
		

		TypedQuery<Autore> newquery = entityManager.createQuery(query, Autore.class);
		if (autore.getNome()!= null && !autore.getNome().isEmpty()) {
			newquery.setParameter("nome", autore.getNome());
		}
		if (autore.getCognome()!= null && !autore.getCognome().isEmpty()) {
			newquery.setParameter("cognome", autore.getCognome());
		}
		
		return newquery.getResultList().stream().collect(Collectors.toSet());
	}
}
