package mock.project.frontend.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		// get error status
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		// TODO: log error details here

		if (status != null) {
			int statusCode = Integer.parseInt(status.toString());

			// display specific error page
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				model.addAttribute("status", "404");
				model.addAttribute("error", "sorry we couldn't find your link!");
				return "error";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				model.addAttribute("status", "500");
				model.addAttribute("error", "sorry we've got internal server error!");
				return "error";
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				model.addAttribute("status", "403");
				model.addAttribute("error", "forbidden error!");
				return "error";
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				model.addAttribute("status", "405");
				model.addAttribute("error", "you are not allowed to do this!");
				return "error";
			}
		}

		// display generic error
		return "error";
	}

}
