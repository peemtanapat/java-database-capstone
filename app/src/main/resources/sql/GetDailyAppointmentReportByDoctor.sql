CREATE PROCEDURE GetDailyAppointmentReportByDoctor( 
	IN input_date DATE
)
BEGIN
	SELECT 
		d.name AS doctor_name,
		a.schedule_start,
		a.schedule_end,
		p.name AS patient_name,
		p.phone_number
	FROM appointment a
	JOIN doctor d ON a.doctor_id = d.id
	JOIN patient p ON a.patient_id = p.id
	WHERE 
		DATE(input_date) = DATE(schedule_start)
	ORDER BY
		d.name, a.schedule_start;
END
