CREATE PROCEDURE smart_clinic_db.GetDoctorWithMostPatientsByMonth(
	IN input_year INT, 
	IN input_month INT
)
BEGIN
    -- Input validation
    IF input_year IS NULL OR input_month IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Year and month parameters cannot be NULL';
    END IF;
    
    IF input_month < 1 OR input_month > 12 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Month must be between 1 and 12';
    END IF;
    
	WITH DoctorWithPatientCount AS 
	(
		SELECT 
			d.id, 
			d.name, 
			d.specialty, 
			COUNT(a.patient_id) AS patient_count,
			-- Use window function to find max count
			MAX(COUNT(DISTINCT a.patient_id)) OVER() AS max_patient_count
		FROM doctor d
		INNER JOIN appointment a ON a.doctor_id = d.id
		WHERE 
			YEAR(a.schedule_start) = input_year 
			AND
			MONTH(a.schedule_start) = input_month
			AND
			a.schedule_start IS NOT NULL	
		GROUP BY d.id, d.name, d.specialty
	)
	SELECT 
		CONCAT(LPAD(input_month, 2, '0'), '/', input_year) AS appointment_month,
		name AS doctor_name, 
		specialty, 
		patient_count 
	FROM DoctorWithPatientCount dwp
	WHERE patient_count = max_patient_count
	ORDER BY name;
	
END