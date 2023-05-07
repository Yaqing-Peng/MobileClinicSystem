/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

import PatientManagement.Patient.Patient;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class VaccinationHistory {

    Patient patient;
    ArrayList<Vaccination> vaccinations;

    public VaccinationHistory(Patient patient) {
        this.patient = patient;
        vaccinations = new ArrayList<>();
    }

    public void addVaccination(Vaccination v) {
        vaccinations.add(v);
    }

    public ArrayList<Vaccination> getVaccinations() {
        return vaccinations;
    }
}