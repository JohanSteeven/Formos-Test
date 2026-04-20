import java.util.EnumMap;
import java.util.Map;

public class RecipeService {
    public static final int DRINK_SIZE_ML = 300;

    // Exact inventory consumption for one 300 ml drink, based on the provided business rules.
    private static final int BLENDED_FRUIT_BASE_PER_DRINK_ML = 150;
    private static final int ICE_PER_DRINK_ML = 90;
    private static final int CONDENSED_MILK_PER_DRINK_ML = 60;
    private static final int SUGAR_PER_DRINK_G = 24;

    private static final int STRAWBERRY_PER_DRINK_G = 150;
    private static final int BANANA_PER_DRINK_G = 180;
    private static final int MANGO_PER_DRINK_G = 210;

    public Map<Ingredient, Integer> getRequirementsForDrink(DrinkFlavor flavor) {
        EnumMap<Ingredient, Integer> requirements = new EnumMap<>(Ingredient.class);
        requirements.put(Ingredient.BLENDED_FRUIT_BASE, BLENDED_FRUIT_BASE_PER_DRINK_ML);
        requirements.put(Ingredient.ICE, ICE_PER_DRINK_ML);
        requirements.put(Ingredient.CONDENSED_MILK, CONDENSED_MILK_PER_DRINK_ML);
        requirements.put(Ingredient.SUGAR, SUGAR_PER_DRINK_G);

        switch (flavor) {
            case STRAWBERRY:
                requirements.put(Ingredient.STRAWBERRIES, STRAWBERRY_PER_DRINK_G);
                break;
            case BANANA:
                requirements.put(Ingredient.BANANAS, BANANA_PER_DRINK_G);
                break;
            case MANGO:
                requirements.put(Ingredient.MANGOES, MANGO_PER_DRINK_G);
                break;
            default:
                throw new IllegalArgumentException("Unsupported flavor: " + flavor);
        }

        return requirements;
    }
}