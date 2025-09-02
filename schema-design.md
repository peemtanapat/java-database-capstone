# 🏥 Smart Clinic Management System - Database Schema Design

## SQL Database Design:

### Common Fields (from BaseModel)

- id BIGINT Auto Increment (PK)
- created_at DATETIME
- updated_at DATETIME

### UserAccount Table

- id BIGINT Auto Increment (PK)
- email VARCHAR (UNIQUE, NOT NULL)
- password VARCHAR (NOT NULL)
- role ENUM('PATIENT', 'DOCTOR', 'ADMIN') (NOT NULL)
- is_active BOOLEAN DEFAULT TRUE
- last_login DATETIME
- created_at DATETIME
- updated_at DATETIME

### Doctor Table

- id BIGINT Auto Increment (PK)
- user_account_id BIGINT (FK → UserAccount)
- name VARCHAR(100) (NOT NULL)
- specialty VARCHAR (NOT NULL)
- department VARCHAR
- phone_number VARCHAR (NOT NULL, format: ^(?:\+66|0)(6|8|9|2)\d{7,8}$)
- created_at DATETIME
- updated_at DATETIME

#### Doctor Available Times Table (doctor_available_times)

- doctor_id BIGINT (FK → Doctor)
- available_time TIME

### Admin Table

- id BIGINT Auto Increment (PK)
- user_account_id BIGINT (FK → UserAccount)
- name VARCHAR(100) (NOT NULL)
- permissions_level VARCHAR
- created_at DATETIME
- updated_at DATETIME

### Patient Table

- id BIGINT Auto Increment (PK)
- user_account_id BIGINT (FK → UserAccount)
- name VARCHAR(100) (NOT NULL)
- dob DATE (NOT NULL)
- address VARCHAR(256) (NOT NULL)
- phone_number VARCHAR (NOT NULL, format: ^(?:\+66|0)(6|8|9|2)\d{7,8}$)
- created_at DATETIME
- updated_at DATETIME

### Appointment Table

- id BIGINT Auto Increment (PK)
- patient_id BIGINT (FK → Patient)
- doctor_id BIGINT (FK → Doctor)
- schedule_start DATETIME (NOT NULL, FUTURE)
- schedule_end DATETIME (NOT NULL, FUTURE)
- is_active BOOLEAN DEFAULT TRUE
- department VARCHAR
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
