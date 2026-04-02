# Student Management System (MVC & JDBC)

A professional Java-based desktop application for managing student records. This project demonstrates the **Model-View-Controller (MVC)** design pattern, database persistence using **JDBC**, and a modern graphical user interface built with **Java Swing**.

---

## 🚀 Features

-   **Full CRUD Operations**: Create, Read, Update, and Delete student records.
-   **Modern GUI Dashboard**: A responsive interface with the Nimbus Look and Feel.
-   **Real-time Data Sync**: Instant table updates upon any database operation.
-   **MVC Architecture**: Clean separation of concerns between data, logic, and presentation.
-   **MySQL Persistence**: Robust data handling via a local MySQL instance (XAMPP).

---

## 🛠️ Technology Stack

| Component | Technology |
| :--- | :--- |
| **Language** | Java 21 (OpenJDK) |
| **GUI Framework** | Java Swing (javax.swing) |
| **Database** | MySQL (via XAMPP) |
| **Connectivity** | JDBC (MySQL Connector/J) |
| **Architecture** | MVC (Model-View-Controller) |

---

## 🏗️ Project Structure & Logic

The project is organized into modular packages to ensure maintainability:

-   **`com.project.model`**: Contains the `Student` POJO, representing the data entity.
-   **`com.project.dao`**: The **Data Access Object** layer. Handles raw SQL queries (`PreparedStatement`) and connection management through `DBConnection`.
-   **`com.project.controller`**: The **Controller** layer. It validates user input and coordinates the flow between the DAO and the GUI.
-   **`com.project.view`**: The **View** layer. Implements the `StudentView` dashboard using Swing components.
-   **`sql/`**: Contains the database initialization script.

---

## ⚙️ Setup & Installation

### 1. Database Configuration
1.  Start **XAMPP** and launch the **MySQL** service.
2.  Import the [sql/create_table.sql](sql/create_table.sql) script using phpMyAdmin or your preferred SQL client.
3.  Ensure the database name is `Student_Management_System` and the user `root` has no password (default XAMPP settings).

### 2. JDBC Driver
Ensure the `mysql-connector-j-9.2.0.jar` is present in the `lib/` directory.

### 3. Run the Application
Open a PowerShell terminal in the project root and execute:

```powershell
./run.ps1
```

The script will automatically compile and launch the GUI.

---

## 👨‍💻 Logic Overview

1.  **Initialization**: `Main.java` launches the `StudentView` on the Event Dispatch Thread (EDT).
2.  **Data Flow**: When a user clicks "Add," the `StudentView` sends the input to the `StudentController`.
3.  **Validation**: The Controller checks for empty fields before calling the `StudentDAO`.
4.  **Persistence**: The `StudentDAO` opens a connection via `DBConnection`, executes the `INSERT` query using `PreparedStatement`, and returns the result.
5.  **UI Update**: Upon success, the `StudentView` clears the form and refreshes the `JTable` by fetching the latest list from the controller.

---

## 📝 License

This project is open-source and available for educational purposes.
