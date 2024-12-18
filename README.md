# OnlineQuizApplication

## Project Overview
This Online Quiz Application allows:
- ADMIN to create quizzes.
- NORMAL user to take quizzes and view their results.

The application is built using:
- Backend: Java, Spring Boot
- Database: MySQL
- Security: Basic Spring Security
- Documentation: Swagger (OpenAPI)

## Setup Instructions

Prerequisites
- Java Development Kit (JDK) 11 or later
- SpringBoot 2xx
- Maven
- MySQL Server
- IDE: STS

---

## Steps to Run the Application

1. Clone the Repository:
   ```bash
   git clone https://github.com/shubhamrothe/OnlineQuizApplication.git
   cd OnlineQuizApplication

2. Configure MySQL Database:
  - Create a database
  - Update database credentials in application.properties
    
3. Install Dependencies:
  - Run the following command:
   mvn clean install

4. Run the Application:
   
5. Access the Application:
  - Swagger UI: http://localhost:9090/swagger-ui/index.html
  - REST API base URL: http://localhost:9090/api/v1
