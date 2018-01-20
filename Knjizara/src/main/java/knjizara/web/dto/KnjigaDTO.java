package knjizara.web.dto;

public class KnjigaDTO {
	
	private Long id;
	private String naziv;
	private String pisac;
	private String ISBN;
	private int kolicina;
	private double cena;
	private Long idIzdavaca;
	private String nazivIzdavaca;

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
	
	public String getPisac() {
		return pisac;
	}
	
	public void setPisac(String pisac) {
		this.pisac = pisac;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	public int getKolicina() {
		return kolicina;
	}
	
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
	public double getCena() {
		return cena;
	}
	
	public void setCena(double cena) {
		this.cena = cena;
	}

	public Long getIdIzdavaca() {
		return idIzdavaca;
	}

	public void setIdIzdavaca(Long idIzdavaca) {
		this.idIzdavaca = idIzdavaca;
	}

	public String getNazivIzdavaca() {
		return nazivIzdavaca;
	}

	public void setNazivIzdavaca(String nazivIzdavaca) {
		this.nazivIzdavaca = nazivIzdavaca;
	}
	
}
