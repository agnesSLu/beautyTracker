***REMOVED***

import security.SecureCredentialsStorage;
import javax.crypto.SecretKey;
***REMOVED***
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***

        // Main menu
***REMOVED***
            System.out.println("\nChoose an option:");
            System.out.println("1. View Products");
            System.out.println("2. Add New Product");
***REMOVED***
***REMOVED***

            int mainChoice = scanner.nextInt();
            switch (mainChoice) {
***REMOVED***
                    viewProducts();
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
***REMOVED***
***REMOVED***
***REMOVED***


***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
                "LEFT JOIN brand ON product.brand_name = brand.brand_name";

***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***

                System.out.println("Product Name: " + productName + ", Price: " + price + ", Size: " + size +
                        ", URL: " + url + ", Expiration Date: " + expirationDate + ", Concern: " + concernName +
                        ", Type: " + typeName + ", Brand: " + brandName);
***REMOVED***

***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***


    private static void viewProducts() {
        String query = "SELECT product_name, price, size, url, expiration_date FROM product";

***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***

                System.out.println("Product Name: " + productName + ", Price: " + price + ", Size: " + size +
                        ", URL: " + url + ", Expiration Date: " + expirationDate);
***REMOVED***

            // Display sub-menu after listing products
            displayProductsSubMenu();

***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
        System.out.println("Are you sure you want to add a new product? (yes/no):");
        String confirmation = scanner.next();

        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Add new product cancelled.");
***REMOVED***
***REMOVED***

        System.out.println("Enter product name:");
        String product_name = scanner.next();

        System.out.println("Enter product price:");
***REMOVED***

        System.out.println("Enter product size:");
        int size = scanner.nextInt();

        System.out.println("Enter product url:");
        String url = scanner.next();

        System.out.println("Enter product expiration date:");
        Long expiration = scanner.nextLong();

        // Add SQL insertion logic
***REMOVED***
             PreparedStatement statement = connection.prepareStatement("INSERT INTO product (product_name, price, size, url, expiration_date) VALUES (?, ?, ?, ?, ?)")) {

            statement.setString(1, product_name);
***REMOVED***
            statement.setInt(3, size);
***REMOVED***
            statement.setDate(5, new Date(expiration));

***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***



***REMOVED***
***REMOVED***
        String keyword = scanner.nextLine();

        String query = "SELECT * FROM product WHERE product_name LIKE ?"; // Assuming you're searching by name; modify as needed

***REMOVED***
***REMOVED***

            statement.setString(1, "%" + keyword + "%");

***REMOVED***
    ***REMOVED***
    ***REMOVED***
    ***REMOVED***
    ***REMOVED***
                    // Add any other product attributes you need to display
                    System.out.println("Name: " + name + ", Price: " + price+ ", Size: " + size );
    ***REMOVED***
***REMOVED***

***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***

    private static void filterProductsByType() {
        System.out.println("Do you want to filter products by type? (yes/no):");
        String confirmation = scanner.nextLine();

        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Filtering by type cancelled.");
***REMOVED***
***REMOVED***

        System.out.println("Enter product type to filter by:");
        String type = scanner.nextLine();

        String query = "SELECT product_name FROM product WHERE type_name = ?";

***REMOVED***
***REMOVED***

            statement.setString(1, type);

***REMOVED***
                boolean productsFound = false;

    ***REMOVED***
                    productsFound = true;
    ***REMOVED***
***REMOVED***
    ***REMOVED***

                if (!productsFound) {
                    System.out.println("No products found for the specified type.");
    ***REMOVED***

***REMOVED*** catch (SQLException e) {
    ***REMOVED***
***REMOVED***
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***


    private static void displayProductsSubMenu() {
        System.out.println("\nProduct Options:");
        System.out.println("1. Filter Products by Keyword");
        System.out.println("2. Filter Products by Type");
        System.out.println("3. Return to Main Menu");
***REMOVED***

***REMOVED***
***REMOVED*** // Consume the newline left-over

***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
                filterProductsByType();
***REMOVED***
            case 3:
***REMOVED*** // Return to main menu
***REMOVED***
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
***REMOVED***
***REMOVED***

    private static void selectProduct(List<String> productNames) {
        System.out.println("Enter the name of the product to select:");
        String selectedProduct = scanner.nextLine();

        if (productNames.contains(selectedProduct)) {
            System.out.println("You selected: " + selectedProduct);
            // Further processing for the selected product
***REMOVED*** else {
            System.out.println("Product not found in the list.");
***REMOVED***


***REMOVED***

    private static void keywordSearchSubMenu(List<String> productNames) {
        System.out.println("\nOptions:");
        System.out.println("1. Select a product");
        System.out.println("2. Filter these products by category");
***REMOVED***

***REMOVED***
***REMOVED*** // Consume the newline

***REMOVED***
***REMOVED***
                selectProduct(productNames);
***REMOVED***
***REMOVED***
                filterByCategoryAndSelectProduct(productNames);
***REMOVED***
***REMOVED***
                System.out.println("Invalid choice. Please enter 1 or 2.");
***REMOVED***
***REMOVED***


    private static void filterByCategoryAndSelectProduct(List<String> productNames) {
        System.out.println("Enter category to filter by:");
        String category = scanner.nextLine();
        // Filter the productNames list by the specified category
        List<String> filteredProductNames = getProductsByCategory(productNames, category);

        if (filteredProductNames.isEmpty()) {
            System.out.println("No products found in this category.");
***REMOVED*** else {
            // Display filtered products
            System.out.println("Filtered Products:");
            for (String productName : filteredProductNames) {
                System.out.println(productName);
***REMOVED***
            // Now let the user select a product
            selectProduct(filteredProductNames);
***REMOVED***
***REMOVED***

    private static List<String> getProductsByCategory(List<String> productNames, String category) {
***REMOVED***
        String query = "SELECT product_name FROM product WHERE type_name = ? AND product_name IN (";

        // Build the query dynamically to include placeholders for each product name
        for (int i = 0; i < productNames.size(); i++) {
            query += "?,";
***REMOVED***
        query = query.substring(0, query.length() - 1);  // Remove the trailing comma
        query += ")";

***REMOVED***
***REMOVED***

            statement.setString(1, category);
            // Set each product name in the query
            for (int i = 0; i < productNames.size(); i++) {
                statement.setString(i + 2, productNames.get(i)); // Indexing starts at 1 and 1 is already used for category
***REMOVED***

***REMOVED***
    ***REMOVED***
                    filteredProductNames.add(resultSet.getString("product_name"));
    ***REMOVED***
***REMOVED***
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***

        return filteredProductNames;
***REMOVED***



***REMOVED***
