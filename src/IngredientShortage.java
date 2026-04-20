public class IngredientShortage {
    private final Ingredient ingredient;
    private final int requiredAmount;
    private final int availableAmount;

    public IngredientShortage(Ingredient ingredient, int requiredAmount, int availableAmount) {
        this.ingredient = ingredient;
        this.requiredAmount = requiredAmount;
        this.availableAmount = availableAmount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public int getMissingAmount() {
        return requiredAmount - availableAmount;
    }
}