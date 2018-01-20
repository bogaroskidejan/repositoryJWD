package restoran.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import restoran.model.Jelo;
import restoran.service.JeloService;
import restoran.service.VrstaJelaService;
import restoran.web.dto.JeloDTO;

@Component
public class JeloDTOToJelo implements Converter<JeloDTO, Jelo> {
	
	@Autowired
	private JeloService jeloService;
	
	@Autowired
	private VrstaJelaService vrstaJelaService;

	@Override
	public Jelo convert(JeloDTO dto) {
		Jelo jelo = null;
		if(dto.getId() == null) {
			jelo = new Jelo();
			jelo.setVrstaJela(
					vrstaJelaService.findOne(
							dto.getIdV()));
		} else {
			jelo = jeloService.findOne(dto.getId());
			if(jelo == null) {
				throw new IllegalStateException("Editing non-existant Food");
			}
		}
		jelo.setId(dto.getId());
		jelo.setNaziv(dto.getNaziv());
		jelo.setCena(dto.getCena());
		
		return jelo;
	}
	
	public List<Jelo> convert(List<JeloDTO> jelaDTO){
		List<Jelo> ret = new ArrayList<>();
		
		for(JeloDTO dto: jelaDTO){
			ret.add(convert(dto));
		}
		return ret;
	}

}
