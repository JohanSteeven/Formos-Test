import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    // Assumption: initial values are reasonable defaults for a small street vendor shift.
    private static final int INITIAL_BLENDED_FRUIT_BASE_ML = 5_000;
    private static final int INITIAL_ICE_ML = 3_000;
    private static final int INITIAL_CONDENSED_MILK_ML = 2_000;
    private static final int INITIAL_SUGAR_G = 1_500;
    private static final int INITIAL_STRAWBERRIES_G = 2_000;
    private static final int INITIAL_BANANAS_G = 2_200;
    private static final int INITIAL_MANGOES_G = 2_500;

    private final EnumMap<Ingredient, Integer> stockLevels;

    private Inventory() {
        this.stockLevels = new EnumMap<>(Ingredient.class);
    }

    public static Inventory createWithDefaultStock() {
        Inventory inventory = new Inventory();
        inventory.stockLevels.put(Ingredient.BLENDED_FRUIT_BASE, INITIAL_BLENDED_FRUIT_BASE_ML);
        inventory.stockLevels.put(Ingredient.ICE, INITIAL_ICE_ML);
        inventory.stockLevels.put(Ingredient.CONDENSED_MILK, INITIAL_CONDENSED_MILK_ML);
        inventory.stockLevels.put(Ingredient.SUGAR, INITIAL_SUGAR_G);
        inventory.stockLevels.put(Ingredient.STRAWBERRIES, INITIAL_STRAWBERRIES_G);
        inventory.stockLevels.put(Ingredient.BANANAS, INITIAL_BANANAS_G);
        inventory.stockLevels.put(Ingredient.MANGOES, INITIAL_MANGOES_G);
        return inventory;
    }

    public int getAmount(Ingredient ingredient) {
        Integer amount = stockLevels.get(ingredient);
        return amount == null ? 0 : amount;
    }

    public Map<Ingredient, Integer> snapshot() {
        return Collections.unmodifiableMap(new EnumMap<>(stockLevels));
    }

    public List<IngredientShortage> findShortages(Map<Ingredient, Integer> requiredIngredients) {
        List<IngredientShortage> shortages = new ArrayList<>();

        for (Map.Entry<Ingredient, Integer> entry : requiredIngredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int required = entry.getValue();
            int available = getAmount(ingredient);

            if (available < required) {
                shortages.add(new IngredientShortage(ingredient, required, available));
            }
        }

        return shortages;
    }

    public void consume(Map<Ingredient, Integer> usedIngredients) {
        for (Map.Entry<Ingredient, Integer> entry : usedIngredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int updatedAmount = getAmount(ingredient) - entry.getValue();
            stockLevels.put(ingredient, updatedAmount);
        }
    }

    public String formatStock() {
        StringBuilder builder = new StringBuilder();
        for (Ingredient ingredient : Ingredient.values()) {
            builder.append(String.format(
                    "- %-20s : %d %s%n",
                    ingredient.getDisplayName(),
                    getAmount(ingredient),
                    ingredient.getUnit()
            ));
        }
        return builder.toString();
    }
}