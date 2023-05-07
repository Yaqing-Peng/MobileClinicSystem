/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient;

import PatientManagement.Clinic.Clinic;
import PatientManagement.Patient.ClinicalHistory.AllergyHistory;
import PatientManagement.Patient.ClinicalHistory.VaccinationHistory;
import PatientManagement.Patient.Encounters.Diagnosis;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;
import PatientManagement.Persona.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kal bugrara
 */
public class Patient {
    Clinic clinic;
    EncounterHistory encounterhistory;
    VaccinationHistory vaccinationhistory;
    Person person;
    AllergyHistory alergyhistory;

    public Patient(Person p, Clinic clinic) {
        encounterhistory = new EncounterHistory(this); // link this patient object back
        vaccinationhistory = new VaccinationHistory(this);
        alergyhistory = new AllergyHistory(this);
        person = p;
        this.clinic = clinic;
    }

    public EncounterHistory getEncounterHistory() {
        return encounterhistory;
    }
    // the below method will return the encounter where the infection occured
    // from the returned encounter you pull the event, the site, etc.

    public Encounter getConfirmedEncounter() {
        ArrayList<Encounter> patientencounterlist = encounterhistory.getEncounterList();
        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            if (diag.isConfirmed()) {
                return currentencounter;// send back the whole confirmed encounters, so we extract event and site
            }
        }
        return null;
    }

    public AllergyHistory getAlergyhistory() {
        return alergyhistory;
    }

    public VaccinationHistory getVaccinationhistory() {
        return vaccinationhistory;
    }

    public boolean isConfirmedPositive() {

        ArrayList<Encounter> patientencounterlist = encounterhistory.getEncounterList();

        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            return diag.isConfirmed();

        }
        return false;
    }

    public List<Encounter> getConfirmedEncounters() {
        ArrayList<Encounter> confirmedEncounters = new ArrayList<>();
        for (Encounter encounter: encounterhistory.getEncounterList()) {
            if (encounter.getDiagnosis().isConfirmed()) {
                confirmedEncounters.add(encounter);
            }
        }
        return confirmedEncounters;
    }


    public Person getPerson() {
        return person;
    }


    public Clinic getClinic() {
        return clinic;
    }

}
