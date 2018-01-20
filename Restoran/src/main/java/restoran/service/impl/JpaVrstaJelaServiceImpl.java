package restoran.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restoran.model.VrstaJela;
import restoran.repository.VrstaJelaRepository;
import restoran.service.VrstaJelaService;

@Service
@Transactional
public class JpaVrstaJelaServiceImpl implements VrstaJelaService {
	
	@Autowired
	private VrstaJelaRepository vrstaJelaRepository;

	public VrstaJela findOne(Long id) {
		return vrstaJelaRepository.findOne(id);
	}

	public List<VrstaJela> findAll() {
		return vrstaJelaRepository.findAll();
	}

	public VrstaJela save(VrstaJela vrstaJela) {
		return vrstaJelaRepository.save(vrstaJela);
	}

	public VrstaJela delete(Long id) {
		VrstaJela vrstaJela = vrstaJelaRepository.findOne(id);
		if(vrstaJela == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant type of food");
		}
		vrstaJelaRepository.delete(vrstaJela);
		return vrstaJela;
	}

}
