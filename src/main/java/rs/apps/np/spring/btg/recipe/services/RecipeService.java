package rs.apps.np.spring.btg.recipe.services;

import java.util.Set;

import rs.apps.np.spring.btg.recipe.commands.RecipeCommand;
import rs.apps.np.spring.btg.recipe.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();

	Recipe findById(Long id);

    RecipeCommand findCommandById(Long l);
    
	RecipeCommand saveRecipeCommand(RecipeCommand rc);

    void deleteById(Long idToDelete);

}
