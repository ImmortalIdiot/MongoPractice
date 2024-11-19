package com.immortalidiot.MongoPractice.repository;

import com.immortalidiot.MongoPractice.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    List<Patient> findBySeverity(String severity);
    List<Patient> findByAge(int age);
}
