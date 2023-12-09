# beautyTracker

## Project Overview

BeautyTracker is a Java application designed to manage and track beauty products. 

It offers features like adding new products, viewing product collections, and maintaining detailed information about brands, 

product types, concerns, functions, ingredients, and packaging.

## Prerequisites

To build and run the BeautyTracker application, you will need the following software and libraries:

- Java Development Kit (JDK): A software development environment used for developing Java applications. Download and install JDK from Oracle's JDK Download Page.

- MySQL Database Server: Used to manage the application's database. Download and install MySQL from MySQL's Download Page.

- Java Database Connectivity (JDBC) Driver for MySQL: Required for connecting the application to the MySQL database. This is typically included with the MySQL installation, or you can download it separately from MySQL's Connector/J Page.


## Features

- Add New Products: Input details such as name, price, size, expiration date, and more.

- View Products: Browse through the existing collection of beauty products.

- Edit Products: Modify details of existing products in the collection.

- Delete Products: Remove products from the collection.

- Manage Brands, Types, and Concerns: Add and edit information related to product brands, types, and concerns.

- Ingredient and Package Details: Add and manage specific details about product ingredients and packaging.

## Installation & Setup

### Clone the Repository:
- git clone https://github.com/agnesSLu/beautyTracker.git

### Database Setup:

- Install MySQL Database Server following the instructions on its download page.
- Create a new database named beautytracker.
- Import the provided schema into your MySQL database.

### Configure Database Connection:
- Open the application's database configuration file.
- Update the DB_URL, USER, and PASSWORD variables with your MySQL connection details, including the expected installation directory for the MySQL server.
### Compile the Java Files:
- Ensure JDK is installed and properly set up in your system's PATH.
- Navigate to the source code directory and compile the Java files.
- Example command: javac BeautyTracker.java

## Running the Application

- Run the application from the command line or your IDE.

- Follow the on-screen prompts to interact with the application.

- Choose different options to add, view, edit, or delete products and their related information.
- After starting the application, follow the on-screen prompts to interact with it.
- You can add, view, edit, or delete products and manage related information.


## Database Schema

- Product Table: Stores main product details.

- Brand Table: Contains information about brands.

- Type Table: Lists types of products.

- Function Table: Details the functions of products.

- Concern Table: Lists various concerns addressed by products.

- Ingredient Table: Details about product ingredients.

- Package Table: Information on product packaging.

## Contributions

### Contributions to the BeautyTracker project are welcome. Please follow these steps:

- Fork the repository.

- Create a new branch for your feature.

- Commit your changes.

- Push to the branch.

- Submit a pull request.


