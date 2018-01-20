package knjizara.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import knjizara.model.Knjiga;
import knjizara.web.dto.KnjigaDTO;

@Component
public class KnjigaToKnjigaDTO implements Converter<Knjiga, KnjigaDTO>{

	@Override
	public KnjigaDTO convert(Knjiga knjiga) {
		KnjigaDTO dto = new KnjigaDTO();
		
		dto.setId(knjiga.getId());
		dto.setNaziv(knjiga.getNaziv());
		dto.setPisac(knjiga.getPisac());
		dto.setISBN(knjiga.getISBN());
		dto.setKolicina(knjiga.getKolicina());
		dto.setCena(knjiga.getCena());
		dto.setIdIzdavaca(knjiga.getIzdavac().getId());
		dto.setNazivIzdavaca(knjiga.getIzdavac().getNaziv());
		
		return dto;
	}

	public List<KnjigaDTO> convert(List<Knjiga> knjige){
		List<KnjigaDTO> ret = new ArrayList<>();
		
		for(Knjiga k : knjige){
			ret.add(convert(k));
		}
		return ret;
	}
	
}
