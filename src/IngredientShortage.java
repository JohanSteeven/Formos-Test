/**
 * Describes a stock shortage for one ingredient during sale validation.
 */
public class IngredientShortage {
    private final Ingredient ingredient;
    private final int requiredAmount;
    private final int availableAmount;

    /**
     * Creates a shortage description.
     *
     * @param ingredient ingredient with insufficient stock
     * @param requiredAmount amount needed to complete the sale
     * @param availableAmount amount currently available in stock
     */
    public IngredientShortage(Ingredient ingredient, int requiredAmount, int availableAmount) {
        this.ingredient = ingredient;
        this.requiredAmount = requiredAmount;
        this.availableAmount = availableAmount;
    }

    /**
     * @return ingredient with shortage
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * @return required amount to complete the sale
     */
    public int getRequiredAmount() {
        return requiredAmount;
    }

    /**
     * @return currently available amount in stock
     */
    public int getAvailableAmount() {
        return availableAmount;
    }

    /**
     * @return missing amount, calculated as required minus available
     */
    public int getMissingAmount() {
        return requiredAmount - availableAmount;
    }
}