# Bank Account & ATM Simulation

A console-based Java application that simulates basic ATM operations using object-oriented programming concepts.

## Features

- Create savings and checking accounts
- PIN authentication
- Check account balance
- Deposit and withdraw money
- Savings account interest calculation
- Checking account overdraft support
- Transaction history with timestamps
- Customer details display
- Exception handling for invalid PINs and insufficient funds
- Input validation

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework
- Exception Handling
- Java Date and Time API

## Project Structure

```text
Task-2-Bank-ATM-Simulation/
├── Account.java
├── SavingsAccount.java
├── CheckingAccount.java
├── Customer.java
├── ATM.java
├── InsufficientFundsException.java
├── InvalidPINException.java
└── README.md
```

## How to Run

Ensure that the Java Development Kit (JDK) is installed.

Compile the source files:

```bash
javac InsufficientFundsException.java InvalidPINException.java Account.java SavingsAccount.java CheckingAccount.java Customer.java ATM.java
```

Run the application:

```bash
java ATM
```

## Account Types

**Savings Account:** Supports deposits, withdrawals within the available balance, and interest calculation.

**Checking Account:** Supports deposits and withdrawals subject to a configurable overdraft limit.

## Learning Outcomes

This project demonstrates inheritance, abstraction, polymorphism, encapsulation, custom exceptions, collections, and input validation in Java.

## Limitations

This is an educational simulation, not a production banking system. Account information is stored in memory and is not persisted after the program exits. PINs are not encrypted.

## Author

Java Programming Internship Project — Datheon AI Systems and Automation Studio
