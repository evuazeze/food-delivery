# Byteworks Food Delivery API - Making Meal ordering easy

## Setup Instructions

1. Clone this repository and open in your favourite IDE.

2. Create an `application.properties` file under the resources directory.

    Copy in this snippet and replace `username` and `password` with yours

    Note that the application uses PostgreSQL

    Create a `fooddelivery` schema in your Postgres database.


   ```
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL94Dialect
   spring.jpa.hibernate.ddl-auto=create-drop
   spring.jpa.show-sql=true
   
   spring.datasource.url=jdbc:postgresql://localhost:5432/fooddelivery
   spring.datasource.username=<username>
   spring.datasource.password=<password>
   spring.datasource.driver-class-name=org.postgresql.Driver
   ```
   
   You do not need to create the database tables as hibernate does that.
   
   Run the queries below to get some meals to work with.
   
       
   ```
   insert into meal (id, name, price) values (1, 'Jollof Rice', 1500);
   
   insert into meal (id, name, price) values (2, 'Fried Rice', 1200);
   
   insert into meal (id, name, price) values (3, 'Vegetable Soup', 800);
   
   insert into meal (id, name, price) values (4, 'Pepper Soup', 1300);
   ```

## API Endpoints

<table>
<tr><th>HTTP VERB</th><th>ENDPOINTS</th><th>DESCRIPTION</th></tr>
<tr><td>POST</td><td>/api/v1/auth/signup</td><td>Creates user account</td></tr>
<tr><td>POST</td><td>/api/v1/auth/login/</td><td>Login to server</td></tr>
<tr><td>POST</td><td>/api/v1/order</td><td>Order meal from vendor</td></tr>
</table>


## Request Format

    POST /api/v1/auth/signup
    
    {
       	"username": "evuazeze",
       	"password": "1234",
       	"email": "evuazeze.emmanuel@gmail.com"
    }
       
    POST /api/v1/order
    
    {
       "userId": 1,
       "mealId": 1,
       "officeDelivery": false,
       "cardPayment": false
    }
       
Note: Authorization Header type should `Basic Auth` with username and password when logging in.
