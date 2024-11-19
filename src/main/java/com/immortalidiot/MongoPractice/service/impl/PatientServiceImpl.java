package com.immortalidiot.MongoPractice.service.impl;

import com.immortalidiot.MongoPractice.model.Patient;
import com.immortalidiot.MongoPractice.repository.PatientRepository;
import com.immortalidiot.MongoPractice.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(String name,
                                 String diseases,
                                 String hospital,
                                 List<String> symptoms,
                                 int age,
                                 String severity) {
        Patient patient = new Patient(name, diseases, hospital, symptoms, age, severity);
        if (!(patient.getName().isEmpty() ||
                patient.getDiseases().isEmpty() ||
                patient.getHospital().isEmpty() ||
                patient.getSymptoms().isEmpty() ||
                patient.getAge() == 0 ||
                patient.getSeverity().isEmpty()) &&
                isValidatePatientData(patient))  {
            return patientRepository.save(patient);
        } else { return null; }
    }

    @Override
    public Patient getPatientById(String id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The patient with the specified id not found"));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Page<Patient> getPatients(PageRequest pageRequest) {
        return patientRepository.findAll(pageRequest);
    }

    @Override
    public List<Patient> findPatientBySeverity(String severity) {
        return patientRepository.findBySeverity(severity);
    }

    @Override
    public List<Patient> findPatientByAge(int age) {
        return patientRepository.findByAge(age);
    }

    @Override
    public Patient updatePatient(String id, Patient newPatient) {
        newPatient.setId(id);
        return patientRepository.save(newPatient);
    }

    @Override
    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }

    private boolean isValidatePatientData(Patient patient) {
        if (patient.getAge() < 0) {
            throw new RuntimeException("Age cannot be negative.");
        }

        String severity = patient.getSeverity();

        if (!"critical".equalsIgnoreCase(severity) && !"stable".equalsIgnoreCase(severity)) {
            throw new RuntimeException("Severity must be either 'critical' or 'stable'.");
        }

        return true;
    }
}
