-- User accounts
INSERT INTO user_account (email, password, role) VALUES
('dr.adams@example.com', 'pass12345', 'DOCTOR'),          -- id=1
('jane.doe@example.com', 'passJane1', 'PATIENT'),         -- id=2
('dr.brown@example.com', 'passDrB22', 'DOCTOR'),          -- id=3
('john.smith@example.com', 'passJohn22', 'PATIENT'),      -- id=4
('dr.green@example.com', 'passDrG33', 'DOCTOR'),          -- id=5
('sara.lee@example.com', 'passSara33', 'PATIENT'),        -- id=6
('paul.walker@example.com', 'passPaul44', 'PATIENT');     -- id=7

-- Doctors
INSERT INTO doctor (user_account_id, name, license_number, specialty) VALUES
(1, 'Dr. Emily Adams', '555-101-2020', 'Cardiologist'),   -- id=1
(3, 'Dr. Michael Brown', '555-202-3030', 'Dermatologist'),-- id=2
(5, 'Dr. Sarah Green', '555-303-4040', 'Neurologist');    -- id=3

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

-- Appointments (spread across doctors and years)
INSERT INTO appointment (schedule_start, schedule_end, doctor_id, patient_id) VALUES
-- Dr. Adams (Cardiologist)
('2025-09-25 09:00:00.000000', '2025-09-25 10:00:00.000000', 1, 1),
('2026-09-25 09:00:00.000000', '2026-09-25 10:00:00.000000', 1, 1),
('2025-10-05 09:00:00.000000', '2025-10-05 09:30:00.000000', 1, 2),
('2025-11-10 13:00:00.000000', '2025-11-10 13:30:00.000000', 1, 3),

-- Dr. Brown (Dermatologist)
('2025-08-15 10:00:00.000000', '2025-08-15 10:30:00.000000', 2, 2),
('2025-08-20 10:30:00.000000', '2025-08-20 11:00:00.000000', 2, 3),
('2026-07-01 10:00:00.000000', '2026-07-01 10:30:00.000000', 2, 4),

-- Dr. Green (Neurologist)
('2025-09-01 15:00:00.000000', '2025-09-01 15:30:00.000000', 3, 1),
('2025-09-02 15:30:00.000000', '2025-09-02 16:00:00.000000', 3, 4),
('2026-09-10 15:00:00.000000', '2026-09-10 15:30:00.000000', 3, 2);