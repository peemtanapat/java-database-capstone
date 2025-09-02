-- User accounts
INSERT INTO user_account (email, password, role) VALUES
('dr.adams@example.com', 'pass12345', 'DOCTOR'),          -- id=1
('jane.doe@example.com', 'passJane1', 'PATIENT'),         -- id=2
('dr.brown@example.com', 'passDrB22', 'DOCTOR'),          -- id=3
('john.smith@example.com', 'passJohn22', 'PATIENT'),      -- id=4
('dr.green@example.com', 'passDrG33', 'DOCTOR'),          -- id=5
('sara.lee@example.com', 'passSara33', 'PATIENT'),        -- id=6
('paul.walker@example.com', 'passPaul44', 'PATIENT'),     -- id=7
('admin@example.com', 'admin', 'ADMIN');     -- id=8

-- Doctors
INSERT INTO doctor (user_account_id, name, specialty) VALUES
(1, 'Dr. Emily Adams', 'Cardiologist'),   -- id=1
(3, 'Dr. Michael Brown', 'Dermatologist'),-- id=2
(5, 'Dr. Sarah Green', 'Neurologist');    -- id=3

-- Available times
INSERT INTO doctor_available_times (doctor_id, available_time) VALUES
(1, '09:00:00.000000'),
(1, '13:00:00.000000'),
(2, '10:00:00.000000'),
(3, '15:00:00.000000');

-- Patients
INSERT INTO patient (user_account_id, name, dob, address, phone_number) VALUES
(2, 'Jane Doe', '1997-11-17', '101 Oak St, Cityville', '0939452459'),   -- id=1
(4, 'John Smith', '1990-05-12', '202 Pine St, Cityville', '0987564321'),-- id=2
(6, 'Sara Lee', '1988-02-25', '303 Maple St, Townsville', '0912345678'),-- id=3
(7, 'Paul Walker', '1985-07-30', '404 Birch St, Townsville', '0945678901'); -- id=4

-- Appointments (extended with multiple appointments per day per doctor and same-day appointments across doctors)
INSERT INTO appointment (schedule_start, schedule_end, doctor_id, patient_id) VALUES
-- Dr. Adams (Cardiologist) - Original appointments
('2025-09-25 09:00:00.000000', '2025-09-25 10:00:00.000000', 1, 1),
('2026-09-25 09:00:00.000000', '2026-09-25 10:00:00.000000', 1, 1),
('2025-10-05 09:00:00.000000', '2025-10-05 09:30:00.000000', 1, 2),
('2025-11-10 13:00:00.000000', '2025-11-10 13:30:00.000000', 1, 3),

-- Dr. Adams - Additional appointments on same days
('2025-09-25 11:00:00.000000', '2025-09-25 11:30:00.000000', 1, 3), -- Same day as first appointment
('2025-09-25 13:00:00.000000', '2025-09-25 13:30:00.000000', 1, 4), -- Same day as first appointment
('2025-10-05 10:00:00.000000', '2025-10-05 10:30:00.000000', 1, 4), -- Same day as existing appointment
('2025-10-05 14:00:00.000000', '2025-10-05 14:30:00.000000', 1, 1), -- Same day as existing appointment
('2025-11-10 14:00:00.000000', '2025-11-10 14:30:00.000000', 1, 2), -- Same day as existing appointment

-- Dr. Brown (Dermatologist) - Original appointments
('2025-08-15 10:00:00.000000', '2025-08-15 10:30:00.000000', 2, 2),
('2025-08-20 10:30:00.000000', '2025-08-20 11:00:00.000000', 2, 3),
('2026-07-01 10:00:00.000000', '2026-07-01 10:30:00.000000', 2, 4),

-- Dr. Brown - Additional appointments on same days
('2025-08-15 11:00:00.000000', '2025-08-15 11:30:00.000000', 2, 1), -- Same day as first appointment
('2025-08-15 14:00:00.000000', '2025-08-15 14:30:00.000000', 2, 4), -- Same day as first appointment
('2025-08-20 12:00:00.000000', '2025-08-20 12:30:00.000000', 2, 1), -- Same day as existing appointment
('2025-08-20 15:00:00.000000', '2025-08-20 15:30:00.000000', 2, 4), -- Same day as existing appointment

-- Dr. Green (Neurologist) - Original appointments
('2025-09-01 15:00:00.000000', '2025-09-01 15:30:00.000000', 3, 1),
('2025-09-02 15:30:00.000000', '2025-09-02 16:00:00.000000', 3, 4),
('2026-09-10 15:00:00.000000', '2026-09-10 15:30:00.000000', 3, 2),

-- Dr. Green - Additional appointments on same days
('2025-09-01 16:00:00.000000', '2025-09-01 16:30:00.000000', 3, 2), -- Same day as first appointment
('2025-09-01 17:00:00.000000', '2025-09-01 17:30:00.000000', 3, 3), -- Same day as first appointment
('2025-09-02 17:00:00.000000', '2025-09-02 17:30:00.000000', 3, 1), -- Same day as existing appointment
('2025-09-02 18:00:00.000000', '2025-09-02 18:30:00.000000', 3, 3), -- Same day as existing appointment

-- SAME DAY APPOINTMENTS ACROSS MULTIPLE DOCTORS
-- September 25, 2025 - Dr. Adams already has appointments, adding Dr. Brown and Dr. Green
('2025-09-25 10:00:00.000000', '2025-09-25 10:30:00.000000', 2, 2), -- Dr. Brown on same day as Dr. Adams
('2025-09-25 15:00:00.000000', '2025-09-25 15:30:00.000000', 3, 3), -- Dr. Green on same day as Dr. Adams
('2025-09-25 16:00:00.000000', '2025-09-25 16:30:00.000000', 3, 2), -- Dr. Green another appointment same day

-- August 15, 2025 - Dr. Brown already has appointments, adding Dr. Adams and Dr. Green
('2025-08-15 09:00:00.000000', '2025-08-15 09:30:00.000000', 1, 3), -- Dr. Adams on same day as Dr. Brown
('2025-08-15 15:00:00.000000', '2025-08-15 15:30:00.000000', 3, 4), -- Dr. Green on same day as Dr. Brown

-- September 1, 2025 - Dr. Green already has appointments, adding Dr. Adams and Dr. Brown
('2025-09-01 09:00:00.000000', '2025-09-01 09:30:00.000000', 1, 4), -- Dr. Adams on same day as Dr. Green
('2025-09-01 10:00:00.000000', '2025-09-01 10:30:00.000000', 2, 3), -- Dr. Brown on same day as Dr. Green

-- October 5, 2025 - Dr. Adams already has appointments, adding Dr. Brown and Dr. Green
('2025-10-05 11:00:00.000000', '2025-10-05 11:30:00.000000', 2, 1), -- Dr. Brown on same day as Dr. Adams
('2025-10-05 15:00:00.000000', '2025-10-05 15:30:00.000000', 3, 4), -- Dr. Green on same day as Dr. Adams
('2025-10-05 16:00:00.000000', '2025-10-05 16:30:00.000000', 3, 2); -- Dr. Green another appointment same day
