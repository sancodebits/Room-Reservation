# ROOM-RESERVATION (Spring Boot)

This is a basic yet interesting project built with Spring Boot, focusing on the concept of room reservation with essential booking features.

## Step-by-Step Guide to Run the Project Application

Follow these steps to run the Spring Boot Room Reservation application:

1.  **Navigate to the Application Entry Point:**

      * Go to the project's source directory: `src → main → java → com → sancode → miniproject`
      * Locate the main application file: `miniProjectApplication.java`

2.  **Run the Application:**

      * Right-click on `miniProjectApplication.java`
      * Select "Run As" or "Run" and choose "Java Application". This will initiate the Spring Boot application.

3.  **Troubleshooting Database Connectivity Issues:**

      * **Initial Observation:** If the Spring project starts in the terminal but stops or gets interrupted, it's likely due to database connectivity problems. This often indicates that the application cannot find or connect to the MySQL database.
      * **Database Check and Creation (MySQL):**
          * Open your MySQL client or command-line tool.
          * Check if a database named `miniproject` exists. You can use the following query:
            ```sql
            SHOW DATABASES;
            ```
          * If the `miniproject` database does not exist, create it using the following SQL command:
            ```sql
            CREATE DATABASE miniproject;
            ```
          * To confirm the database creation, run `SHOW DATABASES;` again and verify that `miniproject` is now listed.

4.  **Re-run the Application:**

      * Close your MySQL client.
      * Go back to your IDE and re-run `miniProjectApplication.java` as a Java Application.

5.  **Verify Successful Startup and Table Creation:**

      * **Console Output:** Observe the console output during application startup. You should see lines of code executing, including messages related to table creation. This indicates that Hibernate (the ORM framework used in Spring Boot) is successfully connecting to the database and creating tables based on your application's entities.
      * **Application Port:**  Look for a line in the console output that indicates the application has started. It should mention the default port, typically:
        ```
        ... Tomcat started on port(s): 8080 (http) with context path '' ...
        ```
        This confirms that the application is running on `localhost:8080`.

6.  **Database Table Verification:**

      * To ensure that the tables have been created in the `miniproject` database, open your MySQL client again.

      * Connect to your MySQL server and use the `miniproject` database:

        ```sql
        USE miniproject;
        ```

      * List the tables in the database using:

        ```sql
        SHOW TABLES;
        ```

        You should see a list of tables that have been automatically created by the Spring Boot application.

      * To examine the schema of a specific table, use the `DESCRIBE` command:

        ```sql
        DESCRIBE _table_name_;
        ```

        Replace `_table_name_` with the actual name of a table from the `SHOW TABLES;` output. This will display the columns, data types, and other details of the table's structure.

      * **Database List Example:**
        ![Database List](https://user-images.githubusercontent.com/105698566/185990786-a67c66ab-0304-4fde-9102-e0d513e565d5.png)

          * *This image shows an example of the MySQL command line displaying a list of databases, including `miniproject`.*

      * **Table List Example:**
        ![Table List](https://user-images.githubusercontent.com/105698566/185990941-4c659768-88db-431f-bda4-83f97080eb9b.png)

          * *This image illustrates the tables created within the `miniproject` database by the application.*

      * **Table Description Example:**
        ![Table Description](https://user-images.githubusercontent.com/105698566/185990984-83a36e82-77ea-46c5-b8ab-2ac059a754ea.png)

          * *These images demonstrate the `DESCRIBE` command output, showing the schema (fields and data types) of a specific table. Note that initially, tables will be empty as no data has been entered yet.*

## API Endpoint Testing with Postman

To interact with the application's API endpoints, you can use Postman (or a similar API client).

1.  **Install Postman:**

      * Download and install the Postman application from [Download Postman](https://www.postman.com/downloads/).

2.  **API Methods and Usage:**

      * This project utilizes common HTTP methods for API interactions:

          * **GET:**  Used to retrieve data from the database. For example, to fetch admin data.
          * **POST:** Used to send new data to the database, creating new records.
          * **PUT:** Used to update existing records in the database.
          * **DELETE:** Used to remove records from the database.

      * **Method View Example:**
        ![Method View](https://user-images.githubusercontent.com/105698566/185991023-9259a6c5-5e2c-4a23-a374-c83b6990d1b3.png)

          * *This image highlights an example of how API methods are defined in the code, which dictates the URL structure for requests.*

      * **Postman GET Request Example:**
        ![Postman GET Request](https://user-images.githubusercontent.com/105698566/185991341-23243df2-ea51-4923-8ae0-4e27811cf422.png)

          * *This image shows a Postman example using a `GET` request to retrieve data from the `/admin` endpoint running on `localhost:8080`.*

By following these steps, you should be able to successfully run the ROOM-RESERVATION Spring Boot application, connect it to a MySQL database, verify table creation, and test API endpoints using Postman.
