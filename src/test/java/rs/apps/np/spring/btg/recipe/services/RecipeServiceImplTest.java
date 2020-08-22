package rs.apps.np.spring.btg.recipe.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rs.apps.np.spring.btg.recipe.commands.RecipeCommand;
import rs.apps.np.spring.btg.recipe.converters.RecipeCommandToRecipe;
import rs.apps.np.spring.btg.recipe.converters.RecipeToRecipeCommand;
import rs.apps.np.spring.btg.recipe.domain.Recipe;
import rs.apps.np.spring.btg.recipe.repositories.RecipeRepository;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;

	@Mock // RecipeRepository je interface i ovde je Mock-ovan
	RecipeRepository recipeRepository;

	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

	@Test
	public void testGetRecipeById() {

		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recOptional = Optional.of(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(recOptional);

		Recipe recipeReturned = recipeService.findById(1L);

		assertNotNull(recipeReturned);
		verify(recipeRepository, times(1)).findById(anyLong());// findAll();
		verify(recipeRepository, never()).findAll();

	}
	
	@Test
	public void getRecipeCommandByIdTest() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(1L);

		when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

		RecipeCommand commandById = recipeService.findCommandById(1L);

		assertNotNull("Null recipe returned", commandById);
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}

	@Test
	public void testGetRecipes() {

		Recipe recipe = new Recipe();
		HashSet recepiesData = new HashSet<>();
		recepiesData.add(recipe);

		when(recipeRepository.findAll()).thenReturn(recepiesData);

		Set<Recipe> recipes = recipeService.getRecipes();
		assertEquals(recipes.size(), 1);

		verify(recipeRepository, times(1)).findAll();

		verify(recipeRepository, never()).findById(anyLong());
		// fail("Not yet implemented");

	}

	@Test
	public void testDeleteById() throws Exception {

		// given
		Long idToDelete = 2L;

		// when
		recipeService.deleteById(idToDelete);

		// then
		verify(recipeRepository, times(1)).deleteById(anyLong());
	}

}
