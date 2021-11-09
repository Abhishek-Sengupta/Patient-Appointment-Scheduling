package com.springdata.patientscheduling;

import com.springdata.patientscheduling.entities.Appointment;
import com.springdata.patientscheduling.entities.Doctor;
import com.springdata.patientscheduling.entities.Insurance;
import com.springdata.patientscheduling.entities.Patient;
import com.springdata.patientscheduling.repos.AppointmentRepository;
import com.springdata.patientscheduling.repos.DoctorRepository;
import com.springdata.patientscheduling.repos.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PatientschedulingApplicationTests {

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	AppointmentRepository appointmentRepository;

	@Test
	public void testCreateDoctor() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("Abhi");
		doctor.setLastName("Sen");
		doctor.setSpeciality("All");
		doctorRepository.save(doctor);
	}

	@Test
	public void testCreatePatient() {
		Patient patient = new Patient();
		patient.setFirstName("Arun");
		patient.setLastName("Das");
		patient.setPhone("9888777999");

		Insurance insurance = new Insurance();
		insurance.setProviderName("ICICI Lombard");
		insurance.setCopay(40d);

		patient.setInsurance(insurance);

		Doctor doctor = doctorRepository.findById(1L).get();
		List<Doctor> doctors = Arrays.asList(doctor);
		patient.setDoctors(doctors);

		patientRepository.save(patient);
	}

	@Test
	public void testCreateAppointment() {

		Appointment appointment = new Appointment();
		Timestamp appointmentTime = new Timestamp(new Date().getTime());
		appointment.setAppointmentTime(appointmentTime);
		appointment.setReason("I have cold and cough");
		appointment.setStarted(true);

		appointment.setPatient(patientRepository.findById(1l).get());
		appointment.setDoctor(doctorRepository.findById(1L).get());

		appointmentRepository.save(appointment);
	}
}
