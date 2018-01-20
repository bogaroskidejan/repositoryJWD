package knjizara.service;

import java.util.List;

import knjizara.model.Izdavac;

public interface IzdavacService {
	
	Izdavac findOne(Long id);
	List<Izdavac> findAll();
	Izdavac save(Izdavac izdavac);
	Izdavac delete(Long id);
	void delete(List<Long> ids);

}
