package servlet1.webshop;

import java.util.ArrayList;

/**
 * Reperezentuje korpu za kupovinu. Sadrzi vektor stavki (uredjeni par
 * (proizvod, kolicina)).
 */
public class ShoppingCart {
	
	private ArrayList<ShoppingCartItem> items;

	public ShoppingCart() {
		items = new ArrayList<ShoppingCartItem>();
	}

	public void addItem(ShoppingCartItem sci) {
		items.add(sci);
	}

	public ArrayList<ShoppingCartItem> getItems() {
		return items;
	}
	
	public boolean removeItem(int id){
		for (int i = 0; i < items.size(); i++) {
			if(items.get(i).getId() == id){
				items.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "ShoppingCart [items=" + items + "]";
	}
}
