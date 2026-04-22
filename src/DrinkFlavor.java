/**
 * Supported drink flavors.
 *
 * <p>Each flavor maps to its own blended-fruit ingredient and fresh-fruit ingredient.</p>
 */
public enum DrinkFlavor {
    STRAWBERRY("Strawberry", Ingredient.STRAWBERRY_BLEND, Ingredient.STRAWBERRIES, 150),
    BANANA("Banana", Ingredient.BANANA_BLEND, Ingredient.BANANAS, 180),
    MANGO("Mango", Ingredient.MANGO_BLEND, Ingredient.MANGOES, 210);

    private final String displayName;
    private final Ingredient blendedFruitIngredient;
    private final Ingredient fruitIngredient;
    private final int fruitGramsPerDrink;

    /**
     * Creates a drink flavor enum value.
     *
     * @param displayName name shown in menu and messages
     * @param blendedFruitIngredient flavor-specific blended fruit ingredient
     * @param fruitIngredient flavor-specific fruit ingredient
     * @param fruitGramsPerDrink grams of fruit consumed for one 300 ml drink
     */
    DrinkFlavor(
            String displayName,
            Ingredient blendedFruitIngredient,
            Ingredient fruitIngredient,
            int fruitGramsPerDrink
    ) {
        this.displayName = displayName;
        this.blendedFruitIngredient = blendedFruitIngredient;
        this.fruitIngredient = fruitIngredient;
        this.fruitGramsPerDrink = fruitGramsPerDrink;
    }

    /**
     * @return flavor name shown to users
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return inventory ingredient used as this flavor's blended fruit base
     */
    public Ingredient getBlendedFruitIngredient() {
        return blendedFruitIngredient;
    }

    /**
     * @return inventory ingredient used as this flavor's fruit
     */
    public Ingredient getFruitIngredient() {
        return fruitIngredient;
    }

    /**
     * @return grams of fruit required for one 300 ml drink
     */
    public int getFruitGramsPerDrink() {
        return fruitGramsPerDrink;
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