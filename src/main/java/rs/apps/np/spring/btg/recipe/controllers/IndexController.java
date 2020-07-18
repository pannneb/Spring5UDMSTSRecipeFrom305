package rs.apps.np.spring.btg.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.apps.np.spring.btg.recipe.services.RecipeService;

@Controller
public class IndexController {

	private RecipeService recipeService;

	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	
//	private CategoryRepository categoryRepository;
//	private UnitOfMeasureRepository unitOfMeasureRepository;
//
//	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
//		this.categoryRepository = categoryRepository;
//		this.unitOfMeasureRepository = unitOfMeasureRepository;
//	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage(Model model) {

//		String categoryDesc = "Americanaaa";
//		Optional<Category> categoryOptional = categoryRepository.findByDescription(categoryDesc);
//		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
//
//		if (categoryOptional.isPresent()) {
//			System.out.println("Category ID is :" + categoryOptional.get().getId());
//		} else {
//			System.out.println("Category: " + categoryDesc + " not found ");	
//		}
//		System.out.println("UnitOfMeasure ID is :" +unitOfMeasureOptional.get().getId());

		model.addAttribute("recipes", recipeService.getRecipes());

		return "index";
	}

}
