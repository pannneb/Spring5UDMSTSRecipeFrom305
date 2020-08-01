package rs.apps.np.spring.btg.recipe.repositories;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import rs.apps.np.spring.btg.recipe.domain.UnitOfMeasure;

@RunWith(SpringRunner.class) // 
@DataJpaTest // ovo nam omogucava embedded DB
public class UnitOfMeasureRepositoryTestIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	@DirtiesContext // cisti Spring context (ukoliko smo nesto ubacivali u bazu, a ne treba da nam kvari ostale testove)
	// za nas test nama i ne treba @DirtiesContext
	public void testFindByDescription() {
		//fail("Not yet implemented");
		Optional<UnitOfMeasure> uomOpt = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		assertEquals("Teaspoon", uomOpt.isPresent()?uomOpt.get().getDescription():"not found");
	}

	@Test
	public void testFindByDescriptionCup() {
		//fail("Not yet implemented");
		Optional<UnitOfMeasure> uomOpt = unitOfMeasureRepository.findByDescription("Cup");
		
		assertEquals("Cup", uomOpt.isPresent()?uomOpt.get().getDescription():"not found");
	}

}
