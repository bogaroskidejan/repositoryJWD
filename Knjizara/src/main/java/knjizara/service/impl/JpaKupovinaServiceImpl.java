package knjizara.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import knjizara.model.Knjiga;
import knjizara.model.Kupovina;
import knjizara.repository.KnjigaRepository;
import knjizara.repository.KupovinaRepository;
import knjizara.service.KupovinaService;

@Service
public class JpaKupovinaServiceImpl implements KupovinaService{
	
	@Autowired
	private KupovinaRepository kupovinaRepository;
	
	@Autowired
	private KnjigaRepository knjigaRepository;

	@Override
	public Kupovina buyABook(Long knjigaId) {
		
		if(knjigaId == null) {
			throw new IllegalArgumentException("Id of a book cannot be null!");
		}
		
		Knjiga knjiga = knjigaRepository.findOne(knjigaId);
		if(knjiga == null) {
			throw new IllegalArgumentException("There is no book with given id!");
		}
		
		if(knjiga.getKolicina() > 0) {
			
			Kupovina kupovina = new Kupovina();
			kupovina.setKnjiga(knjiga);
			
			knjiga.setKolicina(knjiga.getKolicina() - 1);
			
			kupovinaRepository.save(kupovina);
			knjigaRepository.save(knjiga);
			
			return kupovina;
		}
		
		return null;
	}

}
