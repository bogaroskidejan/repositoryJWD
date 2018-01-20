package menjacnicaBaze.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class KursnaLista {
	
	private Date datumFormiranja;
	private List<VrednostValute> vrednostiValute = new ArrayList<>();

	public KursnaLista() {
	}

	public KursnaLista(Date datumFormiranja) {
		this.datumFormiranja = datumFormiranja;
	}

	public KursnaLista(Date datumFormiranja, ArrayList<VrednostValute> vrednostValuteArr) {
		this.datumFormiranja = datumFormiranja;
		this.vrednostiValute = vrednostValuteArr;
	}

	public Date getDatumFormiranja() {
		return datumFormiranja;
	}

	public void setDatumFormiranja(Date datumFormiranja) {
		this.datumFormiranja = datumFormiranja;
	}

	public List<VrednostValute> getVrednostValuteArr() {
		return vrednostiValute;
	}

	public void setVrednostValuteArr(ArrayList<VrednostValute> vrednostValuteArr) {
		this.vrednostiValute = vrednostValuteArr;
	}

}
