package servlet1;

import java.io.*;
import java.util.List;


import javax.servlet.http.*;

import servlet1.dao.ProductDAO;
import servlet1.webshop.*;

/**
 * Osnovni servlet koji lista raspolozive proizvode i omogucuje njihovo
 * dodavanje u korpu.
 */
public class WebShopServlet extends HttpServlet {

	private static final long serialVersionUID = 6593194247788949676L;
	

	/**
	 * Atribut se dodaje u application scope, da bi se video iz klase ShoppingCartServlet.
	 */
	/*
	 * Obratiti paznju da se metod init() zove samo jednom, prilikom prvo pokretanja (inicijalziacije)
	 * servleta.
	 * => Ukoliko bismo u products.txt dodali novi proizvod, bez restartovanja web servera, a prethodno
	 * je servlet vec bio pokrenut, novi proizvod nece biti procitan.
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null){
			response.sendRedirect("login.html");
			return;
		}
		ProductDAO productDAO = new ProductDAO();
		List<Product> products = productDAO.getAllProducts();
        session.setAttribute("products", products);
        response.sendRedirect("webShop.jsp");
		
	}
}