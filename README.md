# Assignment - ORM

This project is a simple implementation of an online store that sells digital products using Hibernate. The data model is represented using an Entity Relationship Diagram (ERD).

## ERD Overview

<img width="856" alt="Screenshot 2024-07-07 at 21 38 52" src="https://github.com/khanhduzz/assign2/assets/110228244/18eb1d30-6201-417d-81cc-065bce0caea3">

The ERD consists of the following entities:
- Song
- Movie
- Ebook
- Customer
- Order
- OrderLine
  
## Setup Instructions
1. Clone the repository.
2. Configure your MySQL database credentials in `hibernate.cfg.xml`.
3. Build the project using Maven: `mvn clean install`.

## Project Setup

### Prerequisites

#### Technologies Used

- **Java 11**: Programming language.
- **Spring Framework**: Used for dependency injection and managing transactions.
- **Hibernate ORM**: Object-Relational Mapping framework for database interactions.
- **MySQL Connector/J**: JDBC driver for MySQL database connectivity.
- **Hibernate Validator**: Used for bean validation.
- **Lombok**: Library to reduce boilerplate code in Java classes.
- **Ehcache**: Distributed cache management.
- **JAXB**: Java Architecture for XML Binding for XML processing.

#### Dependencies

All dependencies are managed via Maven (https://maven.apache.org/).

### Configuration

1. **Database Configuration**

   Configure your database connection in `hibernate.cfg.xml`
   
   Configure SessionManager, SessionFactory in HibernateUtil

3. **Maven compile**

   Run and build: mvn clean install
   
4. **Github CI/CD**

   Install JDK11
   
   Run and build: mvn clean install

### Description

   The project does not use Dto to create, update or get entity. Be careful will lazy initialize error when remove ToString.Exclude in Entity
   
### Usage

   Describe how to use the application or provide any relevant instructions here.

### Contributing

   Feel free to contribute to this project by submitting pull requests.

### License
   
   This project is licensed under the [MIT License](LICENSE).
