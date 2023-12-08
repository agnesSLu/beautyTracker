***REMOVED***

***REMOVED***
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED*** else if (input.equals("no")) {
***REMOVED***
***REMOVED*** else {
***REMOVED***
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
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED*** // Consume newline

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
    ***REMOVED*** else {
***REMOVED***
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
                    // Additional code to display product details can be added here if needed
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
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
                    editProduct(); // Implement this method
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
***REMOVED*** // Exit the edit menu
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***



    private static void editProduct() {
        System.out.print("Enter the name of the product to edit: ");
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

        System.out.println("Enter the new value for " + field + ":");
***REMOVED***

        // Building the SQL Update Query
***REMOVED***

***REMOVED***
***REMOVED***

***REMOVED***
            if (field.equals("price")) {
                statement.setDouble(1, Double.parseDouble(newValue));
***REMOVED*** else if (field.equals("size")) {
***REMOVED***
***REMOVED*** else if (field.equals("expiration_date")) {
                statement.setDate(1, java.sql.Date.valueOf(newValue));
***REMOVED*** else {
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
***REMOVED*** catch (NumberFormatException e) {
***REMOVED***
***REMOVED*** catch (IllegalArgumentException e) {
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
***REMOVED***
***REMOVED***

        System.out.println("Enter the new value for " + field + ":");
***REMOVED***

***REMOVED***

***REMOVED***
***REMOVED***

            // Handle special cases for data types
            if (field.equals("founding_year")) {
***REMOVED***
***REMOVED*** else {
***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
                System.out.println("Brand '" + brandName + "' has been updated.");
***REMOVED*** else {
***REMOVED***
***REMOVED***
***REMOVED*** catch (SQLException e) {
***REMOVED***
***REMOVED***
***REMOVED*** catch (NumberFormatException e) {
***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***

        System.out.println("Enter the new description for the concern '" + concernName + "':");
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

        System.out.print("Enter the new description for the function: ");
***REMOVED***

***REMOVED***

***REMOVED***
***REMOVED***

***REMOVED***
            statement.setString(2, newDescription);
***REMOVED***

***REMOVED***
***REMOVED***
                System.out.println("Function '" + functionName + "' has been updated to '" + newFunctionName + "' with new description.");
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
        System.out.println("Options: is_cruelty_free, is_clean_beauty, is_fragrance_free");
***REMOVED***
***REMOVED***

        List<String> validFields = Arrays.asList("is_cruelty_free", "is_clean_beauty", "is_fragrance_free");
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***

        System.out.println("Enter the new value for " + field + " (true/false):");
        boolean newValue = Boolean.parseBoolean(scanner.nextLine().trim());

***REMOVED***

***REMOVED***
***REMOVED***

            statement.setBoolean(1, newValue);
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
***REMOVED***
***REMOVED***

        System.out.println("Enter the new value for " + field + ":");
***REMOVED***

***REMOVED***

***REMOVED***
***REMOVED***

***REMOVED***
                statement.setDouble(1, Double.parseDouble(newValue));
***REMOVED*** else if (field.equals("refill_available") || field.equals("sustainable_package")) {
                statement.setBoolean(1, Boolean.parseBoolean(newValue));
***REMOVED*** else {
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
***REMOVED*** catch (NumberFormatException e) {
***REMOVED***
***REMOVED***
***REMOVED***









***REMOVED***
