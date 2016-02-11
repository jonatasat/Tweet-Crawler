package br.unisul.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.unisul.javabean.Usuario;
import br.unisul.util.PersistenceManager;

public class UsuarioDAO {
	private static EntityManagerFactory emf = (EntityManagerFactory) PersistenceManager.getInstance().getEntityManagerFactory();
	
	public static void salvar(Usuario usuario){
		EntityManager em = emf.createEntityManager(); 
		try { 
			EntityTransaction t = em.getTransaction(); 
			try { 
				t.begin();
				em.persist(usuario);
				t.commit(); 
			} finally { 
				if (t.isActive()) t.rollback(); 
			} 
		}finally { 
			em.close(); 
		}
	}
}
