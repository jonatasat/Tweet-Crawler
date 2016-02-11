package br.unisul.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.unisul.DAO.TweetDAO;

/**
 * Servlet implementation class HistoricoServlet
 */
@WebServlet("/HistoricoServlet")
public class HistoricoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoricoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String conteudo = null;
		if(TweetDAO.retornaConteudo()!=null){
			conteudo = TweetDAO.retornaConteudo().toString();
		}
		
		String usuario = null;
		if(TweetDAO.retornaUsuario()!=null){
			usuario = TweetDAO.retornaUsuario().toString();
		}
		
		String data =null;
		if(TweetDAO.retornaDataPublicacao()!=null){
			data = TweetDAO.retornaDataPublicacao().toString();
		}
		request.setAttribute("conteudo", conteudo);
		request.setAttribute("usuario", usuario);
		request.setAttribute("data", data);
		ServletContext sc = getServletConfig().getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/historico.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
