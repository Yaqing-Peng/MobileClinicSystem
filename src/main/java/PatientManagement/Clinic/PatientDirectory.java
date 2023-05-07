/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import PatientManagement.Patient.Patient;
import PatientManagement.Persona.Person;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kal bugrara
 */
public class PatientDirectory {
    Clinic clinic;
    ArrayList<Patient> patients;

    public PatientDirectory(Clinic clinic) {
        this.clinic = clinic;
        patients = new ArrayList<Patient>();
    }

    public int getConfirmedPositiveTotals() {
        int sum = 0;

        for (Patient p : patients) {
            if (p.isConfirmedPositive()) {
                sum = sum + 1;
            }
        }
        return sum;
    }

    public ArrayList<Patient> getAllConfirmedPositives() {
        ArrayList<Patient> temp = new ArrayList<Patient>();
        for (Patient p : patients) {
            if (p.isConfirmedPositive()) {
                temp.add(p);
            }
        }
        return temp; // has the list of encounters with confirmed diagnosis
    }

    public Patient newPatient(Person person) {
        Patient patient = new Patient(person, clinic);
        patients.add(patient);
        return patient;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Patient> getPatientsByLocation(String locationName) {
        List<Patient> list = new ArrayList<>();
        for (Patient patient: patients) {
            if (locationName.equals(patient.getPerson().getCity().getName())) {
                list.add(patient);
            }
        }
        return list;
    }

    public Patient getPatientByPersonId(String personId) {
        for (Patient patient: patients) {
            if (patient.getPerson().getPersonId().equals(personId)) {
                return patient;
            }
        }
        return null;
    }

}