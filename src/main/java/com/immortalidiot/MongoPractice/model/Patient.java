package com.immortalidiot.MongoPractice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collation = "Medicals")
public class Patient {

    @Id
    private String id;

    private String name;
    private String diseases;
    private String hospital;
    private List<String> symptoms;
    private int age;
    private String severity;

    protected Patient() {}

    public Patient(String name,
                   String diseases,
                   String hospital,
                   List<String> symptoms,
                   int age,
                   String severity) {
        this.name = name;
        this.diseases = diseases;
        this.hospital = hospital;
        this.symptoms = symptoms;
        this.age = age;
        this.severity = severity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() >= 2) {
            this.name = name;
        }
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        if (!diseases.isEmpty()) {
            this.diseases = diseases;
        }
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        if (!hospital.isEmpty()) {
            this.hospital = hospital;
        }
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        if (!symptoms.isEmpty()) {
            this.symptoms = symptoms;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        if (severity.equalsIgnoreCase("stable") ||
                severity.equalsIgnoreCase("critical")) {
            this.severity = severity;
        }
    }

    @Override
    public String toString() {
        return getId() + ", " + getName() + ", " +
                getAge() + ", " + getDiseases() + ", " +
                formatSymptoms() + ", " + getSeverity() + ", " +
                getHospital();
    }

    private String  formatSymptoms() {
        if (symptoms.isEmpty()) { return "[]"; }
        return "[ " + String.join(", ", symptoms
                .stream()
                .map(symptom -> "\"" + symptom + "\"")
                .toArray(String[]::new)) + " ]";
    }
}
