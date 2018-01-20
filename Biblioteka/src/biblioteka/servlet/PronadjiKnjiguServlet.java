package biblioteka.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biblioteka.dao.KnjigaDAO;
import biblioteka.model.Knjiga;

/**
 * Servlet implementation class PronadjiKnjiguServlet
 */
public class PronadjiKnjiguServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PronadjiKnjiguServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String naziv = request.getParameter("naziv");
		KnjigaDAO knjigaDAO = new KnjigaDAO();
		Knjiga knjiga = knjigaDAO.getKnjigaByNaziv(naziv);
		
		session.setAttribute("knjiga", knjiga);
		response.sendRedirect("pronadjiKnjigu.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
