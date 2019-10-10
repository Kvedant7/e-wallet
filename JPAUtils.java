package com.capgemini.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
	private static EntityManagerFactory emf;	
	private static EntityManager em;
	
	static{
		emf = Persistence.createEntityManagerFactory("JPA-PU");
	}
	
	public EntityManager getEntityManager(){
		if(em==null||!em.isOpen()){
			em = emf.createEntityManager();
		}
		return em;
		
	}
	
	
}
