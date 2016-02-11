package br.unisul.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import br.unisul.javabean.Usuario;
import br.unisul.util.PersistenceManager;

public class LoginDAO {
	private static EntityManagerFactory emf = (EntityManagerFactory) PersistenceManager.getInstance().getEntityManagerFactory();
	
	public static boolean consultaLogin(String login, String senha){
		EntityManager em = emf.createEntityManager();
		boolean encontrou=false;
		try {
			TypedQuery<Usuario> query = em.createQuery("from Usuario where LOGIN = :login and SENHA = :senha", Usuario.class);
			((Query) query).setParameter("login", login);
			((Query) query).setParameter("senha", senha);
			
			if(query.getResultList().isEmpty()){
				encontrou=false;
				return encontrou;
			}else{
				encontrou=true;
				return encontrou;
			}
		}finally {
			em.close();
		}
	}
}
