package rs.apps.np.spring.btg.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.apps.np.spring.btg.recipe.commands.RecipeCommand;
import rs.apps.np.spring.btg.recipe.domain.Recipe;
import rs.apps.np.spring.btg.recipe.services.RecipeService;

@Controller
public class RecipeController {

	RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		return "recipe/show";
	}

	@RequestMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());

		return "recipe/recipeform";
	}

	@RequestMapping("/recipe/{id}/update")
	public String updateById(@PathVariable String id, Model model) {
		RecipeCommand r = recipeService.findCommandById(new Long(id));
		System.out.println("updateById r:" + r);
		model.addAttribute("recipe", r);
		return "recipe/recipeform";
	}

	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

		return "redirect:/recipe/" + savedCommand.getId()+"/show";
	}

}
