package com.project.back_end.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.back_end.models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

   @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.doctor d WHERE d.id = :doctorId AND a.scheduleStart BETWEEN :start AND :end")
   List<Appointment> findByDoctorIdAndAppointmentTimeBetween(@Param("doctorId") Long doctorId,
         @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

   @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.doctor d LEFT JOIN FETCH a.patient p WHERE d.id = :doctorId AND LOWER(p.name) LIKE LOWER(CONCAT('%', :patientName, '%')) AND a.scheduleStart BETWEEN :start AND :end")
   List<Appointment> findByDoctorIdAndPatient_NameContainingIgnoreCaseAndAppointmentTimeBetween(
         @Param("doctorId") Long doctorId, @Param("patientName") String patientName,
         @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

   @Modifying
   @Transactional
   @Query("DELETE FROM Appointment a WHERE a.doctor.id = :doctorId")
   void deleteAllByDoctorId(@Param("doctorId") Long doctorId);

   @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId")
   List<Appointment> findByPatientId(@Param("patientId") Long patientId);

   @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId AND a.isActive = :status ORDER BY a.scheduleStart ASC")
   List<Appointment> findByPatient_IdAndStatusOrderByAppointmentTimeAsc(@Param("patientId") Long patientId,
         @Param("status") boolean status);

   @Query("SELECT a FROM Appointment a WHERE LOWER(a.doctor.name) LIKE LOWER(CONCAT('%', :doctorName, '%')) AND a.patient.id = :patientId")
   List<Appointment> filterByDoctorNameAndPatientId(@Param("doctorName") String doctorName,
         @Param("patientId") Long patientId);

   @Query("SELECT a FROM Appointment a WHERE LOWER(a.doctor.name) LIKE LOWER(CONCAT('%', :doctorName, '%')) AND a.patient.id = :patientId AND a.isActive = :status")
   List<Appointment> filterByDoctorNameAndPatientIdAndStatus(@Param("doctorName") String doctorName,
         @Param("patientId") Long patientId, @Param("status") boolean status);

   @Modifying
   @Transactional
   @Query("UPDATE Appointment a SET a.isActive = :status WHERE a.id = :id")
   void updateStatus(@Param("status") boolean status, @Param("id") long id);
}
