package rs.apps.np.spring.btg.recipe.commands;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.util.BeanUtil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.apps.np.spring.btg.recipe.domain.Difficulty;
import rs.apps.np.spring.btg.recipe.domain.Notes;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String direction;
	private Set<IngredientCommand> ingredients = new HashSet<IngredientCommand>();
	private Difficulty difficulty;
	private NotesCommand notes;
	private Set<CategoryCommand> categories = new HashSet<CategoryCommand>();

}
