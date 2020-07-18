package rs.apps.np.spring.btg.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.apps.np.spring.btg.recipe.domain.Category;
import rs.apps.np.spring.btg.recipe.domain.UnitOfMeasure;

//@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findByDescription(String description);

}
