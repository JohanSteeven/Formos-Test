import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SaleResult {
    private final boolean success;
    private final String message;
    private final List<IngredientShortage> shortages;

    private SaleResult(boolean success, String message, List<IngredientShortage> shortages) {
        this.success = success;
        this.message = message;
        this.shortages = Collections.unmodifiableList(new ArrayList<>(shortages));
    }

    public static SaleResult success(String message) {
        return new SaleResult(true, message, Collections.<IngredientShortage>emptyList());
    }

    public static SaleResult failure(String message, List<IngredientShortage> shortages) {
        return new SaleResult(false, message, shortages);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<IngredientShortage> getShortages() {
        return shortages;
    }
}