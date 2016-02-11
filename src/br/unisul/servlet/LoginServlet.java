package br.unisul.servlet;

import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unisul.DAO.LoginDAO;
import br.unisul.DAO.StreamDAO;
import br.unisul.security.EncryptSha1;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String passwordEncrypt = EncryptSha1.encryptPassword(senha);
		HttpSession session = request.getSession();
		session.setAttribute("login", login);
		
		String data = null;
		String status = null;
		
		if(StreamDAO.retornaData()!=null){
			data = StreamDAO.retornaData().toString();
		}
		
		if(StreamDAO.retornaStatus()!=null){
			status = StreamDAO.retornaStatus().toString();
		}
		 
		boolean encontrou = false;
		encontrou = LoginDAO.consultaLogin(login, passwordEncrypt);
		
		String txt=null;
		
		if(encontrou==true){
			request.setAttribute("data", data);
			request.setAttribute("status", status);
			ServletContext sc = getServletConfig().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/usuario.jsp");
			rd.forward(request, response);
		}else{
			txt= "Login incorreto !";
			request.setAttribute("msg", txt);
			ServletContext sc = getServletConfig().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
	}
}
