package servlet1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet1.dao.ProductDAO;
import servlet1.webshop.Product;

/**
 * Servlet implementation class FindProductServlet
 */
public class FindProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindProductServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null){
			response.sendRedirect("login.html");
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDAO productDAO = new ProductDAO();
		Product p = productDAO.getProductByID(id);
		PrintWriter pw = response.getWriter();
		pw.println(p);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
