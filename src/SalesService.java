import java.util.List;
import java.util.Map;

public class SalesService {
    private final Inventory inventory;
    private final RecipeService recipeService;

    public SalesService(Inventory inventory, RecipeService recipeService) {
        this.inventory = inventory;
        this.recipeService = recipeService;
    }

    public SaleResult sellDrink(DrinkFlavor flavor) {
        Map<Ingredient, Integer> requiredIngredients = recipeService.getRequirementsForDrink(flavor);
        List<IngredientShortage> shortages = inventory.findShortages(requiredIngredients);

        if (!shortages.isEmpty()) {
            return SaleResult.failure(buildFailureMessage(flavor, shortages), shortages);
        }

        inventory.consume(requiredIngredients);
        String successMessage = String.format(
                "Sale completed: 1 %s drink (%d ml).",
                flavor.getDisplayName(),
                RecipeService.DRINK_SIZE_ML
        );
        return SaleResult.success(successMessage);
    }

    private String buildFailureMessage(DrinkFlavor flavor, List<IngredientShortage> shortages) {
        StringBuilder message = new StringBuilder();
        message.append("Sale rejected: insufficient ingredients for ")
                .append(flavor.getDisplayName())
                .append(" drink.")
                .append(System.lineSeparator());

        for (IngredientShortage shortage : shortages) {
            Ingredient ingredient = shortage.getIngredient();
            message.append(String.format(
                    "- %s: required %d %s, available %d %s, missing %d %s%n",
                    ingredient.getDisplayName(),
                    shortage.getRequiredAmount(),
                    ingredient.getUnit(),
                    shortage.getAvailableAmount(),
                    ingredient.getUnit(),
                    shortage.getMissingAmount(),
                    ingredient.getUnit()
            ));
        }

        return message.toString().trim();
    }
}