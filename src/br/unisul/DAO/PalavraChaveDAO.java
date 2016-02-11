package br.unisul.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unisul.javabean.PalavraChave;
import br.unisul.util.PersistenceManager;

public class PalavraChaveDAO {
	
private static EntityManagerFactory emf = (EntityManagerFactory) PersistenceManager.getInstance().getEntityManagerFactory();
	
	public static void salvar(PalavraChave pc){
		EntityManager em = emf.createEntityManager(); 
		try { 
			EntityTransaction t = em.getTransaction(); 
			try { 
				t.begin();
				em.persist(pc);
				t.commit(); 
			} finally { 
				if (t.isActive()) t.rollback(); 
			} 
		}finally { 
			em.close(); 
		}
	}
	
	public static List<PalavraChave> listar(){
		EntityManager em = emf.createEntityManager();
		try {
			List<PalavraChave> q =  em.createQuery("from PalavraChave", PalavraChave.class).getResultList();
			if (!q.isEmpty()){
				return q;
			}
		}catch (NoResultException e){
			return null;
		}finally {
			em.close();
		}
		return null;
	}
	
	public static void deletar(PalavraChave pc){
		EntityManager em = emf.createEntityManager(); 
		try { 
			EntityTransaction t = em.getTransaction(); 
			try {
				t.begin();
				Object o = em.merge(pc);
				em.remove(o);
				t.commit(); 
			} finally { 
				if (t.isActive()) t.rollback(); 
			} 
		}finally { 
			em.close(); 
		}
	}
	
	public static PalavraChave retornaPalavraChave(String palavra){
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("from PalavraChave where palavra = :palavra");
			q.setParameter("palavra", palavra);
			PalavraChave pc = (PalavraChave) q.getSingleResult();
			return pc;
		}catch (NoResultException e){
			return null;
		}finally {
			em.close();
		}
	}
}
