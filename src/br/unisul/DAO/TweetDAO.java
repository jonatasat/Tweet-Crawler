package br.unisul.DAO;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unisul.javabean.Tweet;
import br.unisul.util.PersistenceManager;

public class TweetDAO {
	
private static EntityManagerFactory emf = (EntityManagerFactory) PersistenceManager.getInstance().getEntityManagerFactory();
	
	public static void salvar(Tweet tweet){
		EntityManager em = emf.createEntityManager(); 
		try { 
			EntityTransaction t = em.getTransaction(); 
			try { 
				t.begin();
				em.persist(tweet);
				t.commit(); 
			} finally { 
				if (t.isActive()) t.rollback(); 
			} 
		}finally { 
			em.close(); 
		}
	}
	
	@SuppressWarnings("unused")
	public static Date retornaDataPublicacao(){
		EntityManager em = emf.createEntityManager();
		try {
			 Query q = em.createQuery("select t.dataPublicacao from Tweet t order by t.id desc");
			 q.setMaxResults(1);
			 if(q!=null){
				 return (Date) q.getSingleResult();
			 }
		}catch (NoResultException e){
			return null;
		}finally {
			em.close();
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	public static String retornaUsuario(){
		EntityManager em = emf.createEntityManager();
		try {
			 Query q = em.createQuery("select t.usuario from Tweet t order by t.id desc");
			 q.setMaxResults(1);
			 if(q!=null){
				 return (String) q.getSingleResult();
			 }
		}catch (NoResultException e){
			return null;
		}finally {
			em.close();
		}
		return null;
	}

	
	@SuppressWarnings("unused")
	public static String retornaConteudo(){
		EntityManager em = emf.createEntityManager();
		try {
			 Query q = em.createQuery("select t.conteudo from Tweet t order by t.id desc");
			 q.setMaxResults(1);
			 if(q!=null){
				 return (String) q.getSingleResult();
			 }
		}catch (NoResultException e){
			return null;
		}finally {
			em.close();
		}
		return null;
	}
	
	public static long qtdTweets(){
		EntityManager em = emf.createEntityManager();
		 Query q = null;
		try {
			 q = em.createQuery("select count(*) from Tweet"); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return (long) q.getSingleResult();
	}
}
