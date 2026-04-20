public enum DrinkFlavor {
    STRAWBERRY("Strawberry", Ingredient.STRAWBERRIES),
    BANANA("Banana", Ingredient.BANANAS),
    MANGO("Mango", Ingredient.MANGOES);

    private final String displayName;
    private final Ingredient fruitIngredient;

    DrinkFlavor(String displayName, Ingredient fruitIngredient) {
        this.displayName = displayName;
        this.fruitIngredient = fruitIngredient;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Ingredient getFruitIngredient() {
        return fruitIngredient;
    }

    public static DrinkFlavor fromMenuOption(int option) {
        DrinkFlavor[] values = DrinkFlavor.values();
        if (option < 1 || option > values.length) {
            throw new IllegalArgumentException("Invalid flavor option: " + option);
        }
        return values[option - 1];
    }
}