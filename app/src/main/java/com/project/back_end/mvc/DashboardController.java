package com.project.back_end.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.project.back_end.DTO.DoctorDTO;

import com.project.back_end.models.Role;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final LoginService loginService;
    private final DoctorService doctorService;

    @GetMapping("/adminDashboard/{token}")
    public String getAdminDashboard(
            @PathVariable String token,
            @RequestParam(required = false) String name,
            Model model) {
        try {
            loginService.validateToken(token, Role.ADMIN);

            // Doctor List
            List<DoctorDTO> doctors = doctorService.getDoctors();
            if (name != null && !name.trim().isEmpty()) {
                doctors = doctors.stream()
                        .filter(d -> d.getName().toLowerCase().contains(name.toLowerCase()))
                        .toList();
            }
            model.addAttribute("doctorList", doctors);

            return "admin/adminDashboard";
        } catch (Exception e) {
            log.error("Invalid token for admin dashboard: {}", e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("/doctorDashboard/{token}")
    public String getDoctorDashboard(@PathVariable String token) {
        try {
            loginService.validateToken(token, Role.DOCTOR);
            return "doctor/doctorDashboard";
        } catch (Exception e) {
            log.error("Invalid token for doctor dashboard: {}", e.getMessage());
            return "redirect:/";
        }
    }

    // 1. Set Up the MVC Controller Class:
    // - Annotate the class with `@Controller` to indicate that it serves as an MVC
    // controller returning view names (not JSON).
    // - This class handles routing to admin and doctor dashboard pages based on
    // token validation.

    // 2. Autowire the Shared Service:
    // - Inject the common `Service` class, which provides the token validation
    // logic used to authorize access to dashboards.

    // 3. Define the `adminDashboard` Method:
    // - Handles HTTP GET requests to `/adminDashboard/{token}`.
    // - Accepts an admin's token as a path variable.
    // - Validates the token using the shared service for the `"admin"` role.
    // - If the token is valid (i.e., no errors returned), forwards the user to the
    // `"admin/adminDashboard"` view.
    // - If invalid, redirects to the root URL, likely the login or home page.

    // 4. Define the `doctorDashboard` Method:
    // - Handles HTTP GET requests to `/doctorDashboard/{token}`.
    // - Accepts a doctor's token as a path variable.
    // - Validates the token using the shared service for the `"doctor"` role.
    // - If the token is valid, forwards the user to the `"doctor/doctorDashboard"`
    // view.
    // - If the token is invalid, redirects to the root URL.

}
