package servlet1;

import javax.servlet.http.*;



import java.io.*;

import servlet1.dao.ProductDAO;
import servlet1.dao.ShoppingCartItemDAO;
import servlet1.webshop.*;

/**
 * Klasa koja obavlja listanje stavki u korpi, a ako je pozvana iz forme, dodaje
 * jednu stavku u korpu, pa onda lista).
 */
public class ShoppingCartServlet extends HttpServlet {

	private static final long serialVersionUID = -8628509500586512294L;

	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		User user =(User) session.getAttribute("user");
		if(user==null){
			response.sendRedirect("login.html");
			return;
		}
		ProductDAO productDAO = new ProductDAO();
		ShoppingCartItemDAO shoppingCartItemDAO = new ShoppingCartItemDAO();
		
		ShoppingCart sc = ((User) session.getAttribute("user")).getShoppingCart();
		
		if ( request.getParameter("itemId") != null ) {
			try {
				Product product = productDAO.getProductByID(Integer.parseInt(request.getParameter("itemId")));
				ShoppingCartItem sci = new ShoppingCartItem(user, product, Integer.parseInt(request.getParameter("itemCount")));
				shoppingCartItemDAO.insertItem(sci);
				sc.addItem(sci);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		response.sendRedirect("ShoppingCart.jsp");

	}
}
