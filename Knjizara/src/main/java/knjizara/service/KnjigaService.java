package knjizara.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import knjizara.model.Knjiga;

public interface KnjigaService {
	
	Knjiga findOne(Long id);
	Page<Knjiga> findAll(int page);
	Knjiga save(Knjiga jelo);
	Knjiga delete(Long id);
	void delete(List<Long> ids);
	Page<Knjiga> findByIzdavacId(int page, Long izdavacId);
	Page<Knjiga> pretraga(
			@Param("naziv") String naziv, 
			@Param("pisac") String pisac, 
			@Param("gornjaKol") Integer gornjaKol,
			int page);

}
