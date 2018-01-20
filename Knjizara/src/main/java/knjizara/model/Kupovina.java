package knjizara.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Kupovina")
public class Kupovina {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	private Knjiga knjiga;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
		if(knjiga.getKupovine().contains(this)){
			knjiga.getKupovine().add(this);
		}
	}
	
}
