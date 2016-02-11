package br.unisul.servlet;



import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.unisul.classes.LeitorArquivo;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String msg = null;
		try{
			//LeitorArquivo.ler();
			msg = "Entidades carregadas com sucesso !";

		}catch (Exception e){
			e.printStackTrace();
			msg = "Problemas ao carregar as entidades !";
		}
		request.setAttribute("msg2", msg);
		ServletContext sc = getServletConfig().getServletContext();
		RequestDispatcher rd = sc
				.getRequestDispatcher("/entidades.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items;
		String msg=null;

		try {
			items = upload.parseRequest(request);
			Iterator iter = items.iterator();

			while (iter.hasNext()) {

				FileItem item = (FileItem) iter.next();

				if (!item.isFormField()) {
					File uploadedFile = null;

					if (item.getName() != null && !item.getName().equalsIgnoreCase("")) {

						String nomeArquivo = String.valueOf(Calendar.getInstance().getTimeInMillis());
						String caminhoArquivo = item.getName().substring(item.getName().lastIndexOf("\\") + 1, item.getName().length());
						uploadedFile = new File("/usr/local/tomcat7/webapps/Tcc/tmp/"+nomeArquivo + (caminhoArquivo.substring(caminhoArquivo.lastIndexOf("."), caminhoArquivo.length())));
						try {
							item.write(uploadedFile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							msg = "Falha na escrita do arquivo!";
						}
						LeitorArquivo.ler(uploadedFile);
						msg ="Upload realizado com sucesso!";
					}
				}
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg="Falha ao realizar o upload!";
		}

		request.setAttribute("msg2", msg);
		ServletContext sc = getServletConfig().getServletContext();
		RequestDispatcher rd = sc
				.getRequestDispatcher("/entidades.jsp");
		rd.forward(request, response);
	}
}


