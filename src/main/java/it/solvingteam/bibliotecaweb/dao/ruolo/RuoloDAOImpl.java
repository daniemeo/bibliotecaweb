package it.solvingteam.bibliotecaweb.dao.ruolo;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;


import it.solvingteam.bibliotecaweb.model.ruolo.Ruolo;

public class RuoloDAOImpl implements RuoloDAO {

	
	private EntityManager entityManager;
	

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}

	@Override
	public Set<Ruolo> list() throws Exception {
		return entityManager.createQuery("from Ruolo",Ruolo.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Ruolo get(Long id) throws Exception {
		return entityManager.find(Ruolo.class, id);
	}

	@Override
	public boolean update(Ruolo o) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Ruolo o) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Ruolo o) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	
	
/*
	@Override
	public void update(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		ruoloInstance = entityManager.merge(ruoloInstance);	
		
	}

	@Override
	public void insert(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(ruoloInstance);
		
	}

	@Override
	public void delete(Ruolo ruoloInstance) throws Exception {
		if (ruoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(ruoloInstance));
		
		
	}
	*/



}
