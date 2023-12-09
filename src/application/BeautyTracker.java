package application;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import io.github.cdimascio.dotenv.Dotenv;
public class BeautyTracker {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/beautytracker";
    private static final String USER = "root";

    private static final String PASSWORD = Dotenv.configure().load().get("DB_PASSWORD");
    private static final Scanner scanner = new Scanner(System.in);


    // main function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current Product Collection:");
        displayProductCollection();

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add New Product");
            System.out.println("2. View and Choose Products");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addNewProduct(scanner);
                case 2 -> viewAndChooseProducts(scanner);
                case 3 -> {
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // When user enter, first show the user the whole collection we have
    private static void displayProductCollection() {
        String procedureCall = "{CALL DisplayProductCollection()}";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             CallableStatement callableStatement = connection.prepareCall(procedureCall);
             ResultSet resultSet = callableStatement.executeQuery()) {

            int productCount = 0;
            while (resultSet.next()) {
                productCount++;
                String productName = resultSet.getString("product_name");
                String price = resultSet.getString("price");
                String size = resultSet.getString("size");
                String url = resultSet.getString("url");
                Date expirationDate = resultSet.getDate("expiration_date");
                String concernName = resultSet.getString("concern_name");
                String typeName = resultSet.getString("type_name");
                String brandName = resultSet.getString("brand_name");

                System.out.println("Product #" + productCount + ":");
                System.out.println("  Name: " + productName);
                System.out.println("  Price: " + price);
                System.out.println("  Size: " + size);
                System.out.println("  URL: " + url);
                System.out.println("  Expiration Date: " + (expirationDate != null ? expirationDate.toString() : "N/A"));
                System.out.println("  Concern: " + concernName);
                System.out.println("  Type: " + typeName);
                System.out.println("  Brand: " + brandName);
                System.out.println();
            }

            if (productCount == 0) {
                System.out.println("The current product collection is empty.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to display the product collection.");
            e.printStackTrace();
        }
    }


    // add new products to the collection
    private static void addNewProduct(Scanner scanner) {
        System.out.println("Enter product details...");

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        if (productName.length() > 255) {
            System.out.println("Product name is too long and will be truncated.");
            productName = productName.substring(0, 255);
        }

        String currencySymbol = getCurrencySymbol(scanner);
        System.out.print("Enter the price (" + currencySymbol + "): ");
        double price = scanner.nextDouble();
        while (price < 0) {
            System.out.println("Please enter valid price.");
            price = scanner.nextDouble();
        }
        scanner.nextLine(); // Consume leftover newline

        String sizeUnit = getSizeUnit(scanner);
        System.out.print("Enter size in " + sizeUnit + ": ");
        double size = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter URL (leave blank if not applicable): ");
        String url = scanner.nextLine();

        System.out.print("Enter expiration date (YYYY-MM-DD, leave blank if not applicable): ");
        String expirationDateInput = scanner.nextLine();
        java.sql.Date expirationDate = null;
        if (!expirationDateInput.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = dateFormat.parse(expirationDateInput);
                expirationDate = new java.sql.Date(parsedDate.getTime());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
                return;
            }
        }

        System.out.print("Enter concern name: ");
        String concernName = scanner.nextLine();
        addConcernDetails(scanner, concernName);

        System.out.print("Enter type (skincare, makeup, haircare, fragrances, bath&body, tools&brushes): ");
        List<String> types = List.of(new String[]{"skincare", "makeup", "haircare", "fragrances", "bath&body", "tools&brushes"});
        String typeName = scanner.nextLine();
        while (!types.contains(typeName.toLowerCase())) {
            System.out.print("Please enter valid type.");
            typeName = scanner.nextLine();
        }

        System.out.print("Enter brand name: ");
        String brandName = scanner.nextLine();
        addBrandDetails(scanner, brandName);


        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             CallableStatement statement = connection.prepareCall("{CALL addNewProduct(?, ?, ?, ?, ?, ?, ?, ?)}")) {

            statement.setString(1, productName);
            statement.setString(2, currencySymbol + " " + price);
            statement.setString(3, size + sizeUnit);
            statement.setString(4, url);
            statement.setDate(5, expirationDate);
            statement.setString(6, concernName);
            statement.setString(7, typeName);
            statement.setString(8, brandName);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product added successfully!");
            } else {
                System.out.println("No product was added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Ask if the user wants to add ingredient details
        System.out.print("Do you want to add ingredient details? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            addIngredientDetails(scanner, productName);
        }

        // Ask if the user wants to add package details
        System.out.print("Do you want to add package details? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            addPackageDetails(scanner, productName);
        }

        System.out.print("Would you like to view the complete product list? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            viewAllProducts(scanner);
        }
    }

    private static String getCurrencySymbol(Scanner scanner) {
        String currencySymbol;
        int choice;

        do {
            System.out.println("Select the currency:");
            System.out.println("1. Euro (€)");
            System.out.println("2. US Dollars ($)");
            System.out.println("3. Canadian Dollars (C$)");
            System.out.println("4. British Pounds (£)");
            System.out.println("5. Japanese Yen (JPY¥)");
            System.out.println("6. Chinese Yuan (CN¥)");
            System.out.println("7. Hong Kong Dollars (HK$)");
            System.out.println("8. Korean Won (₩)");
            System.out.println("9. Peruvian Sols (S/.)");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> currencySymbol = "€";
                    case 2 -> currencySymbol = "$";
                    case 3 -> currencySymbol = "C$";
                    case 4 -> currencySymbol = "£";
                    case 5 -> currencySymbol = "JPY¥";
                    case 6 -> currencySymbol = "CN¥";
                    case 7 -> currencySymbol = "HK$";
                    case 8 -> currencySymbol = "₩";
                    case 9 -> currencySymbol = "S/.";
                    default -> {
                        System.out.println("Wrong answer, please select again.");
                        currencySymbol = null;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
                currencySymbol = null;
            }
        } while (currencySymbol == null);

        return currencySymbol;
    }


    private static String getSizeUnit(Scanner scanner) {
        String sizeUnit;
        int choice;

        do {
            System.out.println("Select the size unit:");
            System.out.println("1. Milliliters (ml)");
            System.out.println("2. Liters (l)");
            System.out.println("3. Ounces (oz)");
            System.out.println("4. Kilograms (kg)");
            System.out.println("5. Grams (g)");
            System.out.println("6. Other");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> sizeUnit = "ml";
                    case 2 -> sizeUnit = "l";
                    case 3 -> sizeUnit = "oz";
                    case 4 -> sizeUnit = "kg";
                    case 5 -> sizeUnit = "g";
                    case 6 -> {
                        System.out.print("Enter the size unit: ");
                        sizeUnit = scanner.nextLine().trim();
                    }
                    default -> {
                        System.out.println("Wrong answer, please select again.");
                        sizeUnit = null;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
                sizeUnit = null;
            }
        } while (sizeUnit == null);

        return sizeUnit;
    }



    private static void addIngredientDetails(Scanner scanner, String productName) {
        System.out.print("Enter ingredient name (leave blank to skip): ");
        String ingredientName = scanner.nextLine();

        // If ingredient name is empty, skip adding ingredient details
        if (ingredientName.isEmpty()) {
            System.out.println("Skipping ingredient addition.");
            return;
        }

        boolean isCrueltyFree = getBooleanInput(scanner, "Is the ingredient cruelty-free? (yes/no): ");
        boolean isCleanBeauty = getBooleanInput(scanner, "Is it clean beauty? (yes/no): ");
        boolean isFragranceFree = getBooleanInput(scanner, "Is it fragrance-free? (yes/no): ");

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             CallableStatement statement = connection.prepareCall("{CALL AddIngredientDetails(?, ?, ?, ?, ?)}")) {

            statement.setString(1, ingredientName);
            statement.setBoolean(2, isCrueltyFree);
            statement.setBoolean(3, isCleanBeauty);
            statement.setBoolean(4, isFragranceFree);
            statement.setString(5, productName);

            statement.executeUpdate();
            System.out.println("Ingredient details added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static boolean getBooleanInput(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }


    private static void addPackageDetails(Scanner scanner, String productName) {
        System.out.print("Enter package color (leave blank to skip): ");
        String color = scanner.nextLine();

        System.out.print("Enter package material (leave blank to skip): ");
        String material = scanner.nextLine();

        System.out.print("Enter package weight (leave blank for default): ");
        String weightInput = scanner.nextLine();
        double weight = weightInput.isEmpty() ? 0.0 : Double.parseDouble(weightInput);

        boolean refillAvailable = getBooleanInput(scanner, "Is refill available? (yes/no): ");
        boolean sustainablePackage = getBooleanInput(scanner, "Is the package sustainable? (yes/no): ");

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             CallableStatement statement = connection.prepareCall("{CALL AddPackageDetails(?, ?, ?, ?, ?, ?)}")) {

            statement.setString(1, productName);
            statement.setString(2, color.isEmpty() ? null : color);
            statement.setString(3, material.isEmpty() ? null : material);
            statement.setDouble(4, weight);
            statement.setBoolean(5, refillAvailable);
            statement.setBoolean(6, sustainablePackage);

            statement.execute();
            System.out.println("Package details added successfully.");
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to add package details.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for weight. Please enter a number.");
        }
    }



    private static void addBrandDetails(Scanner scanner, String brandName) {
        System.out.print("Enter brand description (leave blank if not applicable): ");
        String description = scanner.nextLine();

        System.out.print("Enter country of origin (leave blank if not applicable): ");
        String country = scanner.nextLine();

        System.out.print("Enter founding year (leave blank if not applicable): ");
        String foundingYearInput = scanner.nextLine();
        Integer foundingYear = foundingYearInput.isEmpty() ? null : Integer.parseInt(foundingYearInput);

        System.out.print("Enter email (leave blank if not applicable): ");
        String email = scanner.nextLine();

        System.out.print("Enter telephone number (leave blank if not applicable): ");
        String tel = scanner.nextLine();

        System.out.print("Enter founder name (leave blank if not applicable): ");
        String founder = scanner.nextLine();

        // Insert or update brand details in the database
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO brand " +
                     "(brand_name, b_description, country_of_origin, founding_year, email, tel, founder) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE b_description = ?, country_of_origin = ?, founding_year = ?, email = ?, tel = ?, founder = ?")) {

            statement.setString(1, brandName);
            setStringOrNull(statement, 2, description);
            setStringOrNull(statement, 3, country);
            if (foundingYear != null) {
                statement.setInt(4, foundingYear);
            } else {
                statement.setNull(4, java.sql.Types.INTEGER);
            }
            setStringOrNull(statement, 5, email);
            setStringOrNull(statement, 6, tel);
            setStringOrNull(statement, 7, founder);
            // For the ON DUPLICATE KEY UPDATE part
            setStringOrNull(statement, 8, description);
            setStringOrNull(statement, 9, country);
            if (foundingYear != null) {
                statement.setInt(10, foundingYear);
            } else {
                statement.setNull(10, java.sql.Types.INTEGER);
            }
            setStringOrNull(statement, 11, email);
            setStringOrNull(statement, 12, tel);
            setStringOrNull(statement, 13, founder);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for founding year. Please enter a valid number.");
        }
    }






    private static void editConcern() {
        System.out.print("Enter the concern to update: ");
        String concernName = scanner.nextLine().trim();

        System.out.println("Enter the new description for the concern '" + concernName + "' (leave blank to delete the description):");
        String newDescription = scanner.nextLine().trim();

        String userConfirmation = "no";
        if (newDescription.isEmpty()) {
            System.out.println("Are you sure you want to delete the description for this concern? (yes/no):");
            userConfirmation = scanner.nextLine().trim();
            if (!userConfirmation.equalsIgnoreCase("yes")) {
                System.out.println("Description update canceled.");
                return;
            }
        }

        String query = "CALL EditConcern(?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, concernName);
            statement.setString(2, newDescription);
            statement.setString(3, userConfirmation);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("Result"));
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to update the concern.");
            e.printStackTrace();
        }
    }




    private static void setStringOrNull(PreparedStatement statement, int parameterIndex, String value) throws SQLException {
        if (value.isEmpty()) {
            statement.setNull(parameterIndex, java.sql.Types.VARCHAR);
        } else {
            statement.setString(parameterIndex, value);
        }
    }



    private static void addConcernDetails(Scanner scanner, String concernName) {
        System.out.print("Enter concern description (leave blank if not applicable): ");
        String description = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             CallableStatement statement = connection.prepareCall("{CALL AddOrUpdateConcern(?, ?)}")) {

            statement.setString(1, concernName);
            statement.setString(2, description);

            statement.execute();
            System.out.println("Concern details added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    // optional
    private static void addTypeDetails(Scanner scanner, String typeName) {
        System.out.print("Enter type description: ");
        String description = scanner.nextLine();

        // Call the stored procedure to add or update type details in the database
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             CallableStatement statement = connection.prepareCall("{CALL addOrUpdateType(?, ?)}")) {

            statement.setString(1, typeName);
            statement.setString(2, description);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void viewAllProducts(Scanner scanner) {
        String query = "SELECT product_name FROM product ORDER BY product_name";
        boolean hasProducts = false;

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            int productNumber = 1;
            while (resultSet.next()) {
                hasProducts = true;
                String name = resultSet.getString("product_name");
                System.out.println(productNumber + ". " + name);
                productNumber++;
            }

            if (!hasProducts) {
                System.out.println("There are no products saved in the collection.");
                System.out.print("Do you want to add a product? (yes/no): ");
                String addProductChoice = scanner.nextLine().toLowerCase();
                if (addProductChoice.equals("yes")) {
                    addNewProduct(scanner);
                } else {
                    System.out.println("Exiting...");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // After viewProduct, users can select product directly fron the list or choose to filter by other keywords
    private static void viewAndChooseProducts(Scanner scanner) {
        viewAllProducts(scanner); // Displays all products

        System.out.println("Would you like to:");
        System.out.println("1. Select a product by name");
        System.out.println("2. Filter products by keyword");
        System.out.println("Enter your choice: ");

        int choice = scanner.nextInt();

        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> selectProductDirectly();
            case 2 -> filterProductsByKeyword();
            default -> System.out.println("Invalid choice.");
        }
    }

    // view product list -> select a product directly

    private static void selectProductDirectly() {
        System.out.println("Enter the product name to select: ");
        String productName = scanner.nextLine();

        String query = "CALL selectProduct(?)"; // Call the stored procedure

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, productName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Extracting product details
                    String price = resultSet.getString("price");
                    String size = resultSet.getString("size");
                    String url = resultSet.getString("url");
                    Date expirationDate = resultSet.getDate("expiration_date");
                    String concernName = resultSet.getString("concern_name");
                    String typeName = resultSet.getString("type_name");
                    String brandName = resultSet.getString("brand_name");

                    // Display the product information
                    System.out.println("Product Name: " + productName);
                    System.out.println("Price: " + price);
                    System.out.println("Size: " + size);
                    System.out.println("URL: " + url);
                    System.out.println("Expiration Date: " + expirationDate);
                    System.out.println("Concern: " + concernName);
                    System.out.println("Type: " + typeName);
                    System.out.println("Brand: " + brandName);

                    System.out.println("\nOptions for " + productName + ":");
                    System.out.println("1. Edit Product");
                    System.out.println("2. Delete Product");
                    System.out.println("3. Go Back");
                    System.out.println("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1 -> editMenu(productName); // Call edit menu
                        case 2 -> deleteProduct(productName); // Call method to delete the product
                        case 3 -> {
                            return; // Go back
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    System.out.println("Product not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    // filter products by any keyword
    private static void filterProductsByKeyword() {
        List<String> filteredProductNames = new ArrayList<>();
        System.out.println("Enter keyword to search for: ");
        String keyword = scanner.nextLine().trim();
        String searchKeyword = "%" + keyword + "%";

        String query = "CALL filterProductsByKeyword(?)"; // Calling the stored procedure

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, searchKeyword);

            try (ResultSet resultSet = statement.executeQuery()) {
                boolean isResultFound = false;
                while (resultSet.next()) {
                    isResultFound = true;
                    String name = resultSet.getString("product_name");
                    filteredProductNames.add(name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!filteredProductNames.isEmpty()) {
            System.out.println("Products after filtering:");
            int i = 1;
            for (String s : filteredProductNames) {
                System.out.println( i++ + ". " + s);
            }
            System.out.println("Would you like to select a product from these results? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                selectAndViewProduct(filteredProductNames);
            }
        } else {
            System.out.println("No products found with the specified keyword.");
        }
    }


    // prompt the user to ask for selecting a product
    private static void selectAndViewProduct(List<String> productNames) {
        System.out.println("Select a product by typing its name:");
        while (true) {
            String selectedProductName = scanner.nextLine();

            if (selectedProductName.equalsIgnoreCase("exit")) {
                System.out.println("Exiting product selection.");
                break;
            }

            if (productNames.contains(selectedProductName)) {
                System.out.println("You have selected: " + selectedProductName);
                viewSelectedProduct(selectedProductName);  // View the selected product's details
                break;
            } else {
                System.out.println("Product not found in the list. Please try again or type 'exit' to go back.");
            }
        }
    }


    private static void viewSelectedProduct(String productName) {
        String query = "{CALL ViewSelectedProduct(?)}";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             CallableStatement statement = connection.prepareCall(query)) {

            statement.setString(1, productName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    double price = resultSet.getDouble("price");
                    String size = resultSet.getString("size"); // Assuming size is a String
                    String url = resultSet.getString("url");
                    Date expirationDate = resultSet.getDate("expiration_date");
                    String concernName = resultSet.getString("concern_name");
                    String typeName = resultSet.getString("type_name");
                    String brandName = resultSet.getString("brand_name");

                    // Display the product details
                    System.out.println("Product Name: " + productName + ", Price: " + price + ", Size: " + size +
                            ", URL: " + url + ", Expiration Date: " + expirationDate +
                            ", Concern: " + concernName + ", Type: " + typeName +
                            ", Brand: " + brandName);
                    System.out.println("\nOptions for '" + productName + "':");
                    System.out.println("1. Edit this product");
                    System.out.println("2. Delete this product");
                    System.out.println("3. Go back");
                    System.out.println("Enter your choice: ");

                    int actionChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (actionChoice) {
                        case 1 -> editMenu(productName); // Implement this method to edit the product
                        case 2 -> deleteProduct(productName); // Implement this method to delete the product
                        case 3 -> {
                            return; // Go back without any action
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    System.out.println("Product not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private static void deleteProduct(String productName) {
        String query = "DELETE FROM product WHERE product_name = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, productName);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product '" + productName + "' has been deleted.");
            } else {
                System.out.println("Product not found or could not be deleted.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to delete the product.");
            e.printStackTrace();
        }
    }

    private static void editMenu(String productName) {
        while (true) {
            System.out.println("\nEdit Menu:");
            System.out.println("1. Edit Product");
            System.out.println("2. Edit Brand");
            System.out.println("3. Edit Type");
            System.out.println("4. Edit Concern");
            System.out.println("5. Edit Function");
            System.out.println("6. Edit Ingredient");
            System.out.println("7. Edit Package");
            System.out.println("8. Go Back");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.println("Which field would you like to update? Options: price, size, url, expiration_date, concern_name, type_name, brand_name");
                    System.out.print("Enter the field to update: ");
                    String field = scanner.nextLine().trim();
                    System.out.println("Enter the new value for " + field + " (leave blank to delete):");
                    String newValue = scanner.nextLine().trim();
                    while (field.equalsIgnoreCase("price") && Double.parseDouble(newValue.trim().substring(1).trim()) < 0) {
                        System.out.println("Please enter a valid price.");
                        newValue = scanner.nextLine().trim();
                    }
                    editProduct(productName, field, newValue);
                }
                case 2 -> editBrand(); // Implement this method
                case 3 -> editType(); // Implement this method
                case 4 -> editConcern(); // Implement this method
                case 5 -> editFunction(); // Implement this method
                case 6 -> editIngredient(); // Implement this method
                case 7 -> editPackage(); // Implement this method
                case 8 -> {
                    return; // Exit the edit menu
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }




    public static void editProduct(String productName, String field, String newValue) {
        String query = "CALL editProduct(?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, productName);
            statement.setString(2, field);
            statement.setString(3, newValue);
            System.out.println("Do you want to edit " + productName + "? (yes/no)");
            String answer = scanner.nextLine();
            while (!answer.equalsIgnoreCase("yes")
                    && !answer.equalsIgnoreCase("no")){
                System.out.println("Please give a valid response.");
                answer = scanner.nextLine();
            }
            if (answer.equalsIgnoreCase("no")) {
                System.out.println("Cancel to update " + productName);
                return;
            }
            statement.execute();
            System.out.println("Product '" + productName + "' has been updated.");
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to update the product: " + e.getMessage());
        }
    }


    private static void editType() {
        System.out.println("Enter the type to update (e.g., skincare, makeup): ");
        String typeName = scanner.nextLine().trim();

        System.out.println("Enter the new name for the type '" + typeName + "': ");
        String newTypeName = scanner.nextLine().trim();

        String query = "CALL updateType(?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, typeName);
            statement.setString(2, newTypeName);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString("message"));
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to update the type.");
            e.printStackTrace();
        }
    }


    private static void editBrand() {
        System.out.println("Enter the brand name to update: ");
        String brandName = scanner.nextLine().trim();

        System.out.println("Which field would you like to update for '" + brandName + "'?");
        System.out.println("Options: b_description, country_of_origin, founding_year, email, tel, founder");
        System.out.println("Enter the field to update: ");
        String field = scanner.nextLine().trim();

        List<String> validFields = Arrays.asList("b_description", "country_of_origin", "founding_year", "email", "tel", "founder");
        if (!validFields.contains(field)) {
            System.out.println("Invalid field selected.");
            return;
        }

        System.out.println("Enter the new value for " + field + " (leave blank to remove):");
        String newValue = scanner.nextLine().trim();

        // Confirmation for empty values
        if (newValue.isEmpty()) {
            System.out.println("Are you sure you want to remove the value for " + field + "? (yes/no):");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if (!confirmation.equals("yes")) {
                System.out.println("Update cancelled.");
                return;
            }
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             CallableStatement statement = connection.prepareCall("{CALL UpdateBrandDetails(?, ?, ?)}")) {

            statement.setString(1, brandName);
            statement.setString(2, field);
            statement.setString(3, newValue);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Brand '" + brandName + "' has been successfully updated.");
            } else {
                System.out.println("Brand not found or could not be updated.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to update the brand.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for a numerical field.");
        }
    }





    private static void editFunction() {
        System.out.print("Enter the function name to update: ");
        String functionName = scanner.nextLine().trim();

        System.out.println("Enter the new name for the function: ");
        String newFunctionName = scanner.nextLine().trim();

        System.out.println("Enter the new description for the function (leave blank if not applicable): ");
        String newDescription = scanner.nextLine().trim();

        boolean deleteDescription = newDescription.isEmpty();

        // Confirm before deleting the description
        if (deleteDescription) {
            System.out.println("Are you sure you want to delete the description? (yes/no): ");
            String confirmation = scanner.nextLine().trim();
            if (!confirmation.equalsIgnoreCase("yes")) {
                System.out.println("Update cancelled.");
                return;
            }
        }

        String query = "CALL EditFunction(?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, functionName);
            statement.setString(2, newFunctionName);
            statement.setString(3, newDescription);
            statement.setBoolean(4, deleteDescription);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Function '" + functionName + "' has been updated to '" + newFunctionName + "'.");
                if (deleteDescription) {
                    System.out.println("Description deleted successfully.");
                }
            } else {
                System.out.println("Function not found or could not be updated.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to update the function.");
            e.printStackTrace();
        }
    }

    private static void editIngredient() {
        System.out.println("Enter the ingredient name to update: ");
        String ingredientName = scanner.nextLine().trim();

        System.out.println("Which field would you like to update for '" + ingredientName + "'?");
        System.out.println("Options: ingredient_name, is_cruelty_free, is_clean_beauty, is_fragrance_free");
        System.out.println("Enter the field to update: ");
        String field = scanner.nextLine().trim();

        List<String> validFields = Arrays.asList("ingredient_name", "is_cruelty_free", "is_clean_beauty", "is_fragrance_free");
        if (!validFields.contains(field)) {
            System.out.println("Invalid field selected.");
            return;
        }

        System.out.println("Enter the new value for " + field + " (leave blank to delete, true/false for booleans): ");
        String newValue = scanner.nextLine().trim();

        if (newValue.isEmpty()) {
            System.out.println("Are you sure you want to delete this field? (yes/no): ");
            if (!scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                System.out.println("Update cancelled.");
                return;
            }
        }

        String query = "CALL edit_ingredient(?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, ingredientName);
            statement.setString(2, field);
            statement.setString(3, newValue);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ingredient '" + ingredientName + "' has been updated.");
            } else {
                System.out.println("Ingredient not found or could not be updated.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to update the ingredient.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input for boolean field.");
        }
    }


    private static void editPackage() {
        System.out.println("Enter the product name to update its package: ");
        String productName = scanner.nextLine().trim();

        System.out.println("Which field would you like to update for the package of '" + productName + "'?");
        System.out.println("Options: color, material, weight, refill_available, sustainable_package");
        System.out.println("Enter the field to update: ");
        String field = scanner.nextLine().trim();

        List<String> validFields = Arrays.asList("color", "material", "weight", "refill_available", "sustainable_package");
        if (!validFields.contains(field)) {
            System.out.println("Invalid field selected.");
            return;
        }

        System.out.println("Enter the new value for " + field + " (leave blank to clear):");
        String newValue = scanner.nextLine().trim();

        // Call stored procedure
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             CallableStatement statement = connection.prepareCall("{CALL update_package(?, ?, ?)}")) {

            statement.setString(1, productName);
            statement.setString(2, field);
            statement.setString(3, newValue);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Package details for '" + productName + "' have been updated.");
            } else {
                System.out.println("Package not found or could not be updated.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to update the package.");
            e.printStackTrace();
        }
    }

}
