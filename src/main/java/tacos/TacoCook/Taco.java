package tacos.TacoCook;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

// Простой класс тако.
@Data
public class Taco {
    // Проверка поля на пустоту и на то что не меньше ли 5 букв имя тако.
    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long.")
    private String name;
    // Проверка поля на пустоту и на то что не меньше ли 1 значения в ингредиентах тако.
    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient.")
    private List<Ingredient> ingredients;
}
