package knjizara.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import knjizara.model.Izdavac;
import knjizara.web.dto.IzdavacDTO;

@Component
public class IzdavacToIzdavacDTO implements Converter<Izdavac, IzdavacDTO> {

	@Override
	public IzdavacDTO convert(Izdavac izdavac) {
		IzdavacDTO dto = new IzdavacDTO();
		dto.setId(izdavac.getId());
		dto.setNaziv(izdavac.getNaziv());
		dto.setEmail(izdavac.getEmail());
		dto.setTelefon(izdavac.getTelefon());
		
		return dto;
	}
	
	public List<IzdavacDTO> convert(List<Izdavac> izdavaci){
		List<IzdavacDTO> ret = new ArrayList<>();
		
		for(Izdavac i : izdavaci){
			ret.add(convert(i));
		}
		return ret;
	}

}
