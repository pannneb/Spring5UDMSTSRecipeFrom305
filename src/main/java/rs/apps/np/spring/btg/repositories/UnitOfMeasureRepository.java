package rs.apps.np.spring.btg.repositories;

import org.springframework.data.repository.CrudRepository;

import rs.apps.np.spring.btg.recipe.domain.Category;
import rs.apps.np.spring.btg.recipe.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

}
