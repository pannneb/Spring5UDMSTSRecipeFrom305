package rs.apps.np.spring.btg.recipe.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import rs.apps.np.spring.btg.recipe.domain.Recipe;
import rs.apps.np.spring.btg.recipe.services.RecipeService;

/**
 * Use Mockito Mock for RecipeService and Model
 * Verify proper string is returned
 * Verify interactions with mocks
 * 
 * @author Nebojsa
 *
 */
public class IndexControllerTest {

	@Mock
	Model model;
	
	@Mock
	RecipeService recipeService;

	IndexController indexController;

	// @Mock // RecipeRepository je interface i ovde je Mock-ovan
	// RecipeRepository recipeRepository;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		// RecipeService recipeService = new RecipeServiceImpl(recipeRepository);
		indexController = new IndexController(recipeService);

	}

	@Test
	public void testGetIndexPage() {

		//given
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(new Recipe());
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipe.setDescription("desc2");
		recipes.add(recipe);

		when(recipeService.getRecipes()).thenReturn(recipes);
		ArgumentCaptor<Set<Recipe>> ac = ArgumentCaptor.forClass(Set.class);

		//when
		String viewName = indexController.getIndexPage(model);

		//then
		assertEquals("index", viewName);
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), anySet());
		verify(model, times(1)).addAttribute(eq("recipes"), ac.capture());
		
		Set<Recipe> setInController = ac.getValue();
		assertEquals(2, setInController.size());
	}

}
