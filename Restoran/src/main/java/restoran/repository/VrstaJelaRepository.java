package restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.VrstaJela;

@Repository
public interface VrstaJelaRepository extends JpaRepository<VrstaJela, Long> {
	
}
