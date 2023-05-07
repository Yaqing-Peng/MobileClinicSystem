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
public class AllergyHistory {

    Patient patient;
    ArrayList<Allergy> allergies;

    public AllergyHistory(Patient patient) {
        this.patient = patient;
        allergies = new ArrayList<Allergy>();
    }

    public ArrayList<Allergy> getAllergies() {
        return allergies;
    }
}
