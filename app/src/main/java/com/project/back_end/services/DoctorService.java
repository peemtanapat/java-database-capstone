package com.project.back_end.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.back_end.DTO.DoctorDTO;
import com.project.back_end.DTO.DoctorRequestDTO;
import com.project.back_end.exception.InvalidAuthenticationException;
import com.project.back_end.models.Appointment;
import com.project.back_end.models.Doctor;
import com.project.back_end.models.Role;
import com.project.back_end.models.UserAccount;
import com.project.back_end.repo.UserAccountRepository;
import com.project.back_end.repo.AppointmentRepository;
import com.project.back_end.repo.DoctorRepository;

import jakarta.transaction.Transactional;

@Service
public class DoctorService {

        private final DoctorRepository doctorRepository;
        private final AppointmentRepository appointmentRepository;
        private final TokenService tokenService;
        private final UserAccountRepository userAccountRepository;

        public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository,
                        TokenService tokenService, UserAccountRepository userAccountRepository) {
                this.doctorRepository = doctorRepository;
                this.appointmentRepository = appointmentRepository;
                this.tokenService = tokenService;
                this.userAccountRepository = userAccountRepository;
        }

        @Transactional
        public List<DoctorDTO> getDoctors() {
                List<Doctor> all = doctorRepository.findAll();
                return all.stream()
                                .map(d -> new DoctorDTO(
                                                d.getId(),
                                                d.getName(),
                                                d.getSpecialty(),
                                                d.getPhoneNumber(),
                                                d.getUserAccount().getEmail(),
                                                d.getAvailableTimes()))
                                .toList();
        }

        @Transactional
        public List<LocalTime> getDoctorAvailability(Long doctorId, LocalDate date) {
                Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
                if (doctor == null) {
                        return List.of();
                }
                LocalDateTime start = date.atStartOfDay();
                LocalDateTime end = date.atTime(23, 59, 59);
                List<Appointment> booked = appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(doctorId,
                                start, end);
                List<LocalTime> bookedTimes = booked.stream().map(a -> a.getScheduleStart().toLocalTime()).toList();
                return doctor.getAvailableTimes().stream().filter(time -> !bookedTimes.contains(time)).toList();
        }

        @Transactional
        public int deleteDoctor(Long doctorId) {
                Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
                if (doctor == null) {
                        return -1;
                }
                appointmentRepository.deleteAllByDoctorId(doctorId);
                doctorRepository.delete(doctor);
                return 1;
        }

        @Transactional
        public int updateDoctor(Long doctorId, DoctorRequestDTO request) {
                Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
                if (doctor == null) {
                        return -1;
                }
                doctor.setName(request.getName());
                doctor.setSpecialty(request.getSpecialty());
                List<LocalTime> times = request.getAvailableTimes().stream().map(LocalTime::parse).toList();
                doctor.setAvailableTimes(times);
                doctorRepository.save(doctor);
                return 1;
        }

        public DoctorDTO saveDoctor(DoctorRequestDTO request) {
                // Check if doctor with same email already exists
                Doctor existing = doctorRepository.findByEmail(request.getEmail());
                if (existing != null) {
                        throw new RuntimeException("Doctor with email already exists");
                }

                Doctor doctor = new Doctor();
                doctor.setName(request.getName());
                doctor.setSpecialty(request.getSpecialty());
                List<LocalTime> times = request.getAvailableTimes().stream().map(LocalTime::parse).toList();
                doctor.setAvailableTimes(times);

                doctor = doctorRepository.save(doctor);

                return new DoctorDTO(
                                doctor.getId(),
                                doctor.getName(),
                                doctor.getSpecialty(),
                                doctor.getPhoneNumber(),
                                doctor.getUserAccount().getEmail(),
                                doctor.getAvailableTimes());
        }

        @Transactional
        public List<DoctorDTO> filterDoctorBySpecility(String specialty) {
                List<Doctor> doctors = doctorRepository.findBySpecialtyIgnoreCase(specialty);
                return doctors.stream()
                                .map(d -> new DoctorDTO(
                                                d.getId(),
                                                d.getName(),
                                                d.getSpecialty(),
                                                d.getPhoneNumber(),
                                                d.getUserAccount().getEmail(),
                                                d.getAvailableTimes()))
                                .toList();
        }

        @Transactional
        public List<DoctorDTO> filterDoctorsByNameSpecilityandTime(String name, String specialty, String timePeriod) {
                List<Doctor> doctors = doctorRepository.findByNameContainingIgnoreCaseAndSpecialtyIgnoreCase(name,
                                specialty);
                List<Doctor> filtered = doctors.stream()
                                .filter(d -> d.getAvailableTimes().stream()
                                                .anyMatch(time -> timePeriod.equals("AM") ? time.getHour() < 12
                                                                : time.getHour() >= 12))
                                .toList();
                return filtered.stream()
                                .map(d -> new DoctorDTO(
                                                d.getId(),
                                                d.getName(),
                                                d.getSpecialty(),
                                                d.getPhoneNumber(),
                                                d.getUserAccount().getEmail(),
                                                d.getAvailableTimes()))
                                .toList();
        }

        @Transactional
        public List<DoctorDTO> findDoctorByName(String name) {
                List<Doctor> doctors = doctorRepository.findByNameLike(name);
                return doctors.stream()
                                .map(d -> new DoctorDTO(
                                                d.getId(),
                                                d.getName(),
                                                d.getSpecialty(),
                                                d.getPhoneNumber(),
                                                d.getUserAccount().getEmail(),
                                                d.getAvailableTimes()))
                                .toList();
        }

        public String validateDoctor(String email, String password) {
                UserAccount userAccount = userAccountRepository.findByEmailAndPassword(email, password)
                                .orElseThrow(() -> new InvalidAuthenticationException("Invalid email or password"));
                if (!userAccount.isActive() || userAccount.getRole() != Role.DOCTOR) {
                        throw new InvalidAuthenticationException("Invalid role or account is inactive");
                }
                return tokenService.generateToken(email, Role.DOCTOR);
        }
}
