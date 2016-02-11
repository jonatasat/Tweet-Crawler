package br.unisul.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.unisul.DAO.UsuarioDAO;
import br.unisul.javabean.Usuario;
import br.unisul.security.*;

/**
 * Servlet implementation class CriaUsuarioServlet
 */
@WebServlet("/CriaUsuarioServlet")
public class CriaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriaUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		String encryptPassword = EncryptSha1.encryptPassword(senha);
		
		Usuario u = new Usuario();
		u.setLogin(login);
		u.setSenha(encryptPassword);
		
		UsuarioDAO.salvar(u);
		
		ServletContext sc = getServletConfig().getServletContext();
		RequestDispatcher rd = sc
				.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
		
	}

}
