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
***REMOVED***
            System.out.println("2. Exit");
***REMOVED***

            int mainChoice = scanner.nextInt();
    ***REMOVED***

            switch (mainChoice) {
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
                    System.out.println("Invalid choice. Please enter 1 or 2.");
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
***REMOVED***
***REMOVED***
                System.out.println(productCount + ". Product Name: " + productName + ", Price: " + price +
                        ", Size: " + size + ", URL: " + url + ", Expiration Date: " + expirationDate +
                        ", Concern: " + concernName + ", Type: " + typeName + ", Brand: " + brandName);
***REMOVED***
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***

        // Prompt for product details
***REMOVED***
***REMOVED***

        System.out.print("Enter price: ");
***REMOVED***
***REMOVED***

        System.out.print("Enter size: ");
        int size = scanner.nextInt();
***REMOVED***

        System.out.print("Enter URL: ");
***REMOVED***

        System.out.print("Enter expiration date (YYYY-MM-DD): ");
        String expirationDate = scanner.nextLine();

***REMOVED***
***REMOVED***
***REMOVED***

        System.out.print("Enter type (skincare, makeup, etc.): ");
***REMOVED***
        // addTypeDetails(scanner, typeName); // Optional, based on whether you need to modify ENUM values

***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
            statement.setInt(3, size);
***REMOVED***
            statement.setDate(5, java.sql.Date.valueOf(expirationDate));
***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED*** else {
***REMOVED***
***REMOVED***
***REMOVED*** catch (SQLException e) {
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

***REMOVED***
***REMOVED***
            viewAllProducts();  // Method to display all products
***REMOVED***
***REMOVED***


***REMOVED***
        System.out.print("Enter ingredient name: ");
***REMOVED***

        System.out.print("Is the ingredient cruelty-free? (true/false): ");
        boolean isCrueltyFree = scanner.nextBoolean();

        System.out.print("Is it clean beauty? (true/false): ");
        boolean isCleanBeauty = scanner.nextBoolean();

        System.out.print("Is it fragrance-free? (true/false): ");
        boolean isFragranceFree = scanner.nextBoolean();
***REMOVED***

***REMOVED***
             PreparedStatement statement = connection.prepareStatement("INSERT INTO ingredient (ingredient_name, is_cruelty_free, is_clean_beauty, is_fragrance_free, product_name) VALUES (?, ?, ?, ?, ?)")) {

***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
        System.out.print("Enter package color: ");
***REMOVED***

        System.out.print("Enter package material: ");
***REMOVED***

        System.out.print("Enter package weight: ");
        double weight = scanner.nextDouble();
***REMOVED***

        System.out.print("Is refill available? (true/false): ");
        boolean refillAvailable = scanner.nextBoolean();

        System.out.print("Is the package sustainable? (true/false): ");
        boolean sustainablePackage = scanner.nextBoolean();
***REMOVED***

***REMOVED***
             PreparedStatement statement = connection.prepareStatement("INSERT INTO package (product_name, color, material, weight, refill_available, sustainable_package) VALUES (?, ?, ?, ?, ?, ?)")) {

***REMOVED***
            statement.setString(2, color);
            statement.setString(3, material);
***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***




***REMOVED***
        System.out.print("Enter brand description: ");
***REMOVED***

        System.out.print("Enter country of origin: ");
***REMOVED***

        System.out.print("Enter founding year: ");
        int foundingYear = scanner.nextInt();
***REMOVED***

        System.out.print("Enter email: ");
***REMOVED***

        System.out.print("Enter telephone number: ");
        int tel = scanner.nextInt();
***REMOVED***

        System.out.print("Enter founder name: ");
***REMOVED***

***REMOVED***
***REMOVED***
             PreparedStatement statement = connection.prepareStatement("INSERT INTO brand (brand_name, b_description, country_of_origin, founding_year, email, tel, founder) VALUES (?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE b_description = ?, country_of_origin = ?, founding_year = ?, email = ?, tel = ?, founder = ?")) {

***REMOVED***
            statement.setString(2, description);
            statement.setString(3, country);
            statement.setInt(4, foundingYear);
            statement.setString(5, email);
            statement.setInt(6, tel);
            statement.setString(7, founder);
***REMOVED***
            statement.setString(8, description);
            statement.setString(9, country);
            statement.setInt(10, foundingYear);
            statement.setString(11, email);
            statement.setInt(12, tel);
            statement.setString(13, founder);

***REMOVED***
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
        System.out.print("Enter concern description: ");
***REMOVED***

***REMOVED***
***REMOVED***

***REMOVED***
            statement.setString(2, description);
***REMOVED***

***REMOVED***
***REMOVED*** catch (SQLException e) {
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
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***

    private static void viewAllProducts() {
        // SQL query to fetch and display all products
        String query = "SELECT product_name, price, size, url, expiration_date FROM product";
***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
                // Extract product details
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***

                // Display product information
                System.out.println("Name: " + name + ", Price: " + price + ", Size: " + size +
                        ", URL: " + url + ", Expiration Date: " + expirationDate);
***REMOVED***
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***

    private static void selectProduct(List<String> productNames) {
***REMOVED***
        String selectedProductName = scanner.nextLine();

        if (productNames.contains(selectedProductName)) {
            System.out.println("You have selected: " + selectedProductName);

***REMOVED*** else {
            System.out.println("Product not found in the list. Please try again.");
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
    ***REMOVED***
    ***REMOVED***

***REMOVED***

***REMOVED***
                    System.out.println("Name: " + name + ", Price: " + price + ", Size: " + size +
***REMOVED***
***REMOVED***
***REMOVED***
    ***REMOVED***
***REMOVED***
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***

        if (filteredProductNames.isEmpty()) {
***REMOVED***
***REMOVED*** else {
            selectAndViewProduct(filteredProductNames);
***REMOVED***
***REMOVED***



***REMOVED***
***REMOVED***
        if (productNames.isEmpty()) {
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
***REMOVED***
***REMOVED***
***REMOVED*** else {
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
    ***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
    ***REMOVED*** else {
***REMOVED***
    ***REMOVED***
***REMOVED***
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***


***REMOVED***

        String deleteQuery = "DELETE FROM product WHERE product_name = ?";
***REMOVED***
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

***REMOVED***
***REMOVED***

***REMOVED***
                System.out.println("Product deleted successfully.");
***REMOVED*** else {
***REMOVED***
***REMOVED***
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***

    private static void editProduct(String productName) {

        System.out.println("Enter new price:");
        double newPrice = scanner.nextDouble();
***REMOVED*** // Consume the newline

        String updateQuery = "UPDATE product SET price = ? WHERE product_name = ?";
***REMOVED***
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setDouble(1, newPrice);
***REMOVED***
***REMOVED***

***REMOVED***
                System.out.println("Product updated successfully.");
***REMOVED*** else {
***REMOVED***
***REMOVED***
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED***









***REMOVED***
