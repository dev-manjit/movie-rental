Overview# Movie Rental

### Tech stacks

* Java: 17
* Spring Boot: 3.3.3
* Apache-maven: 3.9.0

### Build application

* Download and Extract: Download the zip file of the Spring application and extract its contents to a directory of your
  choice.
* Import into IDE:Open your IDE (Eclipse or IntelliJ IDEA) and import the project using the existing Maven project
  option.
* Run Maven Command: Open a terminal or command prompt, navigate to the project's root directory. Run the Maven command:
  ```mvn clean install```
  This will build the project.
* Verify: Check the terminal or command prompt for any build errors or successful build messages

### Run application

* Run Maven Command: ```mvn spring-boot:run``` and verify functionalities with below API

### API Usage

- ***Register Movies***
    - **Endpoint:** `POST /api/v1/movies`
    - **Parameters:**
    - {
      "id":"XXX",
      "title":"XXXXX",
      "code":"NEW|REGULAR|CHILDREN"
      }
    -
        - The input Json with Movie details and code(Movie category-NEW,REGULAR,CHILDREN) in given format.
    - **Example:** To register the Movie 'Car'
      URL: `http://localhost:8080//api/v1/movies`
    - **Request Type:** raw JSON in postman
    - **Request Body:**  {
      "id":"F003",
      "title":"Fast & Furious X",
      "code":"NEW"
      }
    - **Response:** The API returns a response containing the saved Movie with id or failure response.

- ***Generate Rental Statement***
    - **Endpoint:** `POST /api/v1/rentals/statement`
    - **Parameters:**
    - {
      "name":"XXXX",
      "rentals":[{
      "movieId":"XXX",
      "days": XX
      },
      {
      "movieId":"XXX",
      "days": XX
      }
      ]
      }
    -
        - The input Json contain Customer name and Rental details(movie id and rental days) in given format.
    - **Example:** To generate statement of a customer
      URL: `http://localhost:8080/api/v1/rentals/statement`
    - **Request Type:** raw JSON in postman
    - **Request Body:**  {
      "name":"Robert H.",
      "rentals":[{
      "movieId":"F001",
      "days": 3
      },
      {
      "movieId":"F002",
      "days": 2
      },
      {
      "movieId":"F003",
      "days": 5
      }
      ]
      }
    - **Note:** Make sure these movies already registered.
    - **Response:** The API returns a response containing the saved Movie with id or failure response.
-
    - **Testing:** You can test the API using tools like Postman or by accessing the URL directly in a web browser.

### Test application

* Test Command: Execute the following Maven command to run the tests
  ```mvn test```
  This command will compile the test classes, execute the tests, and provide the results in the terminal.

* Check Test Results:
  After the tests are executed, you'll see output in the terminal indicating whether the tests passed or failed.
  Additionally, you can check the detailed test reports in the target/surefire-reports directory.


