package rs.apps.np.spring.btg.recipe.services;

import java.util.Set;

import rs.apps.np.spring.btg.recipe.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	Set<UnitOfMeasureCommand> listAllUoms();
}
