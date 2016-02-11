package br.unisul.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.DAO.PalavraChaveDAO;
import br.unisul.javabean.PalavraChave;

/**
 * Servlet implementation class PalavrasChaveServlet
 */
@WebServlet("/PalavrasChaveServlet")
public class PalavrasChaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PalavrasChaveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String p = request.getParameter("p");
		String palavra = request.getParameter("palavra");
		
		if(p.equalsIgnoreCase("remover")){
			PalavraChave palavraChave = PalavraChaveDAO.retornaPalavraChave(palavra);
			PalavraChaveDAO.deletar(palavraChave);
			
			List<PalavraChave> list= PalavraChaveDAO.listar();
			
			request.setAttribute("msg2", "Termo removido com sucesso");
			request.setAttribute("list", list);
			ServletContext sc = getServletConfig().getServletContext();
			RequestDispatcher rd = sc
					.getRequestDispatcher("/termos.jsp");
			rd.forward(request, response);
			
			
		}else if(p.equalsIgnoreCase("a")){
			List<PalavraChave> list= PalavraChaveDAO.listar();
			request.setAttribute("list", list);
			ServletContext sc = getServletConfig().getServletContext();
			RequestDispatcher rd = sc
					.getRequestDispatcher("/termos.jsp");
			rd.forward(request, response);
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String palavraChave = request.getParameter("palavraChave");
		String param = request.getParameter("param");

		if(param.equalsIgnoreCase("Salvar")){


			PalavraChave pc = new PalavraChave();
			pc.setPalavra(palavraChave);
			try{
				PalavraChaveDAO.salvar(pc);
			}catch (Exception e){
				e.printStackTrace();
			}
			
			List<PalavraChave> list= PalavraChaveDAO.listar();
			request.setAttribute("list", list);
			request.setAttribute("msg2", "Termo cadastrado com sucesso!");
			ServletContext sc = getServletConfig().getServletContext();
			RequestDispatcher rd = sc
					.getRequestDispatcher("/termos.jsp");
			rd.forward(request, response);

		}
	}
}
