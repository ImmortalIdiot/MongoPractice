package com.immortalidiot.MongoPractice.controller;

import com.immortalidiot.MongoPractice.model.Patient;
import com.immortalidiot.MongoPractice.service.GeneratePatientService;
import com.immortalidiot.MongoPractice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@CrossOrigin
public class PatientController {

    private final PatientService patientService;
    private final GeneratePatientService generatePatientService;

    public PatientController(PatientService patientService,
                             GeneratePatientService generatePatientService) {
        this.patientService = patientService;
        this.generatePatientService = generatePatientService;
    }

    @PostMapping("/create")
    public Patient createPatient(@RequestParam String name,
                                 @RequestParam String diseases,
                                 @RequestParam String hospital,
                                 @RequestParam List<String> symptoms,
                                 @RequestParam int age,
                                 @RequestParam String severity) {
        return patientService.createPatient(name, diseases, hospital, symptoms, age, severity);
    }

    @PostMapping("/fill/{count}")
    public ResponseEntity<String> fillPatients(@PathVariable int count) {
        generatePatientService.generatePatients(count);
        return ResponseEntity.ok(count + " patients have been generated and saved successfully");
    }

    @PutMapping("/update/{id}")
    public Patient updatePatient(@PathVariable String id,
                                 @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }

    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable String id) {
        return patientService.getPatientById(id);
    }

    @GetMapping("/severity")
    public List<Patient> getPatientBySeverity(@RequestParam String severity) {
        return patientService.findPatientBySeverity(severity);
    }

    @GetMapping("/age")
    public List<Patient> getPatientByAge(@RequestParam int age) {
        return patientService.findPatientByAge(age);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("The patient with id " + id + " have been removed successfully");
    }
}
