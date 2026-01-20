# Library-Management-System

This project is a **Java-based console Library Management System** designed to automate the management of library resources and borrowing processes. It supports inventory tracking for various materials such as **books, magazines, and DVDs**, while also managing user operations including borrowing, returning, and penalty handling.

---

## Features

### 📦 Multi-Type Item Support
- The system supports **Books, Magazines, and DVDs**.
- Each material type has its own specific attributes (e.g., Author, Director, Publisher).
- All materials are managed through the `Items` class, which tracks:
  - Availability status (`isAvailable`)
  - Borrowed date (`borrowedDate`)

### User Roles & Constraints
- Supported user types:
  - **Student**
  - **Academic**
  - **Guest**
- Each user has:
  - A borrowing limit (`count`)
  - A penalty score (`penalty`)
- Borrowing and returning operations update user and item states dynamically.

### File-Based Data Management
- Initial data (items and users) and operational commands are read from text files.
- All system outputs and logs are written to **`output.txt`** in a detailed and structured format.

---

## Project Structure
src/
├── Main.java
├── Items.java
├── Users.java
├── fileReader.java

### File Descriptions
- **Main.java**  
  Entry point of the application. Handles file paths, data loading, and command execution.
- **Items.java**  
  Manages library materials, including availability and borrowing metadata.
- **Users.java**  
  Stores user information, borrowed items, and penalty tracking.
- **fileReader.java**  
  Utility class used by `Main` to parse and read input files.

---

## Input File Formats

The system processes the following input files:

- **items.txt**  
  Format: `Type,ID,Title,Attributes...`  
  Example:
  B,1001,The Great Gatsby,F. Scott Fitzgerald,Classic Fiction,normal
  - **users.txt**  
Format: `UserType,Name,ID,Phone,...`
- **commands.txt**  
Commands such as borrowing and returning items.

---

## Compilation & Execution

To compile and run the project (assuming input files are located in the `src` directory):

```bash
javac src/*.java
java src.Main src/items.txt src/users.txt src/commands.txt src/output.txt
