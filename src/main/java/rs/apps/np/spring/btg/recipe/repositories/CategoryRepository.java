package rs.apps.np.spring.btg.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.apps.np.spring.btg.recipe.domain.Category;

// @Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByDescription(String description);
	
}
