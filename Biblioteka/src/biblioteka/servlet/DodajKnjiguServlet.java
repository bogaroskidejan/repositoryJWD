package biblioteka.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biblioteka.dao.AutorDAO;
import biblioteka.dao.KnjigaDAO;
import biblioteka.model.Autor;
import biblioteka.model.Knjiga;

/**
 * Servlet implementation class DodajKnjiguServlet
 */
public class DodajKnjiguServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajKnjiguServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null){
			response.sendRedirect("login.html");
			return;
		}
		
		String naslov = request.getParameter("naslov");
		int id = Integer.parseInt(request.getParameter("autori"));
//		Knjiga knjiga = new Knjiga(naslov, id);
		KnjigaDAO.insertKnjiga(naslov, id);
		response.sendRedirect("pregledKnjiga.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
