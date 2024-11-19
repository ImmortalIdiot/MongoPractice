package com.immortalidiot.MongoPractice.repository;

import com.immortalidiot.MongoPractice.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientRepository extends MongoRepository<Patient, String> {
    List<Patient> findBySeverity(String severity);
    List<Patient> findByAge(int age);
}
