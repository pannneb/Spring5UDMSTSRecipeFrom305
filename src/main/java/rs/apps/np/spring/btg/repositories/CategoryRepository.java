package rs.apps.np.spring.btg.repositories;

import org.springframework.data.repository.CrudRepository;

import rs.apps.np.spring.btg.recipe.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
