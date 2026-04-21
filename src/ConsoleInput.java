import java.util.Scanner;

/**
 * Utility helpers for robust console input reading.
 */
public final class ConsoleInput {
    /**
     * Prevents instantiation of utility class.
     */
    private ConsoleInput() {
    }

    /**
     * Reads an integer from input and validates it falls within a closed range.
     *
     * <p>The method keeps prompting until valid input is provided.</p>
     *
     * @param scanner scanner used to read from standard input
     * @param prompt text shown before each input attempt
     * @param minInclusive minimum accepted value
     * @param maxInclusive maximum accepted value
     * @return a valid integer in the requested range
     */
    public static int readIntInRange(Scanner scanner, String prompt, int minInclusive, int maxInclusive) {
        while (true) {
            System.out.print(prompt);
            String raw = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(raw);
                if (value >= minInclusive && value <= maxInclusive) {
                    return value;
                }
            } catch (NumberFormatException ex) {
                // Continue loop and show validation message below.
            }

            System.out.printf(
                    "Invalid input. Please enter a number between %d and %d.%n",
                    minInclusive,
                    maxInclusive
            );
        }
    }
}