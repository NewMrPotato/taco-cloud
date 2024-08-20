package tacos.Web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import tacos.TacoCook.Ingredient;
import tacos.TacoCook.Ingredient.Type;
import tacos.TacoCook.Taco;
import tacos.TacoCook.TacoOrder;

import javax.validation.Valid;

// Контроллер для страницы разработки тако.
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    // Добавление всех ингредиентов тако в модель и фильтрация их по их типу.
    // Модель это прикол из Spring MVC.
    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        // Проходимся по всем типам ингредиентов.
        Type[] types = Type.values();
        for (Type type : types) {
            // Добавление их  в модель.
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    // Создание запроса тако для модели.
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    // Создание тако для модели.
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    // Создание метода с HTTP запросом GET к design.html.
    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    // Создание метода с HTTP запросом POST.
    // То есть обработка полученной формы, после заполнения пользователем полей тако в design.html.
    @PostMapping   // Valid требует выполнить проверку Taco по аннотациям с проверками в самом классе.
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){
        // Проверка на наличие ошибок при создания пользователем тако.
        // Аннотации с проверками в классе tacos.TacoCook.Taco.
        if(errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    // Фильтрация по типам ингредиентов.
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
