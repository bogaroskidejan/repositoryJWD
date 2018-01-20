package restoran.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="VrstaJela")
public class VrstaJela {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="opis")
	private String opis;
	
	@OneToMany(mappedBy="vrstaJela",cascade=CascadeType.REMOVE)
	private List<Jelo> jela = new ArrayList<Jelo>();
	
	public VrstaJela() {}

	public VrstaJela(Long id, String naziv, String opis, List<Jelo> jela) {
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.jela = jela;
	}

	public VrstaJela(String naziv, String opis, List<Jelo> jela) {
		this.naziv = naziv;
		this.opis = opis;
		this.jela = jela;
	}

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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Jelo> getJela() {
		return jela;
	}

	public void setJela(List<Jelo> jela) {
		this.jela = jela;
	}
	
	public void addJelo(Jelo jelo){ 
		this.jela.add(jelo);
		if(!(jelo.getVrstaJela().equals(this))){ 
			jelo.setVrstaJela(this);
		}
}
	
}
