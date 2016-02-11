package br.unisul.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.DAO.StreamDAO;
import br.unisul.classes.TweetStream;
import br.unisul.javabean.Stream;

/**
 * Servlet implementation class StreamTweetServlet
 */
@WebServlet("/StreamTweetServlet")
public class StreamTweetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StreamTweetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String param = request.getParameter("param");
		if(param.equalsIgnoreCase("start")){
			Stream s = new Stream();
			Date data = new Date(System.currentTimeMillis());
			s.setDataStatus(data);
			s.setStatus("ATIVO");
			StreamDAO.salvar(s);
			TweetStream.startStream();
			
			String dataStream = StreamDAO.retornaData().toString();
			String status = StreamDAO.retornaStatus().toString();
			System.out.println(status);
			
			request.setAttribute("data", dataStream);
			request.setAttribute("status", status);
			ServletContext sc = getServletConfig().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/usuario.jsp");
			rd.forward(request, response);
		}else{
			Stream s = new Stream();
			Date data = new Date(System.currentTimeMillis());
			s.setDataStatus(data);
			s.setStatus("INATIVO");
			StreamDAO.salvar(s);
			TweetStream.stopStream();
			
			String dataStream = StreamDAO.retornaData().toString();
			String status = StreamDAO.retornaStatus().toString();
			
			request.setAttribute("data", dataStream);
			request.setAttribute("status", status);
			ServletContext sc = getServletConfig().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/usuario.jsp");
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
