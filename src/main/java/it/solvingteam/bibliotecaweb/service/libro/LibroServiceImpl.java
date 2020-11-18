package it.solvingteam.bibliotecaweb.service.libro;

import java.util.Set;

import javax.persistence.EntityManager;

import it.solvingteam.bibliotecaweb.dao.EntityManagerUtil;
import it.solvingteam.bibliotecaweb.dao.libro.LibroDAO;
import it.solvingteam.bibliotecaweb.model.autore.Autore;
import it.solvingteam.bibliotecaweb.model.libro.Libro;




public class LibroServiceImpl implements LibroService{
 
	private LibroDAO libroDAO;
	@Override
	public Set<Libro> setAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Libro get(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public boolean aggiorna(Libro libroInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
        boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);
			if(libroInstance.getAutore().getId() != 0) {
			// eseguo quello che realmente devo fare
			result =libroDAO.update(libroInstance);
			result = true;
			}
			else { 
				
				return result;
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
	public boolean inserisciNuovo(Libro libroInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
         boolean result= false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);
			if(libroInstance.getAutore().getId() != 0) {
				//System.out.println("non puoi inserire un libro senza un autore!!!!!"); inserire nella servlet
				 // fare il controllo da servlet sul tipo di ritorno!!!
				libroDAO.insert(libroInstance);
				result = true;
			}
			else { 
				
				result= false;
				
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
	public boolean rimuovi(Libro libroInstance) throws Exception {
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
            boolean result= false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);
            
			// eseguo quello che realmente devo fare
			libroDAO.delete(libroInstance);
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
	public boolean aggiungiAutore(Libro libroEsistente,  Autore autoreInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
         boolean result= false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			boolean libroAggiornato = libroDAO.update(libroEsistente);
			if(libroAggiornato) {
			libroEsistente = entityManager.merge(libroEsistente);
			autoreInstance = entityManager.merge(autoreInstance);
		
			autoreInstance.getLibri().add(libroEsistente);
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
	/*
	@Override
	public boolean rimuoviAutore(Libro libroEsistente,  Autore autoreInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
         boolean result = false;
		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			libroEsistente = entityManager.merge(libroEsistente);
			autoreInstance = entityManager.merge(autoreInstance);
			
			Set<Libro> listaLibri = libroDAO.list();
			if(listaLibri.size()==1) {
			autoreInstance.getLibri().remove(libroEsistente);
			entityManager.getTransaction().commit();
			result= true;
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
        return result;
	}
	*/
	
	@Override 
	public Set<Libro> findByLibro(Libro libro){
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.ricercaLibro(libro);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}

	}
	public void setLibroDAO(LibroDAO libroDAO) {
		this.libroDAO = libroDAO;
	
	}

}
