package restoran.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import restoran.model.Jelo;

public interface JeloService {

	Jelo findOne(Long id);
	Page<Jelo> findAll(int page);
	Page<Jelo> findAllByVrstaJelaNazivLike(String vrstaJelaNaziv, int page);
	Jelo save(Jelo jelo);
	Jelo delete(Long id);
	Page<Jelo> pretraga(
			@Param("naziv") String naziv,
			int page);
	
}
