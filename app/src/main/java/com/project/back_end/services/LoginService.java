package com.project.back_end.services;

import org.springframework.stereotype.Service;

import com.project.back_end.exception.InvalidAuthenticationException;
import com.project.back_end.exception.InvalidTokenException;
import com.project.back_end.models.Role;
import com.project.back_end.models.UserAccount;
import com.project.back_end.repo.UserAccountRepository;

import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService {
    // 1. **@Service Annotation**
    // The @Service annotation marks this class as a service component in Spring.
    // This allows Spring to automatically detect it through component scanning
    // and manage its lifecycle, enabling it to be injected into controllers or
    // other services using @Autowired or constructor injection.
    private final TokenService tokenService;
    private final UserAccountRepository userAccountRepository;

    // 2. **Constructor Injection for Dependencies**
    // The constructor injects all required dependencies (TokenService,
    // Repositories, and other Services). This approach promotes loose coupling,
    // improves testability,
    // and ensures that all required dependencies are provided at object creation
    // time.

    public String getRawToken(String authorization) {
        if (!authorization.startsWith("Bearer ")) {
            throw new SecurityException("Authorization header must start with Bearer");
        }

        return authorization.substring(7);
    }

    public boolean validateToken(String token, Role expectedRole) {
        if (!tokenService.validateToken(token, expectedRole)) {
            throw new InvalidTokenException(token);
        }

        return true;
    }
    // 3. **validateToken Method**
    // This method checks if the provided JWT token is valid for a specific user. It
    // uses the TokenService to perform the validation.
    // If the token is invalid or expired, it returns a 401 Unauthorized response
    // with an appropriate error message. This ensures security by preventing
    // unauthorized access to protected resources.

    public String validateAdmin(String email, String password) {
        UserAccount userAccount = userAccountRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new InvalidAuthenticationException("Invalid email or password"));

        if (!userAccount.isActive() || Role.ADMIN != userAccount.getRole()) {
            throw new InvalidAuthenticationException("Invalid role or account is inactive");
        }

        return tokenService.generateToken(email, Role.ADMIN);
    }
    // 4. **validateAdmin Method**
    // This method validates the login credentials for an admin user.
    // - It first searches the admin repository using the provided username.
    // - If an admin is found, it checks if the password matches.
    // - If the password is correct, it generates and returns a JWT token (using the
    // admin’s username) with a 200 OK status.
    // - If the password is incorrect, it returns a 401 Unauthorized status with an
    // error message.
    // - If no admin is found, it also returns a 401 Unauthorized.
    // - If any unexpected error occurs during the process, a 500 Internal Server
    // Error response is returned.
    // This method ensures that only valid admin users can access secured parts of
    // the system.

    // 5. **filterDoctor Method**
    // This method provides filtering functionality for doctors based on name,
    // specialty, and available time slots.
    // - It supports various combinations of the three filters.
    // - If none of the filters are provided, it returns all available doctors.
    // This flexible filtering mechanism allows the frontend or consumers of the API
    // to search and narrow down doctors based on user criteria.

    // 6. **validateAppointment Method**
    // This method validates if the requested appointment time for a doctor is
    // available.
    // - It first checks if the doctor exists in the repository.
    // - Then, it retrieves the list of available time slots for the doctor on the
    // specified date.
    // - It compares the requested appointment time with the start times of these
    // slots.
    // - If a match is found, it returns 1 (valid appointment time).
    // - If no matching time slot is found, it returns 0 (invalid).
    // - If the doctor doesn’t exist, it returns -1.
    // This logic prevents overlapping or invalid appointment bookings.

    // 7. **validatePatient Method**
    // This method checks whether a patient with the same email or phone number
    // already exists in the system.
    // - If a match is found, it returns false (indicating the patient is not valid
    // for new registration).
    // - If no match is found, it returns true.
    // This helps enforce uniqueness constraints on patient records and prevent
    // duplicate entries.

    // 8. **validatePatientLogin Method**
    // This method handles login validation for patient users.
    // - It looks up the patient by email.
    // - If found, it checks whether the provided password matches the stored one.
    // - On successful validation, it generates a JWT token and returns it with a
    // 200 OK status.
    // - If the password is incorrect or the patient doesn't exist, it returns a 401
    // Unauthorized with a relevant error.
    // - If an exception occurs, it returns a 500 Internal Server Error.
    // This method ensures only legitimate patients can log in and access their data
    // securely.

    // 9. **filterPatient Method**
    // This method filters a patient's appointment history based on condition and
    // doctor name.
    // - It extracts the email from the JWT token to identify the patient.
    // - Depending on which filters (condition, doctor name) are provided, it
    // delegates the filtering logic to PatientService.
    // - If no filters are provided, it retrieves all appointments for the patient.
    // This flexible method supports patient-specific querying and enhances user
    // experience on the client side.

}
