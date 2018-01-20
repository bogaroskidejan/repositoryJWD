package restoran.service;

import java.util.List;

import restoran.model.VrstaJela;

public interface VrstaJelaService {
	
	VrstaJela findOne(Long id);
	List<VrstaJela> findAll();
	VrstaJela save(VrstaJela vrstaJela);
	VrstaJela delete(Long id);

}
