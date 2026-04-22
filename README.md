# Street Vendor Drink Inventory Console App

## Overview
This project is a Java console application for a street vendor who sells 300 ml blended fruit drinks and tracks ingredient inventory in memory.

The implementation focuses on clean object-oriented structure, readable business logic, and interview-friendly code.

## Assumptions
- The test did not define starting stock quantities, so reasonable hardcoded defaults are used.
- Initial inventory constants are centralized in `Inventory` and easy to modify.
- Blended fruit inventory is flavor-specific (`Strawberry blend`, `Banana blend`, `Mango blend`) and not shared.
- Inventory is in-memory only. No database and no persistence after exit.
- This version sells one drink at a time and only one size: 300 ml.

## Architecture
- `Main`: app startup, menu loop, user interaction flow.
- `DrinkFlavor`: enum for available flavors.
- `Ingredient`: enum for inventory ingredients and units.
- `Inventory`: current stock, stock formatting, shortage checks, and consumption.
- `RecipeService`: centralized per-drink ingredient requirements.
- `SalesService`: sale orchestration, stock validation, and stock reduction.
- `SaleResult`: result object for sale success/failure and messaging.
- `IngredientShortage`: structured shortage details for failed sales.
- `ConsoleInput`: reusable numeric input validation helper.

## Class-By-Class Details

### Main
- Responsibility: orchestration of the console user experience.
- Creates `Inventory`, `RecipeService`, and `SalesService` on startup.
- Runs the menu loop and delegates business actions to services.
- Keeps I/O concerns separate from recipe and stock logic.

### ConsoleInput
- Responsibility: robust numeric input validation.
- Exposes `readIntInRange(...)` so menu and flavor selection share one validation rule.
- Prevents duplicated parsing code and keeps `Main` focused on flow.

### DrinkFlavor
- Responsibility: flavor catalog and flavor-specific metadata.
- Each flavor defines:
   - display name,
   - specific blended fruit ingredient (`Strawberry blend`, `Banana blend`, `Mango blend`),
   - specific fresh fruit ingredient,
   - fresh fruit grams consumed per 300 ml drink.
- Centralizes flavor configuration and avoids magic values in services.

### Ingredient
- Responsibility: ingredient catalog and measurement units.
- Defines all stock-tracked ingredients, including flavor-specific blended fruit and fresh fruit.
- Ensures all stock display and shortage messages use a single source of truth for names and units.

### RecipeService
- Responsibility: recipe calculation for one drink.
- Applies shared ingredient rules for any flavor:
   - 150 ml blended fruit (flavor-specific),
   - 90 ml ice,
   - 60 ml condensed milk,
   - 24 g sugar.
- Adds flavor-specific fresh fruit grams through `DrinkFlavor` metadata.
- Keeps all quantity logic in one place to simplify future extensions (sizes, mixed flavors, pricing).

### Inventory
- Responsibility: in-memory stock state and stock operations.
- Holds hardcoded initial stock constants and current stock levels.
- Provides:
   - stock reads (`getAmount`),
   - immutable snapshots (`snapshot`),
   - shortage detection (`findShortages`),
   - stock deduction (`consume`),
   - user-facing stock formatting (`formatStock`).
- Does not know about console menus or business workflows.

### SalesService
- Responsibility: sale use case execution.
- Workflow:
   1. asks `RecipeService` for required ingredients,
   2. asks `Inventory` to validate shortages,
   3. rejects sale if any ingredient is insufficient,
   4. consumes stock and returns success otherwise.
- Produces user-facing results through `SaleResult`.

### SaleResult
- Responsibility: immutable result of one sale attempt.
- Carries:
   - success flag,
   - message for console output,
   - optional list of shortages for failure scenarios.
- Uses static factories (`success`, `failure`) for clear intent.

### IngredientShortage
- Responsibility: structured shortage detail for one ingredient.
- Keeps required amount, available amount, and computed missing amount.
- Used by `SalesService` to build detailed rejection messages.

## Business Rules Implemented

For each 300 ml drink:
- 150 ml blended fruit (specific to selected flavor)
- 90 ml ice
- 60 ml condensed milk
- 24 g sugar

Flavor-specific fresh fruit consumption per 300 ml drink:
- Strawberry: 150 g strawberries
- Banana: 180 g bananas
- Mango: 210 g mangoes

## Sale Sequence (Step By Step)
1. User selects `Sell drink` in the menu.
2. User selects one flavor.
3. `Main` calls `SalesService.sellDrink(flavor)`.
4. `SalesService` obtains recipe requirements from `RecipeService`.
5. `Inventory.findShortages(...)` validates all required ingredients.
6. If shortages exist, sale is rejected and each shortage is reported.
7. If no shortages exist, `Inventory.consume(...)` deducts stock.
8. Success result is returned and updated stock can be displayed.

## Extensibility Notes
- Add low-stock alerts by introducing a stock-threshold service without changing menu or recipe contracts.
- Add multiple sizes by extending `RecipeService` to accept size and derive quantities.
- Add mixed-fruit drinks by extending `DrinkFlavor` or introducing a `DrinkRecipe` model.
- Add pricing/cost by introducing a pricing service that consumes recipe output.
- Add sales tracking by introducing an in-memory or persistent sale log service.

## Build And Run (Command Line)
From the project root:

1. Compile:
   `javac src\*.java`
2. Run:
   `java -cp src Main`

## Sample Console Session
Street Vendor Drink Sales

1. Show inventory
2. Sell drink
3. Exit
Select an option (1-3): 2

Choose drink flavor:
1. Strawberry
2. Banana
3. Mango
Select flavor (1-3): 1
Sale completed: 1 Strawberry drink (300 ml).
Updated inventory:
- Strawberry blend     : 1650 ml
- Banana blend         : 1700 ml
- Mango blend          : 1500 ml
- Ice                  : 2910 ml
- Condensed milk       : 1940 ml
- Sugar                : 1476 g
- Strawberries         : 1850 g
- Bananas              : 2200 g
- Mangoes              : 2500 g

1. Show inventory
2. Sell drink
3. Exit
Select an option (1-3): 3
Exiting application. Goodbye.

## Proposed Delivery Folder Structure
Formos/
- README.md
- Formos.iml
- src/
  - Main.java
  - ConsoleInput.java
  - DrinkFlavor.java
  - Ingredient.java
  - IngredientShortage.java
  - Inventory.java
  - RecipeService.java
  - SaleResult.java
  - SalesService.java