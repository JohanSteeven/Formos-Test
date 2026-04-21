import java.util.Scanner;

/**
 * Entry point for the street vendor drink inventory console application.
 *
 * <p>This class owns the text menu loop and delegates business behavior to
 * service and domain classes.</p>
 */
public class Main {

    /**
     * Starts the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Inventory inventory = Inventory.createWithDefaultStock();
        RecipeService recipeService = new RecipeService();
        SalesService salesService = new SalesService(inventory, recipeService);

        // Initial stock values are assumptions for this coding test and are centralized in Inventory.
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            System.out.println("Street Vendor Drink Sales");

            while (running) {
                printMenu();
                int option = ConsoleInput.readIntInRange(scanner, "Select an option (1-3): ", 1, 3);

                switch (option) {
                    case 1:
                        showInventory(inventory);
                        break;
                    case 2:
                        sellDrink(scanner, salesService, inventory);
                        break;
                    case 3:
                        running = false;
                        System.out.println("Exiting application. Goodbye.");
                        break;
                    default:
                        System.out.println("Unknown option.");
                }
            }
        }
    }

    /**
     * Prints the main menu options.
     */
    private static void printMenu() {
        System.out.println();
        System.out.println("1. Show inventory");
        System.out.println("2. Sell drink");
        System.out.println("3. Exit");
    }

    /**
     * Displays the current stock levels for all ingredients.
     *
     * @param inventory current inventory state
     */
    private static void showInventory(Inventory inventory) {
        System.out.println();
        System.out.println("Current Inventory:");
        System.out.print(inventory.formatStock());
    }

    /**
     * Handles the menu flow for selling one drink.
     *
     * <p>The method prompts for flavor selection, executes the sale, and prints
     * either a success summary (with updated inventory) or a detailed shortage message.</p>
     *
     * @param scanner scanner used for reading user input
     * @param salesService sales use case service
     * @param inventory current inventory state
     */
    private static void sellDrink(Scanner scanner, SalesService salesService, Inventory inventory) {
        System.out.println();
        System.out.println("Choose drink flavor:");

        DrinkFlavor[] flavors = DrinkFlavor.values();
        for (int i = 0; i < flavors.length; i++) {
            System.out.printf("%d. %s%n", i + 1, flavors[i].getDisplayName());
        }

        int flavorOption = ConsoleInput.readIntInRange(
                scanner,
                "Select flavor (1-" + flavors.length + "): ",
                1,
                flavors.length
        );
        DrinkFlavor flavor = DrinkFlavor.fromMenuOption(flavorOption);

        SaleResult result = salesService.sellDrink(flavor);
        System.out.println(result.getMessage());

        if (result.isSuccess()) {
            System.out.println("Updated inventory:");
            System.out.print(inventory.formatStock());
        }
    }
}
