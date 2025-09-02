# Smart Clinic Management System

A web-based application for managing clinic operations, including appointments, patient records, and administrative tasks. Built with Java, Spring Boot, and SQL.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

### ✅ Appointments Management

- Patients can book, reschedule, and cancel appointments online.
- Doctors can view and manage their schedules.

### ✅ Patient Records Management

- Store and manage patient medical histories and prescriptions.

### ✅ Administrative Tasks

- Manage clinic staff, schedules, and reports.

## Tech Stack

- Java 17+
- Spring Boot
- SQL (MySQL)
- NoSQL (MongoDB)
- Docker
- Maven
- HTML/CSS/JavaScript

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Docker (for containerized deployment)

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/peemtanapat/java-database-capstone.git
   cd java-database-capstone
   ```
2. Build the project:
   ```bash
   cd app
   ./mvnw clean install
   ```
3. Run with Docker:
   ```bash
   docker-compose up --build
   ```
4. Access the app at `http://localhost:8080`

## Usage

- Patients and doctors can log in to manage appointments and records.
- Admins can access dashboards for reports and management.

## Contributing

Contributions are welcome! Please open issues or submit pull requests for improvements.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a pull request

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
