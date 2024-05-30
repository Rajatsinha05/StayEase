
---

# StayEase API

StayEase API is a RESTful service that provides endpoints for managing users, hotels, and bookings.

## Tech Stack

- **Java**: Core programming language
- **Spring Boot**: Framework for building the application
- **Spring Security**: For authentication and authorization
- **Gradle**: Build automation tool
- **MySQL**: Database management system
- **Hibernate**: ORM (Object-Relational Mapping) framework

## Setup

To set up and run the StayEase API locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/rajatsinha05/StayEase.git
   ```
2. Navigate to the project directory:
   ```bash
   cd StayEase
   ```
3. Build the project using Gradle:
   ```bash
   ./gradlew build
   ```
4. Run the application:
   ```bash
   java -jar build/libs/StayEase.jar
   ```

## Routes

### User Management

#### Register User

- **Route:** `POST /users/register`
- **Description:** Registers a new user in the system.
- **Request Body:**
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "role": "USER"
  }
  ```
- **Sample Response:**
  - Status: 201 Created
  - Body:
    ```json
    {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "role": "USER"
    }
    ```

#### User Login

- **Route:** `POST /users/login`
- **Description:** Authenticates a user and returns an access token.
- **Request Body:**
  ```json
  {
    "email": "john.doe@example.com",
    "password": "password123"
  }
  ```
- **Sample Response:**
  - Status: 200 OK
  - Body:
    ```json
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
    ```

#### Get All Users

- **Route:** `GET /users`
- **Description:** Retrieves information about all users. (Accessible only to admins)
- **Sample Response:**
  - Status: 200 OK
  - Body:
    ```json
    [
      {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@example.com",
        "role": "USER"
      },
      {
        "id": 2,
        "firstName": "Jane",
        "lastName": "Smith",
        "email": "jane.smith@example.com",
        "role": "ADMIN"
      }
    ]
    ```

#### Get User By ID

- **Route:** `GET /users/{id}`
- **Description:** Retrieves information about a specific user.
- **Sample Response:**
  - Status: 200 OK
  - Body:
    ```json
    {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "role": "USER"
    }
    ```

#### Update User

- **Route:** `PUT /users/{id}`
- **Description:** Allows users to update their own information.
- **Request Body:**
  ```json
  {
    "firstName": "Updated First Name"
  }
  ```
- **Sample Response:**
  - Status: 200 OK
  - Body:
    ```json
    {
      "id": 1,
      "firstName": "Updated First Name",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "role": "USER"
    }
    ```

#### Delete User

- **Route:** `DELETE /users/{id}`
- **Description:** Allows users to delete their own account.
- **Sample Response:**
  - Status: 200 OK

### Hotel Management

#### Add Hotel

- **Route:** `POST /hotels`
- **Description:** Adds a new hotel to the system.
- **Request Body:**
  ```json
  {
    "name": "Sample Hotel",
    "location": "City A",
    "rating": 4.5
  }
  ```
- **Sample Response:**
  - Status: 201 Created
  - Body:
    ```json
    {
      "id": 1,
      "name": "Sample Hotel",
      "location": "City A",
      "rating": 4.5
    }
    ```

#### Get All Hotels

- **Route:** `GET /hotels`
- **Description:** Retrieves information about all hotels.
- **Sample Response:**
  - Status: 200 OK
  - Body:
    ```json
    [
      {
        "id": 1,
        "name": "Sample Hotel",
        "location": "City A",
        "rating": 4.5
      },
      {
        "id": 2,
        "name": "Another Hotel",
        "location": "City B",
        "rating": 4.2
      }
    ]
    ```

#### Get Hotel By ID

- **Route:** `GET /hotels/{id}`
- **Description:** Retrieves information about a specific hotel.
- **Sample Response:**
  - Status: 200 OK
