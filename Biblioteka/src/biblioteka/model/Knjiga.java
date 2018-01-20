  package biblioteka.model;

public class Knjiga {
	
	private int id;
	private String naslov;
	private Autor autor;
	
	public Knjiga(String naslov, Autor autor) {
		this.naslov = naslov;
		this.autor= autor;
	}
	
	public Knjiga(int id, String naslov, Autor autor) {
		this.id = id;
		this.naslov = naslov;
		this.autor = autor;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
}
