package rs.apps.np.spring.btg.recipe.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rs.apps.np.spring.btg.recipe.commands.IngredientCommand;
import rs.apps.np.spring.btg.recipe.converters.IngredientCommandToIngredient;
import rs.apps.np.spring.btg.recipe.converters.IngredientToIngredientCommand;
import rs.apps.np.spring.btg.recipe.domain.Ingredient;
import rs.apps.np.spring.btg.recipe.domain.Recipe;
import rs.apps.np.spring.btg.recipe.repositories.RecipeRepository;
import rs.apps.np.spring.btg.recipe.repositories.UnitOfMeasureRepository;

// import lombok.extern.java.Log;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
// @Log
public class IngredientServiceImpl implements IngredientService {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
			RecipeRepository recipeRepository,
			IngredientCommandToIngredient ingredientCommandToIngredient,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (!recipeOptional.isPresent()) {
			// todo impl error handling
			log.error("recipe id not found. Id: " + recipeId);
		}

		Recipe recipe = recipeOptional.get();

		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

		if (!ingredientCommandOptional.isPresent()) {
			// todo impl error handling
			log.error("Ingredient id not found: " + ingredientId);
		}

		return ingredientCommandOptional.get();
	}
	

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
            	Ingredient i = ingredientCommandToIngredient.convert(command);
            	i.setRecipe(recipe);
            	//add new Ingredient
                recipe.addIngredient(i);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();
            
            if (!savedIOptional.isPresent()) {
            	savedIOptional = savedRecipe.getIngredients().stream()
            			.filter(ri->ri.getDescription().equals(command.getDescription()))
						.filter(ri->ri.getAmount().equals(command.getAmount()))
						.filter(ri->ri.getUom().getId().equals(command.getUom().getId()))
						.findFirst();
            }
            
            return ingredientToIngredientCommand.convert(savedIOptional.get());
        }

    }

	@Override
	public void deleteById(Long recipeId, Long id) {
		log.debug("Deleting ingredient:"+recipeId+":"+id);
		Optional<Recipe> rOpt = recipeRepository.findById(recipeId);
		if (rOpt.isPresent()) {
			Recipe r = rOpt.get();
			log.debug("found recipe");
			Optional<Ingredient> ingOpt = r.getIngredients().stream()
					.filter(ing->ing.getId().equals(id)).findFirst();
			if (ingOpt.isPresent()) {
				log.debug("found ingredient");
				Ingredient iToDel = ingOpt.get();
				iToDel.setRecipe(null); // Ovo ce izazvati Hibernate da ga izbrise iz DB
				r.getIngredients().remove(ingOpt.get());
				// ingredientRepository.deleteById(id);
				recipeRepository.save(r);
			}
		} else {
			log.debug("Recipe Id Not found. Id: " + recipeId);
		}
	}
    
    
}
