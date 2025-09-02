CREATE PROCEDURE smart_clinic_db.GetDoctorWithMostPatientsByYear(IN input_year INT)
BEGIN
	WITH DoctorWithPatientCount AS 
	(
		SELECT 
			d.id, 
			d.name, 
			d.specialty, 
			YEAR(a.schedule_start) AS appointment_year,
			COUNT(a.patient_id) AS PatientCount
		FROM doctor d
		JOIN appointment a ON a.doctor_id = d.id
		WHERE YEAR(a.schedule_start) = input_year
		GROUP BY d.id, d.name, d.specialty, YEAR(a.schedule_start)
	)
	,
	MaxPatientCountPerYear AS 
	(
		SELECT 
			dwp.appointment_year, 
			MAX(PatientCount) AS MaxPatientCount
		FROM DoctorWithPatientCount dwp
		GROUP BY dwp.appointment_year
	)
	SELECT 
		dwp.appointment_year, 
		dwp.name AS doctor_name, 
		dwp.specialty, 
		dwp.PatientCount 
	FROM DoctorWithPatientCount dwp
	JOIN MaxPatientCountPerYear mpc 
		ON mpc.appointment_year = dwp.appointment_year AND dwp.PatientCount = mpc.MaxPatientCount
	ORDER BY dwp.appointment_year, dwp.name;
END