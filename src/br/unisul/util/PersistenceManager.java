package br.unisul.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
	public static final boolean DEBUG = true;
	private static final PersistenceManager singleton = new PersistenceManager();
	protected EntityManagerFactory emf;
	
	public static PersistenceManager getInstance() { 
		return singleton; 
	} 
	private PersistenceManager() { 

	}
	public EntityManagerFactory getEntityManagerFactory() { 
		try { 
			Class.forName("com.mysql.jdbc.Driver"); 
		} 
		catch (ClassNotFoundException e) { 
			e.printStackTrace(); 
		} 
		if (emf == null) createEntityManagerFactory(); 
		return emf;
	} 
	
	public void closeEntityManagerFactory() { 
		if (emf != null) { 
			emf.close(); 
			emf = null; 
			if (DEBUG) System.out.println("Persistence finished at " + new java.util.Date()); 
		} 
	}
	
	protected void createEntityManagerFactory() {
		this.emf = Persistence.createEntityManagerFactory("persist");
		if (DEBUG)
			System.out.println("Persistence started at " + new java.util.Date());
	}
}

