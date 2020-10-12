package rs.apps.np.spring.btg.recipe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView numberFormatException(Exception e) {
		log.error("Handling number format exception");
		log.error(e.getMessage());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("400error");
		mav.addObject("exception", e);
		return mav;
	}

}
