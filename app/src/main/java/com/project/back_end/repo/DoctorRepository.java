package com.project.back_end.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.back_end.models.Doctor;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

   @Query("SELECT d, d.userAccount.email FROM Doctor d WHERE d.userAccount.email = :email")
   Doctor findByEmail(String email);

   @Query("SELECT d FROM Doctor d WHERE d.name LIKE CONCAT('%', :name, '%')")
   List<Doctor> findByNameLike(String name);

   List<Doctor> findByNameContainingIgnoreCaseAndSpecialtyIgnoreCase(String name, String specialty);

   List<Doctor> findBySpecialtyIgnoreCase(String specialty);
}