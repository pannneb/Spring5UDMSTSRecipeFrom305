package rs.apps.np.spring.btg.recipe.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Nebojsa
 *
 */
public interface ImageService {

	void saveImageFile(Long recipeId, MultipartFile file);

}
