/**
 * Supported drink flavors.
 *
 * <p>Each flavor maps to its fruit ingredient in inventory.</p>
 */
public enum DrinkFlavor {
    STRAWBERRY("Strawberry", Ingredient.STRAWBERRIES),
    BANANA("Banana", Ingredient.BANANAS),
    MANGO("Mango", Ingredient.MANGOES);

    private final String displayName;
    private final Ingredient fruitIngredient;

    /**
     * Creates a drink flavor enum value.
     *
     * @param displayName name shown in menu and messages
     * @param fruitIngredient flavor-specific fruit ingredient
     */
    DrinkFlavor(String displayName, Ingredient fruitIngredient) {
        this.displayName = displayName;
        this.fruitIngredient = fruitIngredient;
    }

    /**
     * @return flavor name shown to users
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return inventory ingredient used as this flavor's fruit
     */
    public Ingredient getFruitIngredient() {
        return fruitIngredient;
    }

    /**
     * Converts a 1-based menu option to a flavor.
     *
     * @param option selected menu option
     * @return flavor corresponding to the option
     * @throws IllegalArgumentException when option is outside valid range
     */
    public static DrinkFlavor fromMenuOption(int option) {
        DrinkFlavor[] values = DrinkFlavor.values();
        if (option < 1 || option > values.length) {
            throw new IllegalArgumentException("Invalid flavor option: " + option);
        }
        return values[option - 1];
    }
}