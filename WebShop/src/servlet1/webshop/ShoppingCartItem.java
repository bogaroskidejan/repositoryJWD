package servlet1.webshop;

/** Reprezentuje stavku u korpi. Stavku cine proizvod i kolicina. */
public class ShoppingCartItem {

	private static int idCounter;
	private User user;
	private Product product;
	private int count;
	private int id;

	public int getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ShoppingCartItem(Product p, int count) {
		this.product = p;
		this.count = count;
		this.id = idCounter++;
	}
	
	public ShoppingCartItem(User user, Product product, int count, int id) {
		this.user = user;
		this.product = product;
		this.count = count;
		this.id = id;
	}

	public ShoppingCartItem(User user, Product product, int count) {
		this.user = user;
		this.product = product;
		this.count = count;
	}

	public void setProduct(Product p) {
		product = p;
	}

	public Product getProduct() {
		return product;
	}

	public void setCount(int c) {
		count = c;
	}

	public int getCount() {
		return count;
	}

	@Override
	public boolean equals(Object obj) {
		ShoppingCartItem sci = (ShoppingCartItem) obj;
		return sci.getId() == this.id;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [user=" + user + ", product=" + product
				+ ", count=" + count + ", id=" + id + "]";
	}

}
