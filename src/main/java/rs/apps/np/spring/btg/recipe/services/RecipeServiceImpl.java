package rs.apps.np.spring.btg.recipe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rs.apps.np.spring.btg.recipe.commands.RecipeCommand;
import rs.apps.np.spring.btg.recipe.converters.RecipeCommandToRecipe;
import rs.apps.np.spring.btg.recipe.converters.RecipeToRecipeCommand;
import rs.apps.np.spring.btg.recipe.domain.Recipe;
import rs.apps.np.spring.btg.recipe.repositories.RecipeRepository;
// import lombok.extern.java.Log;

@Service
@Slf4j
// @Log
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	
	public RecipeServiceImpl(RecipeRepository recipeRepository,
			RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		super();
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.info("I'm in the service RecipeServiceImpl");
		System.out.println("sysout I'm in the service RecipeServiceImpl");
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Recipe findById(Long id) {
		
		Optional<Recipe> recipeOptional =  recipeRepository.findById(id);
		if (!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found");
		}
		
		return recipeOptional.get();
		// TODO Auto-generated method stub
		// return null;
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand rc) {
		Recipe detachedRecipe = recipeCommandToRecipe.convert(rc);

		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		log.debug("Saved RecipeId:"+savedRecipe.getId());
		return recipeToRecipeCommand.convert(savedRecipe);

	}

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }

}
