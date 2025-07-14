# 🏦 BankApp-API

A backend application built using **Java Spring Boot**, **JPA**, and **MySQL** that simulates a simple banking system with customer registration, fund transfer, transaction statements, and payee management.

---

## 📌 Features

1. **User Registration & Account Creation**
   - Accepts user details: `name`, `age`, `gender`, `email`, `phone`
   - Validates age must be greater than 18
   - Generates a 10-digit account number
   - Assigns ₹10,000 as the opening balance

2. **Fund Transfer**
   - Transfers funds from one account to another
   - Validates sufficient balance
   - Saves each transaction in the database for history

3. **Account Statement**
   - Returns debits and credits for a given account, month, and year

4. **Payee Management (CRUD)**
   - Each customer can maintain up to 5 payees
   - Add, update, patch, delete, and fetch payee list

---

## 🚀 API Endpoints

### 🔹 Registration & Account Creation
- `POST /api/bank/register`

### 🔹 Fund Transfer
- `POST /api/bank/transfer`

### 🔹 Statement
- `POST /api/bank/statement`

### 🔹 Payee Management
- `POST /api/payee/add`  
- `PUT /api/payee/update`  
- `PATCH /api/payee/patch/{id}`  
- `DELETE /api/payee/delete/{id}`  
- `GET /api/payee/customer/{accountNumber}`


