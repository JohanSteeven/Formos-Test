import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Inventory inventory = Inventory.createWithDefaultStock();
        RecipeService recipeService = new RecipeService();
        SalesService salesService = new SalesService(inventory, recipeService);

        // Initial stock values are assumptions for this coding test and are centralized in Inventory.
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            System.out.println("Street Vendor Drink Sales Console");

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

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Show inventory");
        System.out.println("2. Sell drink");
        System.out.println("3. Exit");
    }

    private static void showInventory(Inventory inventory) {
        System.out.println();
        System.out.println("Current Inventory:");
        System.out.print(inventory.formatStock());
    }

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
