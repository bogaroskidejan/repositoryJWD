package knjizara.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import knjizara.model.Izdavac;
import knjizara.repository.IzdavacRepository;
import knjizara.web.dto.IzdavacDTO;

@Component
public class IzdavacDTOToIzdavac implements Converter<IzdavacDTO, Izdavac>{

	@Autowired
	private IzdavacRepository izdavacRepository;
	
	@Override
	public Izdavac convert(IzdavacDTO dto) {
		Izdavac izdavac = new Izdavac();
		
		if(izdavac.getId() == null) {
			izdavac = izdavacRepository.findOne(dto.getId());
			
			if(izdavac == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant editor");
			}
		}
		
		izdavac.setId(dto.getId());
		izdavac.setNaziv(dto.getNaziv());
		izdavac.setEmail(dto.getEmail());
		izdavac.setTelefon(dto.getTelefon());
		
		return izdavac;
	}
	
	public List<Izdavac> convert (List<IzdavacDTO> dtoEditors){
		List<Izdavac> editors = new ArrayList<>();
		
		for(IzdavacDTO dto : dtoEditors){
			editors.add(convert(dto));
		}
		return editors;
	}

}
