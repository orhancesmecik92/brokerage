
Brokerage App Initialization.

Brokage API is a backend application developed for a brokerage firm. Employees can create, list, and cancel stock orders on behalf of customers.

## Setup & Run

### Requirements
- **Java 17** or later
- **Maven**
- **H2 Database** (embedded)
- **Postman** (optional for testing)

### Clone the Repository
Clone the project from GitHub:
open git bash
git clone <repository url here>
cd brokage

mvn clean install
mvn spring-boot:run

http://localhost:"port"/h2-console
jdbc:h2:mem:testdb
Username: sa
Password: (leave empty)
