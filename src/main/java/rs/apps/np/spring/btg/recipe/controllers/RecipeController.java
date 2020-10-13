package rs.apps.np.spring.btg.recipe.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import rs.apps.np.spring.btg.recipe.commands.RecipeCommand;
import rs.apps.np.spring.btg.recipe.domain.Recipe;
import rs.apps.np.spring.btg.recipe.exceptions.NotFoundException;
import rs.apps.np.spring.btg.recipe.services.RecipeService;

@Controller
@Slf4j
public class RecipeController {

	private final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";

	RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@GetMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		return "recipe/show";
	}

	@GetMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());

		return RECIPE_RECIPEFORM_URL;
	}

	@GetMapping("/recipe/{id}/update")
	public String updateById(@PathVariable String id, Model model) {
		RecipeCommand r = recipeService.findCommandById(new Long(id));
		System.out.println("updateById r:" + r);
		model.addAttribute("recipe", r);
		return RECIPE_RECIPEFORM_URL;
	}

	@PostMapping("recipe")
	public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult result) {

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error->{
				log.debug(error.toString());
			});

			// model.addAttribute("recipe", new Recipe());
			return RECIPE_RECIPEFORM_URL;
		}
		
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

		return "redirect:/recipe/" + savedCommand.getId()+"/show";

	}

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception e) {
    	log.error("Handling not found exception");
    	log.error(e.getMessage());
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("404error");
    	mav.addObject("exception", e);
    	
    	return mav;
    }

	//    @ResponseStatus(HttpStatus.BAD_REQUEST)
	//    @ExceptionHandler(NumberFormatException.class)
	//    public ModelAndView numberFormatException(Exception e) {
	//    	log.error("Handling number format exception");
	//    	log.error(e.getMessage());
	//    	ModelAndView mav = new ModelAndView();
	//    	mav.setViewName("400error");
	//    	mav.addObject("exception", e);
	//      	return mav;
	//    }

}
