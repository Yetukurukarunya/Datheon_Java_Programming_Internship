
# Student Grade Management System

A Java console application developed as part of the Datheon
Java Programming Internship.

## Overview

The application helps teachers manage student records, record
course marks, calculate academic statistics, and generate report cards.

## Features

- Register students with unique IDs
- Record and update course marks
- Validate student details and scores
- Calculate student average marks and GPA
- Calculate class average and median of student averages
- Generate formatted report cards
- Handle invalid menu input

## Technologies Used

- Java JDK 17+
- Object-Oriented Programming
- ArrayList and HashMap
- Exception Handling
- Java Collections Framework
- String formatting

## Project Structure

    Task-1-Student-Grade-Management/
    ├── Student.java
    ├── GradeManager.java
    └── README.md

## Requirements

Install JDK 17 or newer and configure Java in your PATH.

## How to Run

Open a terminal in the project directory.

Compile:

    javac Student.java GradeManager.java

Run:

    java GradeManager

## GPA Calculation

The application uses a project-defined 10-point GPA scale:

    GPA = Average Marks / 10

This is a simplified convention and may differ from
institution-specific grading systems.

## Class Statistics

The class average and median are calculated using the
average marks of students who have at least one recorded grade.

## Current Limitations

- Student records are stored in memory and are not persistent.
- The application is a console-based prototype.
- Authentication and database storage are not implemented.

## Internship Project

Task 1: Student Grade Management System  
Program: Java Programming Internship  
Organization: Datheon – AI Systems and Automation Studio
