# 🏥 Smart Clinic Management System - User Stories

## 🛠️ Admin

1. As an admin, I want to **log into the portal with my username and password**, so that I can manage the platform securely.
2. As an admin, I want to **log out of the portal**, so that system access remains protected.
3. As an admin, I want to **add doctors to the portal**, so that they can provide services to patients.
4. As an admin, I want to **delete a doctor's profile from the portal**, so that outdated or invalid records are removed.
5. As an admin, I want to **run a stored procedure in MySQL CLI to get the number of appointments per month**, so that I can track usage statistics.

---

## 👤 Patient

1. As a patient, I want to **view a list of doctors without logging in**, so that I can explore my options before registering.
2. As a patient, I want to **sign up using my email and password**, so that I can book appointments.
3. As a patient, I want to **log into the portal**, so that I can manage my bookings.
4. As a patient, I want to **log out of the portal**, so that my account remains secure.
5. As a patient, I want to **book an hour-long appointment with a doctor**, so that I can receive consultation.
6. As a patient, I want to **view my upcoming appointments**, so that I can prepare accordingly.
7. As a patient, I want to **cancel or reschedule my appointment** at least 24 hours in advance, so that I can manage unexpected schedule changes without affecting clinic operations.

---

## 🩺 Doctor

1. As a doctor, I want to **log into the portal**, so that I can manage my appointments.
2. As a doctor, I want to **log out of the portal**, so that my data is protected.
3. As a doctor, I want to **view my appointment calendar**, so that I can stay organized.
4. As a doctor, I want to **mark my unavailability**, so that patients can only book available slots.
5. As a doctor, I want to **update my profile with specialty and contact information**, so that patients have accurate details.
6. As a doctor, I want to **view patient details for upcoming appointments**, so that I can be prepared.

---

---

# Full-version User Stories

## 🛠️ Admin

### Story 1: Admin Login

**Title:**  
As an admin, I want to log into the portal with my username and password, so that I can manage the platform securely.

**Acceptance Criteria:**

1. Admin must enter valid username and password.
2. Incorrect credentials should show an error message.
3. Successful login redirects to the admin dashboard.

**Priority:** High  
**Story Points:** 3  
**Notes:**

- Support for password encryption.
- Consider multi-factor authentication in future.

---

### Story 2: Admin Logout

**Title:**  
As an admin, I want to log out of the portal, so that system access remains protected.

**Acceptance Criteria:**

1. Admin can log out from any page.
2. After logout, the session is invalidated.
3. Admin is redirected to the login page.

**Priority:** High  
**Story Points:** 2  
**Notes:**

- Auto-logout after inactivity should be considered.

---

### Story 3: Add Doctor

**Title:**  
As an admin, I want to add doctors to the portal, so that they can provide services to patients.

**Acceptance Criteria:**

1. Admin can enter doctor details (name, specialty, contact info: address, email, phoneNo).
2. System validates required fields before submission.
3. Doctor profile is visible to patients after addition.

**Priority:** High  
**Story Points:** 5  
**Notes:**

- Handle duplicate email, name gracefully.

---

### Story 4: Delete Doctor

**Title:**  
As an admin, I want to delete a doctor's profile from the portal, so that outdated or invalid records are removed.

**Acceptance Criteria:**

1. Admin can select a doctor profile to delete.
2. System confirms before deletion.
3. Doctor’s data is removed or archived.

**Priority:** Medium  
**Story Points:** 3  
**Notes:**

- Consider "soft delete" to avoid accidental data loss e.g., existing patient appointments.

---

### Story 5: Appointment Stats

**Title:**  
As an admin, I want to run a stored procedure in MySQL CLI to get the number of appointments per month, so that I can track usage statistics.

**Acceptance Criteria:**

1. Procedure must return appointment count per month.
2. Results are accurate and reflect latest data.
3. Admin can view statistics in a report format.

**Priority:** Medium  
**Story Points:** 5  
**Notes:**

- Later, provide this as a UI dashboard instead of CLI only.

---

## 👤 Patient

### Story 1: Browse Doctors

**Title:**  
As a patient, I want to view a list of doctors without logging in, so that I can explore my options before registering.

**Acceptance Criteria:**

1. Patients can view doctor names, specialty, and availability.
2. No login is required to browse doctors.
3. System prevents booking without registration.

**Priority:** High  
**Story Points:** 3  
**Notes:**

- Prevent exposure of sensitive doctor information.
- Pagination may be needed if doctor list is large.

---

### Story 2: Patient Signup

**Title:**  
As a patient, I want to sign up using my email and password, so that I can book appointments.

**Acceptance Criteria:**

1. Patient must provide unique email.
2. Password must follow security standards.
3. Signup confirmation email is sent.

**Priority:** High  
**Story Points:** 5  
**Notes:**

- Support social login in future (Google/Facebook).

---

### Story 3: Patient Login

**Title:**  
As a patient, I want to log into the portal, so that I can manage my bookings.

**Acceptance Criteria:**

1. Patient must enter valid credentials.
2. Invalid login should show error message.
3. Successful login redirects to patient dashboard.

**Priority:** High  
**Story Points:** 3  
**Notes:**

- (Future) Add "forgot password" option.

---

### Story 4: Patient Logout

**Title:**  
As a patient, I want to log out of the portal, so that my account remains secure.

**Acceptance Criteria:**

1. Patient can log out from any page.
2. Session is terminated after logout.
3. Patient is redirected to login page.

**Priority:** High  
**Story Points:** 2  
**Notes:**

- Auto-logout after inactivity recommended.

---

### Story 5: Book Appointment

**Title:**  
As a patient, I want to book an hour-long appointment with a doctor, so that I can receive consultation.

**Acceptance Criteria:**

1. Appointment requires selecting doctor, date, and time.
2. System prevents double booking of slots (ensures availability before booking).
3. Confirmation email/SMS is sent.

**Priority:** High  
**Story Points:** 8  
**Notes:**

- Email/SMS confirmation may be added.

---

### Story 6: View Upcoming Appointments

**Title:**  
As a patient, I want to view my upcoming appointments, so that I can prepare accordingly.

**Acceptance Criteria:**

1. Patient dashboard shows list of upcoming appointments.
2. Appointment details include doctor, date, and time.
3. Only future appointments are shown.

**Priority:** High  
**Story Points:** 3  
**Notes:**

---

### Story 7: Cancel/Reschedule my Upcoming Appointments

**Title:**  
As a patient, I want to cancel or reschedule my appointment at least 24 hours in advance, so that I can manage unexpected schedule changes without affecting clinic operations.

**Acceptance Criteria:**

1. Allow patients to cancel or reschedule only if the appointment is more than 24 hours away.
2. Block cancellation/reschedule for appointments within the next 24 hours.
3. Send confirmation notification to patient and doctor after changes.

**Priority:** High  
**Story Points:** 5  
**Notes:**

- This reduces last-minute cancellations and improves doctor time management.

---

## 🩺 Doctor

### Story 1: Doctor Login

**Title:**  
As a doctor, I want to log into the portal, so that I can manage my appointments.

**Acceptance Criteria:**

1. Doctor enters valid credentials.
2. Invalid login shows error message.
3. Successful login redirects to doctor dashboard.

**Priority:** High  
**Story Points:** 3  
**Notes:**

- Multi-factor authentication optional.

---

### Story 2: Doctor Logout

**Title:**  
As a doctor, I want to log out of the portal, so that my data is protected.

**Acceptance Criteria:**

1. Doctor can log out from any page.
2. Session is invalidated after logout.
3. Redirects to login page.

**Priority:** High  
**Story Points:** 2  
**Notes:**

- Add inactivity timeout in future.

---

### Story 3: Appointment Calendar

**Title:**  
As a doctor, I want to view my appointment calendar, so that I can stay organized.

**Acceptance Criteria:**

1. Doctor dashboard shows upcoming appointments.
2. Calendar view is filterable by day/week/month.
3. Appointment details are clickable for full info.

**Priority:** High  
**Story Points:** 5  
**Notes:**

- Consider calendar sync with Google/Outlook.

---

### Story 4: Mark Unavailability

**Title:**  
As a doctor, I want to mark my unavailability, so that patients can only book available slots.

**Acceptance Criteria:**

1. Doctor can mark unavailable days/times.
2. Patients cannot book during unavailable slots.
3. System shows updated availability instantly.

**Priority:** High  
**Story Points:** 5  
**Notes:**

- Recurring unavailability should be supported. (e.g., every Friday).

---

### Story 5: Update Profile

**Title:**  
As a doctor, I want to update my profile with specialty and contact information, so that patients have accurate details.

**Acceptance Criteria:**

1. Editable fields include specialty, phone, and email.
2. Validation on required fields.
3. Updated data reflects instantly.

**Priority:** Medium  
**Story Points:** 2  
**Notes:**

- Admin approval may be required for changes.

---

### Story 6: View Patient Details

**Title:**  
As a doctor, I want to view patient details for upcoming appointments, so that I can be prepared.

**Acceptance Criteria:**

1. Doctor can access patient details (name, medical history, notes).
2. Only patients with booked appointments are visible.
3. Data must comply with privacy/security standards.

**Priority:** High  
**Story Points:** 5  
**Notes:**

- Sensitive data should follow HIPAA compliance (if applicable).

---
