package restoran.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import restoran.model.VrstaJela;
import restoran.web.dto.VrstaJelaDTO;

@Component
public class VrstaJelaToVrstaJelaDTO implements Converter<VrstaJela, VrstaJelaDTO>{

	@Override
	public VrstaJelaDTO convert(VrstaJela vrstaJela) {
		VrstaJelaDTO dto = new VrstaJelaDTO();
		
		dto.setId(vrstaJela.getId());
		dto.setNaziv(vrstaJela.getNaziv());
		dto.setOpis(vrstaJela.getOpis());
		
		return dto;
	}
	
	public List<VrstaJelaDTO> convert(List<VrstaJela> vrsteJela){
		List<VrstaJelaDTO> ret = new ArrayList<>();
		
		for(VrstaJela j : vrsteJela){
			ret.add(convert(j));
		}
		return ret;
	}

}
