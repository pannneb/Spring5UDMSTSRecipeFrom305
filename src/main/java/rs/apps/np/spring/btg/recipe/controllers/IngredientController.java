package rs.apps.np.spring.btg.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import rs.apps.np.spring.btg.recipe.commands.IngredientCommand;
import rs.apps.np.spring.btg.recipe.commands.RecipeCommand;
import rs.apps.np.spring.btg.recipe.commands.UnitOfMeasureCommand;
import rs.apps.np.spring.btg.recipe.domain.Recipe;
import rs.apps.np.spring.btg.recipe.services.IngredientService;
import rs.apps.np.spring.btg.recipe.services.RecipeService;
import rs.apps.np.spring.btg.recipe.services.UnitOfMeasureService;

@Controller
@Slf4j
public class IngredientController {

	private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final IngredientService ingredientService;

	public IngredientController(RecipeService recipeService, 
			IngredientService ingredientService,UnitOfMeasureService unitOfMeasureService) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        return "recipe/ingredient/show";
    }


    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipeIngredient(@PathVariable String recipeId,
                                       Model model){
    	
    	RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

		if (recipeCommand == null) {
			throw new RuntimeException("recipeCommand is null");
		}
    	IngredientCommand ic = new IngredientCommand();
    	ic.setRecipeId(Long.valueOf(recipeId));
    	
    	
        model.addAttribute("ingredient", ic);

        ic.setUom(new UnitOfMeasureCommand());
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    
    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }


    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteById(@PathVariable String recipeId,
            @PathVariable String id){

        log.debug("Deleting ingredient id: " + id);

        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));
        return "redirect:/recipe/" + recipeId + "/ingredients" ;
    }

    
}
