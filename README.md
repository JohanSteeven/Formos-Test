# Street Vendor Drink Inventory Console App

## Overview
This project is a Java console application for a street vendor who sells 300 ml blended fruit drinks and tracks ingredient inventory in memory.

The implementation focuses on clean object-oriented structure, readable business logic, and interview-friendly code.

## Assumptions
- The test did not define starting stock quantities, so reasonable hardcoded defaults are used.
- Initial inventory constants are centralized in `Inventory` and easy to modify.
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

## Build And Run (Command Line)
From the project root:

1. Compile:
   `javac src\*.java`
2. Run:
   `java -cp src Main`

## Sample Console Session
Street Vendor Drink Sales Console

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
- Blended fruit base   : 4850 ml
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