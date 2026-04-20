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

    Ingredient(String displayName, String unit) {
        this.displayName = displayName;
        this.unit = unit;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUnit() {
        return unit;
    }
}