# Assignment - FPT ORM

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

## Project Setup

### Prerequisites

- JDK 11
- Maven 3.6.3 or above
- Hibernate 5.4 or above
- MySQL 8.0 or above (or any other preferred database)

### Configuration

1. **Database Configuration**

   Configure your database connection in `hibernate.cfg.xml`
   
   Configure SessionManager, SessionFactory in HibernateUtil

3. **Maven compile**

   Run and build: mvn clean install
   
4. **Github ci/cd**

   Install JDK11
   Run and build: mvn clean install

### Description

1. **Returning Data**

   The project does not use Dto to create, update or get entity. Be careful will lazy initialize error when remove ToString.Exclude in Entity
   

Feel free to modify the README file as per your specific project requirements. If you need further assistance with the implementation details, let me know!
