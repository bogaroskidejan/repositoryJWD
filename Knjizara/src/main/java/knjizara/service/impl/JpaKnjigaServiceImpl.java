package knjizara.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import knjizara.model.Knjiga;
import knjizara.repository.KnjigaRepository;
import knjizara.service.KnjigaService;

@Service
@Transactional
public class JpaKnjigaServiceImpl implements KnjigaService{
	
	@Autowired
	private KnjigaRepository knjigaRepository;

	@Override
	public Knjiga findOne(Long id) {
		return knjigaRepository.findOne(id);
	}

	@Override
	public Page<Knjiga> findAll(int page) {
		return knjigaRepository.findAll(new PageRequest(page, 4));
	}

	@Override
	public Knjiga save(Knjiga knjiga) {
		return knjigaRepository.save(knjiga);
	}

	@Override
	public Knjiga delete(Long id) {
		Knjiga knjiga = knjigaRepository.findOne(id);
		if(knjiga == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant book");
		}
		knjigaRepository.delete(knjiga);
		return knjiga;
	}

	@Override
	public void delete(List<Long> ids) {
		
	}

	@Override
	public Page<Knjiga> pretraga(String naziv, String pisac, Integer gornjaKol, int page) {
		if(naziv != null ){
			naziv = "%" + naziv + "%";
		}
		if(pisac != null ){
			pisac = "%" + pisac + "%";
		}
		return knjigaRepository.pretraga(naziv, pisac, gornjaKol, new PageRequest(page, 4));
	}

	@Override
	public Page<Knjiga> findByIzdavacId(int page, Long izdavacId) {
		return knjigaRepository.findByIzdavacId(izdavacId, new PageRequest(page, 4));
	}

}
