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
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
// @Log
public class ImageServiceImpl implements ImageService {@Override

	public void saveImageFile(Long recipeId, MultipartFile file) {
		log.debug("Received a file");
	}

}
