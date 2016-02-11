package br.unisul.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import br.unisul.javabean.Entidade;
import br.unisul.util.PersistenceManager;

public class EntidadeDAO {
	private static EntityManagerFactory emf = (EntityManagerFactory) PersistenceManager.getInstance().getEntityManagerFactory();
	
	public static void salvar(Entidade en){
		EntityManager em = emf.createEntityManager(); 
		try { 
			EntityTransaction t = em.getTransaction(); 
			try { 
				t.begin();
				em.persist(en);
				t.commit(); 
			} finally { 
				if (t.isActive()) t.rollback(); 
			} 
		}finally { 
			em.close(); 
		}
	}
	
	public static List<Entidade> listar(){
		EntityManager em = emf.createEntityManager();
		try {
			List<Entidade> e =  em.createQuery("from Entidade", Entidade.class).getResultList();
			if (!e.isEmpty()){
				return e;
			}
		}catch (NoResultException e){
			return null;
		}finally {
			em.close();
		}
		return null;
	}

}
