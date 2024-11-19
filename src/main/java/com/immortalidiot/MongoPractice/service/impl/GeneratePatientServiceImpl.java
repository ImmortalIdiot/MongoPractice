package com.immortalidiot.MongoPractice.service.impl;

import com.github.javafaker.Faker;
import com.immortalidiot.MongoPractice.model.Patient;
import com.immortalidiot.MongoPractice.repository.PatientRepository;
import com.immortalidiot.MongoPractice.service.GeneratePatientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GeneratePatientServiceImpl implements GeneratePatientService {

    private final PatientRepository patientRepository;

    public GeneratePatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void generatePatients(int count) {
        Faker faker = new Faker();
        Random random = new Random();

        List<Patient> patients = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            patients.add(new Patient(faker.name().fullName(),
                    faker.medical().diseaseName(),
                    faker.medical().hospitalName(),
                    List.of(faker.medical().symptoms().split(", ")),
                    random.nextInt(0, 100),
                    random.nextBoolean() ? "critical" : "stable"
            ));
        }

        patientRepository.saveAll(patients);
    }
}
