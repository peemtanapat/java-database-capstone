# 🏥 Smart Clinic Management System - Database Schema Design

## SQL Database Design:

Including 6 tables (Patient, Doctor, Appointment, Admin, UserAccount, Department)

## Appointment Table

- patient_id BIGINT (PK,FK -> Patient)
- doctor_id BIGINT (PK,FK -> Doctor)
- schedule_start DATETIME
- schedule_end DATETIME
- is_active boolean
- department_id (FK -> Department)
- updated_at DATETIME
- created_at DATETIME

## 👤 Patient Table

- id BIGINT Auto Increment (PK)
- user_account_id (FK → UserAccount)
- first_name VARCHAR
- last_name VARCHAR
- dob date
- phone_number VARCHAR
- emergency_contact VARCHAR
- medical_record_number
- updated_at DATETIME
- created_at DATETIME

## 🩺 Doctor Table

- id BIGINT Auto Increment (PK)
- user_account_id (FK → UserAccount)
- department_id (FK -> Department)
- license_number VARCHAR
- specialization VARCHAR
- created_at DATETIME
- updated_at DATETIME

## Admin Table

- id BIGINT Auto Increment (PK)
- user_account_id (FK → UserAccount)
- first_name, last_name
- department_id (FK -> Department)
- permissions_level

## 🧾 UserAccount Table

- id BIGINT Auto Increment (PK)
- role (OPTION: 'patient', 'doctor', 'admin')
- email VARCHAR (UNIQUE)
- password VARCHAR
- is_active boolean
- last_login DATETIME
- created_at DATETIME
- updated_at DATETIME

## 💼 Department Table:

- id BIGINT Auto Increment (PK)
- department_name ('Cardiology', 'Pediatrics', 'Radiology', etc.)
- description
- location VARCHAR
- floor VARCHAR
- created_at DATETIME
- updated_at DATETIME

# NoSQL Database Design:

## Prescription Collection:

- id BIGINT Auto Increment (PK)
- appointment_id (FK -> Appointment)
- patient_name VARCHAR
- medication VARCHAR
- refillCount INT
- dosage VARCHAR
- doctor_notes VARCHAR
- created_at DATETIME
- updated_at DATETIME

## Log Collection:

- id (PK)
- class ('Patient', 'Doctor', 'Appointment', etc.)
- function VARCHAR
- message VARCHAR
- type ('ERROR', 'INFO', 'DEBUG')
- created_at DATETIME
