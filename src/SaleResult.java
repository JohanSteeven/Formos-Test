import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Immutable result object for one sale attempt.
 *
 * <p>Encapsulates success/failure state, user-facing message, and
 * shortage details when the sale is rejected.</p>
 */
public class SaleResult {
    private final boolean success;
    private final String message;
    private final List<IngredientShortage> shortages;

    /**
     * Internal constructor used by named factory methods.
     */
    private SaleResult(boolean success, String message, List<IngredientShortage> shortages) {
        this.success = success;
        this.message = message;
        this.shortages = Collections.unmodifiableList(new ArrayList<>(shortages));
    }

    /**
     * Creates a successful sale result.
     *
     * @param message success message for console output
     * @return success result
     */
    public static SaleResult success(String message) {
        return new SaleResult(true, message, Collections.<IngredientShortage>emptyList());
    }

    /**
     * Creates a failed sale result with shortage details.
     *
     * @param message failure message for console output
     * @param shortages ingredients that are insufficient
     * @return failure result
     */
    public static SaleResult failure(String message, List<IngredientShortage> shortages) {
        return new SaleResult(false, message, shortages);
    }

    /**
     * @return true when the sale was completed
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @return user-facing summary of the sale result
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return immutable list of shortages; empty for successful sales
     */
    public List<IngredientShortage> getShortages() {
        return shortages;
    }
}