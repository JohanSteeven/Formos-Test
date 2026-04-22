import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory inventory for all ingredients used by the vendor.
 *
 * <p>This class centralizes stock initialization, stock querying,
 * shortage detection, and stock consumption.</p>
 */
public class Inventory {
    // Assumption: initial values are reasonable defaults for a small street vendor shift.
    // Each flavor has independent blended-fruit stock.
    private static final int INITIAL_STRAWBERRY_BLEND_ML = 1_800;
    private static final int INITIAL_BANANA_BLEND_ML = 1_700;
    private static final int INITIAL_MANGO_BLEND_ML = 1_500;
    private static final int INITIAL_ICE_ML = 3_000;
    private static final int INITIAL_CONDENSED_MILK_ML = 2_000;
    private static final int INITIAL_SUGAR_G = 1_500;
    private static final int INITIAL_STRAWBERRIES_G = 2_000;
    private static final int INITIAL_BANANAS_G = 2_200;
    private static final int INITIAL_MANGOES_G = 2_500;

    private final EnumMap<Ingredient, Integer> stockLevels;

    /**
     * Creates an empty inventory instance.
     *
     * <p>Use {@link #createWithDefaultStock()} to build a ready-to-use inventory.</p>
     */
    private Inventory() {
        this.stockLevels = new EnumMap<>(Ingredient.class);
    }

    /**
     * Creates inventory with hardcoded default stock values.
     *
     * @return inventory preloaded with initial quantities
     */
    public static Inventory createWithDefaultStock() {
        Inventory inventory = new Inventory();
        inventory.stockLevels.put(Ingredient.STRAWBERRY_BLEND, INITIAL_STRAWBERRY_BLEND_ML);
        inventory.stockLevels.put(Ingredient.BANANA_BLEND, INITIAL_BANANA_BLEND_ML);
        inventory.stockLevels.put(Ingredient.MANGO_BLEND, INITIAL_MANGO_BLEND_ML);
        inventory.stockLevels.put(Ingredient.ICE, INITIAL_ICE_ML);
        inventory.stockLevels.put(Ingredient.CONDENSED_MILK, INITIAL_CONDENSED_MILK_ML);
        inventory.stockLevels.put(Ingredient.SUGAR, INITIAL_SUGAR_G);
        inventory.stockLevels.put(Ingredient.STRAWBERRIES, INITIAL_STRAWBERRIES_G);
        inventory.stockLevels.put(Ingredient.BANANAS, INITIAL_BANANAS_G);
        inventory.stockLevels.put(Ingredient.MANGOES, INITIAL_MANGOES_G);
        return inventory;
    }

    /**
     * Returns available stock for one ingredient.
     *
     * @param ingredient ingredient to query
     * @return available amount, or 0 if not present
     */
    public int getAmount(Ingredient ingredient) {
        Integer amount = stockLevels.get(ingredient);
        return amount == null ? 0 : amount;
    }

    /**
     * Returns a read-only snapshot of the current stock.
     *
     * @return immutable map copy of current stock levels
     */
    public Map<Ingredient, Integer> snapshot() {
        return Collections.unmodifiableMap(new EnumMap<>(stockLevels));
    }

    /**
     * Validates required ingredients against current stock and returns all shortages.
     *
     * @param requiredIngredients ingredient amounts required for a sale
     * @return list of shortages; empty when stock is sufficient
     */
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

    /**
     * Deducts ingredient amounts from current stock.
     *
     * <p>Callers are expected to validate stock sufficiency before consuming.</p>
     *
     * @param usedIngredients ingredient amounts to subtract
     */
    public void consume(Map<Ingredient, Integer> usedIngredients) {
        for (Map.Entry<Ingredient, Integer> entry : usedIngredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int updatedAmount = getAmount(ingredient) - entry.getValue();
            stockLevels.put(ingredient, updatedAmount);
        }
    }

    /**
     * Formats inventory as user-friendly multi-line text.
     *
     * @return formatted stock text for console output
     */
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