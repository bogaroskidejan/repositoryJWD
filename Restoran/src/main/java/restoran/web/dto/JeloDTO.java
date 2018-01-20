package restoran.web.dto;

public class JeloDTO {
	
	private Long id;
	private String naziv;
	private double cena;
	private Long idV;
	private String nazivV;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Long getIdV() {
		return idV;
	}

	public void setIdV(Long idV) {
		this.idV = idV;
	}
	
	public String getNazivV() {
		return nazivV;
	}

	public void setNazivV(String nazivV) {
		this.nazivV = nazivV;
	}
	
}
