package rs.apps.np.spring.btg.recipe.domain;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Data
@ToString(exclude = "recipe")
@EqualsAndHashCode(exclude = "recipe")
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne // ne treba nam cascade
	private Recipe recipe;

	@Lob
	private String recipeNotes;

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	//	public String getRecipeNotes() {
	//		return recipeNotes;
	//	}
	//
	//	public void setRecipeNotes(String recipeNotes) {
	//		this.recipeNotes = recipeNotes;
	//	}
	//
	//	public Long getId() {
	//		return id;
	//	}
	//
	//	public void setId(Long id) {
	//		this.id = id;
	//	}
	//
	//	@Override
	//	public String toString() {
	//		StringBuilder builder = new StringBuilder();
	//		builder.append("Notes [recipe=").append(recipe).append(", recipeNotes=").append(recipeNotes).append("]");
	//		return builder.toString();
	//	}

}
