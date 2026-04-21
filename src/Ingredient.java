/**
 * Inventory ingredients used by the vendor.
 *
 * <p>Each ingredient defines a human-readable name and its measurement unit.</p>
 */
public enum Ingredient {
    BLENDED_FRUIT_BASE("Blended fruit base", "ml"),
    ICE("Ice", "ml"),
    CONDENSED_MILK("Condensed milk", "ml"),
    SUGAR("Sugar", "g"),
    STRAWBERRIES("Strawberries", "g"),
    BANANAS("Bananas", "g"),
    MANGOES("Mangoes", "g");

    private final String displayName;
    private final String unit;

    /**
     * Creates an ingredient enum value.
     *
     * @param displayName label shown in console output
     * @param unit measurement unit used by this ingredient
     */
    Ingredient(String displayName, String unit) {
        this.displayName = displayName;
        this.unit = unit;
    }

    /**
     * @return ingredient name shown to the user
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return unit used to measure this ingredient
     */
    public String getUnit() {
        return unit;
    }
}