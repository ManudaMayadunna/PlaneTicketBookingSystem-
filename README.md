# ✈️ Plane Ticket Booking System

A Java-based console application for booking plane tickets. Designed for learning and practice, this project demonstrates object-oriented programming, file handling, user input validation, and basic ticketing workflows.

## Table of Contents

- [Features](#features)  
- [Technologies](#technologies)  
- [Architecture](#architecture)  
- [Getting Started](#getting-started)  
  - [Prerequisites](#prerequisites)  
  - [Setup & Running](#setup--running)  
- [Usage](#usage)  
- [Project Structure](#project-structure)  
- [Contributing](#contributing)  
- [License](#license)  

---

## Features

- Flight search by destination, date, and time  
- Booking and cancelation of tickets  
- Passenger management  
- Data persistence using Java serialization or text files  
- Validation of user input and business rules

---

## Technologies

- Java 8+  
- Standard libraries for I/O, collections, and serialization  
- Console-based user interface

---

## Architecture

The application follows a layered, object-oriented design:

- `model/` – Plain Old Java Objects (POJOs) like `Flight`, `Ticket`, `Passenger`  
- `service/` – Business logic for managing flights and bookings  
- `storage/` – File I/O or serialization logic  
- `ui/` – Console input/output handling  
- `Main.java` – Application entry point

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or above installed  
- (Optional) An IDE like IntelliJ IDEA or Eclipse

### Setup & Running

1. Clone the repository:  
   git clone https://github.com/ManudaMayadunna/PlaneTicketBookingSystem-.git
   cd PlaneTicketBookingSystem-
   
2. Compile the project:
  javac -d bin src/**/*.java

3. Run the application:
  java -cp bin ui.Main



## Usage

Welcome to the Plane Ticket Booking System!

1. Search Flights
2. Book Ticket
3. Cancel Ticket
4. View Bookings
5. Exit
