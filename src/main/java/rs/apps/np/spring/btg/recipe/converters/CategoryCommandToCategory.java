package rs.apps.np.spring.btg.recipe.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import rs.apps.np.spring.btg.recipe.commands.CategoryCommand;
import rs.apps.np.spring.btg.recipe.domain.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand source) {
		if (source == null) {
			return null;
		}

		final Category c = new Category();
		BeanUtils.copyProperties(source, c);
				
		// c = new Category();
		// c.setId(source.getId());
		// c.setDescription(source.getDescription());
		return c;
	}

}
