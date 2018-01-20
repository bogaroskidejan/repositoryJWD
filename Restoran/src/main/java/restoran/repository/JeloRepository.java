package restoran.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import restoran.model.Jelo;

@Repository
public interface JeloRepository extends PagingAndSortingRepository<Jelo, Long> {
	
	Page<Jelo> findAllByVrstaJelaNazivLike(String vrstaJelaNaziv, Pageable pageRequest);
	
	@Query("SELECT j FROM Jelo j WHERE "
			+ "(:naziv IS NULL or j.naziv like :naziv )"
			)
	Page<Jelo> pretraga(
			@Param("naziv") String naziv,
			Pageable pageRequest);

}
