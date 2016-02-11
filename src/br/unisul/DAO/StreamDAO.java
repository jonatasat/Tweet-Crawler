package br.unisul.DAO;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import br.unisul.javabean.Stream;
import br.unisul.util.PersistenceManager;

public class StreamDAO {
private static EntityManagerFactory emf = (EntityManagerFactory) PersistenceManager.getInstance().getEntityManagerFactory();
	
	public static void salvar(Stream stream){
		EntityManager em = emf.createEntityManager(); 
		try { 
			EntityTransaction t = em.getTransaction(); 
			try { 
				t.begin();
				em.persist(stream);
				t.commit(); 
			} finally { 
				if (t.isActive()) t.rollback(); 
			} 
		}finally { 
			em.close(); 
		}
	}
	
	public static Date retornaData(){
		EntityManager em = emf.createEntityManager();
		try {
			
			Date q = (Date) em.createQuery("select max(s.dataStatus) from Stream s").getSingleResult();
			
			if(q !=null){
				return q;
			}
		}catch (NoResultException e){
			return null;
			
		}finally {
			em.close();
		}
		return null;
	}
	
	public static String retornaStatus(){
		EntityManager em = emf.createEntityManager();
		try {
			String s = (String) em.createQuery("select t.status from Stream t where id=(select max(t1.id) from Stream t1)").getSingleResult();
			
			if(!s.isEmpty()){
				return s;
			}
		}catch (NoResultException e){
			return null;
		}finally {
			em.close();
		}
		return null;
	}
}
