package rs.apps.np.spring.btg.repositories;

import org.springframework.data.repository.CrudRepository;

import rs.apps.np.spring.btg.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

	

}
