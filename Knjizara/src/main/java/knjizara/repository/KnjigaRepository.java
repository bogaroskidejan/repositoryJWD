package knjizara.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import knjizara.model.Knjiga;

@Repository
public interface KnjigaRepository extends PagingAndSortingRepository<Knjiga, Long>{
	
	Page<Knjiga> findByIzdavacId(Long izdavacId, Pageable pageRequest);
	
	@Query("SELECT k FROM Knjiga k WHERE "
			+ "(:naziv IS NULL or k.naziv like :naziv ) AND "
			+ "(:pisac IS NULL OR k.pisac like :pisac  ) AND "
			+ "(:gornjaKol IS NULL OR k.kolicina <= :gornjaKol)"
			)
	Page<Knjiga> pretraga(
			@Param("naziv") String naziv, 
			@Param("pisac") String pisac, 
			@Param("gornjaKol") Integer gornjaKol,
			Pageable pageRequest);

}
