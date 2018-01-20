package knjizara.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import knjizara.model.Knjiga;
import knjizara.repository.IzdavacRepository;
import knjizara.repository.KnjigaRepository;
import knjizara.web.dto.KnjigaDTO;

@Component
public class KnjigaDTOToKnjiga implements Converter<KnjigaDTO, Knjiga>{

	@Autowired
	private KnjigaRepository knjigaRepository;
	
	@Autowired
	private IzdavacRepository izdavacRepository;
	
	@Override
	public Knjiga convert(KnjigaDTO dto) {
		Knjiga knjiga;
		
		if(dto.getId() == null) {
			knjiga = new Knjiga();
			knjiga.setIzdavac(
					izdavacRepository.findOne(
							dto.getIdIzdavaca()));
		} else {
			knjiga = knjigaRepository.findOne(
					dto.getId());
			if(knjiga == null) {
				throw new IllegalStateException("Editing non-existant book");
			}
		}
		
		knjiga.setNaziv(dto.getNaziv());
		knjiga.setPisac(dto.getPisac());
		knjiga.setISBN(dto.getISBN());
		knjiga.setKolicina(dto.getKolicina());
		knjiga.setCena(dto.getCena());
		
		return knjiga;
	}
	
	public List<Knjiga> convert (List<KnjigaDTO> dtoBooks){
		List<Knjiga> books = new ArrayList<>();
		
		for(KnjigaDTO dto : dtoBooks){
			books.add(convert(dto));
		}
		return books;
	}

}
