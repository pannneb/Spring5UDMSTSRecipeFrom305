package rs.apps.np.spring.btg.recipe.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	Category category;

	@Before
	public void setUp() {
		category = new Category();
	}
	
	@Test
	public void testSetId() {
		// fail("Not yet implemented");
		Long idValue = 4l;
		category.setId(idValue);
		assertEquals(idValue, category.getId());
	}

	@Test
	public void testSetDescription() {
		String destValue = "testDesc";
		category.setDescription(destValue);
		assertEquals(destValue, category.getDescription());
		// fail("Not yet implemented");
	}

	@Test
	public void testSetRecipes() {
		// fail("Not yet implemented");
	}

}
