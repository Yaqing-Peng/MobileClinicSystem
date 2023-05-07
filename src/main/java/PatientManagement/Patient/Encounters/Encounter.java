/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Encounters;

import PatientManagement.Catalogs.Limits;
import PatientManagement.Catalogs.VOrderInventoryCatalog;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Patient.Patient;

/**
 *
 * @author kal bugrara
 * 
 */

public class Encounter {
    Patient patient;
    String chiefComplaint;
    Diagnosis diagnosis;
    Event event;
    VitalSigns vitalSigns;
    EncounterHistory encounterHistory;

    VOrderInventoryCatalog vOrderInventoryCatalog;

    public Encounter(Patient p, String cc, Event ev, EncounterHistory eh) { // event is the date when the check was made
        chiefComplaint = cc;
        event = ev;
        patient = p;
        encounterHistory = eh;
        vitalSigns = new VitalSigns(this);
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public VOrderInventoryCatalog createVOrderInventoryCatalog() {
        vOrderInventoryCatalog = new VOrderInventoryCatalog(this);
        return vOrderInventoryCatalog;
    }

    public VOrderInventoryCatalog getvOrderInventoryCatalog() {
        return vOrderInventoryCatalog;
    }


    public Patient getPatient() {
        return patient;
    }
    
    public void newDiagnosis(Disease disease, boolean confirmed) {
        diagnosis = new Diagnosis(disease, confirmed);
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public Limits getVitalSignLimits(int age, String name) {
        Clinic clinic = patient.getClinic();
        VitalSignsCatalog vsc = clinic.getVitalSignsCatalog();
        return vsc.findVitalSignLimits(age, name);
    }

    public VitalSigns getVitalSigns() {
        return vitalSigns;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public VitalSignMetric addNewVitals(String name, int value) {
        return vitalSigns.addNewVitals(name, value);
    }

    public EncounterHistory getEncounterHistory() {
        return encounterHistory;
    }

    public boolean areVitalsNormal() {
        return vitalSigns.areNormal();
    }

    public Event getEvent() {
        return event;
    }

    public void printShortInfo(){
        if (diagnosis == null) {
            return;
        }
        String diseaseName = "None";
        String thirdPartyName = "None";
        if (diagnosis.isConfirmed()) {
            diseaseName = diagnosis.getDisease().getName();
            if (diagnosis.getDisease().isTransfer) {
                thirdPartyName = diagnosis.getDisease().getThirdPartyName();
            }
        }
        System.out.println(patient.getPerson().getPersonId() + " | "+ event.getSite().getName() +" | " + event.getDate()
        + " | " + chiefComplaint + " | " + diseaseName
        + " | " + thirdPartyName);
    }
}