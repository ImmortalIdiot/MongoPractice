package com.immortalidiot.MongoPractice.service;

import com.immortalidiot.MongoPractice.model.Patient;

import java.util.List;

public interface PatientService {
    Patient createPatient(String name,
                          String diseases,
                          String hospital,
                          List<String> symptoms,
                          int age,
                          String severity);
    Patient getPatientById(String id);
    List<Patient> getAllPatients();
    List<Patient> findPatientBySeverity(String severity);
    List<Patient> findPatientByAge(int age);
    Patient updatePatient(String id, Patient newPatient);
    void deletePatient(String id);
}
