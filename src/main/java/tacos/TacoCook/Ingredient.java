package tacos.TacoCook;

import lombok.Data;

// Простой класс ингредиента для тако.
@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
