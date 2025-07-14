
I’ve built a simple E-commerce REST API project using Java, Spring Boot, JPA, Maven, and MySQL. The application has four main functionalities: user registration, login, saving products, and searching products by name.
________________________________________
### User Registration
In the registration API, I used input validation to ensure the user name, email, and password are not null. I also check that the email is unique to prevent duplicate registrations. The data is saved in the users table using JPA.
________________________________________
### Login
The login API checks if the email and password match a user in the database. I kept it simple and didn’t use Spring Security since it’s just for demo/testing.
________________________________________
### Save Product
I created a product save API to allow inserting products into the database. It uses a DTO to receive name and price, with validation to ensure fields are not null.
________________________________________
### Product Search
The search API allows users to search for products by name using a partial match with the LIKE keyword. I also implemented pagination, so the response returns 5 products per page. This uses Spring Data JPA’s Pageable and Page<Product> features.
________________________________________

Test Flow Example
For example, I can first use the save API to add a product like “iPhone 15”, and then I can search for it using /search?name=iphone, which will return matching results with pagination. 

---

# @FeignClient

The /api/orders/buy API allows a customer to purchase one or more products by providing:
•	Customer ID
•	Account number (from BankApp)
•	List of products (productId + quantity)
It then:
1.	Validates customer info
2.	Validates product stock
3.	Deducts inventory
4.	Calls BankApp to transfer payment
5.	Stores order(s) in the database
6.	Returns structured success or error response 

---
---

🎯 Project: E-Commerce REST API
🔧 Tech Stack
Java 21

Spring Boot

Spring Data JPA

MySQL

Maven

🛠️ Key Features & APIs
1. ✅ User Registration
POST /api/auth/register

Validates:

name, email, password are not null.

Email must be unique (no duplicate user).

Stores user data in users table.

2. 🔐 User Login
POST /api/auth/login

Checks email & password match.

Returns success or failure message.

No security (for simplicity).

3. 💾 Save Product
POST /api/products/save

Saves a product (name, price) into DB.

Uses validation via @Valid.

4. 🔍 Product Search
GET /api/products/search?name=phone&page=0

Supports:

Partial search using LIKE query.

Pagination: 5 products per page.

Uses Spring Data Pageable & Page<Product>.



