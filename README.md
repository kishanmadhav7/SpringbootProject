ToDo_Terminal: A Spring Boot To-Do List Application
Description
ToDo_Terminal is a full-stack web application that allows users to manage their personal to-do lists. It features a secure login system, a futuristic terminal-style user interface, and real-time countdowns to upcoming tasks. Each user has a private account where they can create, edit, complete, and delete their tasks.

This project is built using the Spring Boot framework for the backend and Thymeleaf for server-side rendering of the dynamic HTML pages. It is connected to a MySQL database for persistent data storage.

Features
User Authentication: Secure user registration and login system powered by Spring Security. Passwords are encrypted using BCrypt.

Private To-Do Lists: Each user's to-do list is completely private and tied to their account.

Full CRUD Functionality: Create, Read, Update, and Delete to-do items.

Due Dates & Times: Assign a specific due date and time to each task using a calendar picker.

Live Countdown Timer: The dashboard displays a real-time countdown to the next upcoming (incomplete) task.

Themed UI: A stylish, futuristic interface inspired by computer terminals, with a dynamic animated background.

Tech Stack
Backend:

Java 17

Spring Boot 3

Spring Security (for authentication)

Spring Data JPA (for database interaction)

Frontend:

Thymeleaf (Server-side template engine)

HTML5, CSS3, JavaScript (ES6)

Database:

MySQL

Build Tool:

Maven

Prerequisites
Before you begin, ensure you have the following installed on your local machine:

Java Development Kit (JDK): Version 17 or higher.

Apache Maven: For building the project and managing dependencies.

MySQL Server: The database where your user and task data will be stored.

Setup and Installation
Follow these steps to get the project running locally:

1. Clone the Repository

git clone <your-repository-url>
cd todo-terminal-project

2. Create the MySQL Database

Open your MySQL client (e.g., MySQL Workbench, DBeaver, or the command line).

Create a new database for the application.

CREATE DATABASE todolist_db;

3. Configure Application Properties

Navigate to src/main/resources/.

Open the application.properties file.

Update the spring.datasource properties with your MySQL username and password.

# MySQL Datasource Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/todolist_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

# ... other properties

The spring.jpa.hibernate.ddl-auto=update property will automatically create the necessary users and todo_item tables when the application starts for the first time.

How to Run the Application
Open a terminal or command prompt in the root directory of the project.

Use the Maven wrapper to build and run the application:

# On Windows
./mvnw spring-boot:run

# On macOS/Linux
./mvnw spring-boot:run

Once the application has started, open your web browser and navigate to:
http://localhost:8080

You will be redirected to the login page. You can create a new account using the "Sign Up" link.
