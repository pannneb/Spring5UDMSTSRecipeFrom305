package rs.apps.np.spring.btg.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.apps.np.spring.btg.recipe.domain.Recipe;

//@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long>{

	

}
