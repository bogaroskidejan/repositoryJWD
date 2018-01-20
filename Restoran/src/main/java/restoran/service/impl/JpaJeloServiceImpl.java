package restoran.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import restoran.model.Jelo;
import restoran.repository.JeloRepository;
import restoran.service.JeloService;

@Service
@Transactional
public class JpaJeloServiceImpl implements JeloService {
	
	@Autowired
	private JeloRepository jeloRepository;

	public Jelo findOne(Long id) {
		return jeloRepository.findOne(id);
	}

	public Page<Jelo> findAll(int page) {
		return jeloRepository.findAll(new PageRequest(page, 4));
	}
	
	public Jelo save(Jelo jelo) {
		return jeloRepository.save(jelo);
	}

	public Jelo delete(Long id) {
		Jelo jelo = jeloRepository.findOne(id);
		if(jelo == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant food");
		}
		jeloRepository.delete(jelo);
		return jelo;
	}
	
	@Override
	public Page<Jelo> findAllByVrstaJelaNazivLike(String vrstaJelaNaziv, int page) {
		if(vrstaJelaNaziv != null ){
			vrstaJelaNaziv = "%" + vrstaJelaNaziv + "%";
		}
		return jeloRepository.findAllByVrstaJelaNazivLike(vrstaJelaNaziv,  new PageRequest(page, 5));
	}

	@Override
	public Page<Jelo> pretraga(String naziv, int page) {
		if(naziv != null ){
			naziv = "%" + naziv + "%";
		}
		return jeloRepository.pretraga(naziv, new PageRequest(page, 5));
	}

}
