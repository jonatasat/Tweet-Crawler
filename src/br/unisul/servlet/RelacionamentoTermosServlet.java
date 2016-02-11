package br.unisul.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.unisul.classes.RelacionaTermoFull;

/**
 * Servlet implementation class RelacionamentoTermosServlet
 */
@WebServlet("/RelacionamentoTermosServlet")
public class RelacionamentoTermosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RelacionamentoTermosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String msg2 = null;
		long count = 0;
		try{
			count = RelacionaTermoFull.listarTweets();
			msg2 = "Relacionamentos realizados com sucesso. Quantidade de relacionamentos: "+count;
		}catch(Exception e){
			e.printStackTrace();
			msg2 = "Ocorreu um erro ao relacionar os Termos !";
		}
		
		request.setAttribute("msg2", msg2);
		ServletContext sc = getServletConfig().getServletContext();
		RequestDispatcher rd = sc
				.getRequestDispatcher("/relacionamentos.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
