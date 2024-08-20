package tacos.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// Контроллер для домашней страницы TacoCloud.
@Controller
public class HomeController {
	// Выполнение HTTP запроса GET.
	// Запрос по странице home.html.
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
