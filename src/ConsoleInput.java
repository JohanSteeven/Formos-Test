import java.util.Scanner;

public final class ConsoleInput {
    private ConsoleInput() {
    }

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