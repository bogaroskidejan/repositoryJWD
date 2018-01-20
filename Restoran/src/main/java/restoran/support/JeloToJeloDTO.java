package restoran.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import restoran.model.Jelo;
import restoran.web.dto.JeloDTO;

@Component
public class JeloToJeloDTO implements Converter<Jelo, JeloDTO> {
	
	public JeloDTO convert(Jelo jelo) {
		JeloDTO dto = new JeloDTO();
		
		dto.setId(jelo.getId());
		dto.setNaziv(jelo.getNaziv());
		dto.setCena(jelo.getCena());
		dto.setIdV(jelo.getVrstaJela().getId());
		dto.setNazivV(jelo.getVrstaJela().getNaziv());
		
		return dto;
	}
	
	public List<JeloDTO> convert(List<Jelo> jela){
		List<JeloDTO> ret = new ArrayList<>();
		
		for(Jelo j : jela){
			ret.add(convert(j));
		}
		return ret;
	}

}
