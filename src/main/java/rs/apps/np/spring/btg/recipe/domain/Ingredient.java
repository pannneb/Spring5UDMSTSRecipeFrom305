package rs.apps.np.spring.btg.recipe.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Getter
@Setter
@ToString(exclude = "recipe")
@EqualsAndHashCode(exclude = "recipe")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	private BigDecimal amount;

	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure uom;

	@ManyToOne
	private Recipe recipe;

	public Ingredient() {
		super();
	}

	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
		super();
		this.description = description;
		this.amount = amount;
		this.uom = uom;
	}

	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
		super();
		this.description = description;
		this.amount = amount;
		this.uom = uom;
		this.recipe = recipe;
	}

	// public String getDescription() {
	// return description;
	// }
	//
	// public void setDescription(String description) {
	// this.description = description;
	// }
	//
	// public BigDecimal getAmount() {
	// return amount;
	// }
	//
	// public void setAmount(BigDecimal amount) {
	// this.amount = amount;
	// }
	//
	// public Recipe getRecipe() {
	// return recipe;
	// }
	//
	// public void setRecipe(Recipe recipe) {
	// this.recipe = recipe;
	// }
	//
	// public Long getId() {
	// return id;
	// }
	//
	// public void setId(Long id) {
	// this.id = id;
	// }
	//
	// @Override
	// public String toString() {
	// StringBuilder builder = new StringBuilder();
	// builder.append("Ingredient [description=").append(description).append(",
	// amount=").append(amount)
	// .append(", recipe=").append(recipe).append(", id=").append(id).append(",
	// uom=").append(uom).append("]");
	// return builder.toString();
	// }
	//
	// public UnitOfMeasure getUom() {
	// return uom;
	// }
	//
	// public void setUom(UnitOfMeasure uom) {
	// this.uom = uom;
	// }

}
