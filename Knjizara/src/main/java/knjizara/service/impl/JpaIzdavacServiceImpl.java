package knjizara.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import knjizara.model.Izdavac;
import knjizara.repository.IzdavacRepository;
import knjizara.service.IzdavacService;

@Service
@Transactional
public class JpaIzdavacServiceImpl implements IzdavacService{
	
	@Autowired
	private IzdavacRepository izdavacRepository;

	@Override
	public Izdavac findOne(Long id) {
		return izdavacRepository.findOne(id);
	}

	@Override
	public List<Izdavac> findAll() {
		return izdavacRepository.findAll();
	}

	@Override
	public Izdavac save(Izdavac izdavac) {
		return izdavacRepository.save(izdavac);
	}

	@Override
	public Izdavac delete(Long id) {
		Izdavac izdavac = izdavacRepository.findOne(id);
		if(izdavac == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant book");
		}
		izdavacRepository.delete(izdavac);
		return izdavac;
	}

	@Override
	public void delete(List<Long> ids) {
		
	}

}
