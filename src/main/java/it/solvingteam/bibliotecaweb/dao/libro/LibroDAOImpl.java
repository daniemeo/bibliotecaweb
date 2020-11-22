package it.solvingteam.bibliotecaweb.dao.libro;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solvingteam.bibliotecaweb.model.libro.Libro;

public class LibroDAOImpl implements LibroDAO{
	
   private EntityManager entityManager;
	

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}

	@Override
	public Set<Libro> list() throws Exception {
		return entityManager.createQuery("from Libro",Libro.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Libro get(Long id) throws Exception {
		String query= "select l from Libro l join fetch l.autore where l.id = ?1";
		TypedQuery <Libro> querynew = entityManager.createQuery(query, Libro.class);
		 querynew.setParameter(1, id).getResultList();
		return entityManager.find(Libro.class, id);
	}

	@Override
	public boolean update(Libro libroInstance) throws Exception {
		boolean result= false;
		if (libroInstance == null) {
			throw new Exception("Problema valore in input");
		}
		else {
		libroInstance = entityManager.merge(libroInstance);	
		result= true;
		}
		return result;
		
	}

	@Override
	public boolean insert(Libro libroInstance) throws Exception {
		boolean result= false;
		
		if (libroInstance == null) {
			throw new Exception("Problema valore in input");
		}else {
		entityManager.persist(libroInstance);
		result = true;
		}
		return result;
		
	}

	@Override
	public boolean delete(Libro libroInstance) throws Exception {
		boolean result;
		if (libroInstance == null) {
			throw new Exception("Problema valore in input");
		} else {
		entityManager.remove(entityManager.merge(libroInstance));
		result= true;
		}
		return result;
	}
    
	@Override
	public Set<Libro> ricercaLibro(Libro libro){
		String query ="SELECT l FROM Libro l join fetch l.autore where 1 = 1";
		  if (libro.getTitolo() != null && !libro.getTitolo().isEmpty())
		  { 
			  query+= " and l.titolo = :titolo ";
		  
		  }
		  if (libro.getTrama() != null && !libro.getTrama().isEmpty())
		  { 
			  query+= " and l.trama = :trama ";
		  
		  }
		  if(libro.getGenere() != null ) {
			  
			  query+= " and l.genere = :genere";
		  }
		  if(libro.getAutore() != null ) {
			  
			  query+= " and l.autore = :autore";
		  }
		  
			  
		  TypedQuery <Libro> querynew = entityManager.createQuery(query, Libro.class);
		 if(libro.getTitolo()!= null && !libro.getTitolo().isEmpty()) {
			 querynew.setParameter("titolo", libro.getTitolo());
		 }
		 if(libro.getTrama()!= null && !libro.getTrama().isEmpty()) {
			 querynew.setParameter("trama", libro.getTrama());
		 }
		 if(libro.getGenere()!= null) {
		 querynew.setParameter("genere", libro.getGenere());
		 }
		 if(libro.getAutore() != null ) {
		 querynew.setParameter("autore", libro.getAutore());
		 }
		 return querynew.getResultList().stream().collect(Collectors.toSet());
		
	}


}
