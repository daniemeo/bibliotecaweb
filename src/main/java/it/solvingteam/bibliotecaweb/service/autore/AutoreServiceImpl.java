package it.solvingteam.bibliotecaweb.service.autore;

import java.util.Set;

import javax.persistence.EntityManager;

import it.solvingteam.bibliotecaweb.dao.EntityManagerUtil;
import it.solvingteam.bibliotecaweb.dao.autore.AutoreDAO;
import it.solvingteam.bibliotecaweb.model.autore.Autore;

public class AutoreServiceImpl implements AutoreService {

	private AutoreDAO autoreDAO;

	@Override
	public Set<Autore> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return autoreDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Autore get(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return autoreDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public boolean aggiorna(Autore autoreInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);
			// eseguo quello che realmente devo fare
			result = autoreDAO.update(autoreInstance);

			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	@Override
	public boolean inserisciNuovo(Autore autoreInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);
			// if(!autoreInstance.getLibri().isEmpty()) {
			// eseguo quello che realmente devo fare
			autoreDAO.insert(autoreInstance);
			result = true;
			entityManager.getTransaction().commit();
			// } abbiamo tolto il controllo sull'autore!!

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		}
		return result;

	}

	@Override
	public boolean rimuovi(Long id) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		boolean result = false;

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();
			
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);
			Autore autore = autoreDAO.get(id);
			if (autore.getLibri().size() == 0) {
				// eseguo quello che realmente devo fare
				autoreDAO.delete(autore);

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
	public void setAutoreDAO(AutoreDAO autoreDAO) {
		this.autoreDAO = autoreDAO;

	}

	@Override
	public Set<Autore> findByAutore(Autore autore) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return autoreDAO.ricercaAutore(autore);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

}
