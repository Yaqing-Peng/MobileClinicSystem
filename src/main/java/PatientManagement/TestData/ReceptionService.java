package PatientManagement.TestData;

import PatientManagement.Catalogs.Drug;
import PatientManagement.Catalogs.VOrderInventoryCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Clinic.Site;
import PatientManagement.Patient.ClinicalHistory.Allergy;
import PatientManagement.Patient.ClinicalHistory.AllergyHistory;
import PatientManagement.Patient.ClinicalHistory.Vaccination;
import PatientManagement.Patient.ClinicalHistory.VaccinationHistory;
import PatientManagement.Patient.Encounters.*;
import PatientManagement.Patient.Orders.AssessmentOrder;
import PatientManagement.Patient.Orders.MedicationOrder;
import PatientManagement.Patient.Orders.TreatmentOrder;
import PatientManagement.Patient.Patient;
import PatientManagement.Persona.Person;

import java.util.ArrayList;
import java.util.List;

public class ReceptionService {

    // 1. Patient schedules an appointment
    //  1.1 new patient if necessary
    //  1.2 add patient info in clinic data system
    //  1.3 new event if necessary
    public Event scheduleAppointment(Clinic clinic, Person person, String scheduleDate, String scheduleSiteName) {
        System.out.println("Schedule an appointment for patient: " + person.getPersonId() + " | Site: "
                + scheduleSiteName + " | Date: " + scheduleDate);
        //Get scheduleSiteName the patient want
        Site scheduleSite = null;
        for (Site sit: clinic.getSiteCatalog().getSites()) {
            if (sit.getName().equals(scheduleSiteName)) {
                scheduleSite = sit;
            }
        }
        if (scheduleSite == null) {
            System.out.println("We don't have scheduleSiteName patient want to schedule, please choose new scheduleSiteName");
            return null;
        }
        ArrayList<Event> scheduledEvents = clinic.getEventschedule().getScheduledEvents();
        Event eventToAdd = null;
        for (Event event: scheduledEvents) {
            if (event.getDate().equals(scheduleDate)) {
                // Add patient to this event
                eventToAdd = event;
                break;
            }
        }
        if (eventToAdd == null) {// we don't have event at the scheduleDate
            String budgetNumber = Constants.FAKER.regexify("[0-9]{3}-[1-9][0-9]?|100");
            eventToAdd = clinic.getEventschedule().newEvent(scheduleSite, budgetNumber, scheduleDate);
        }
        // Check if patient have already visited this clinic
        Person personInCatalog = clinic.getPersonDirectory().findPerson(person.getPersonId());
        if (personInCatalog == null) { // means first come, we need create patient info.
            Patient patient = clinic.getPatientDirectory().newPatient(person);
            person.setPatient(patient);
            clinic.getPersonDirectory().addPerson(person);
        }
        System.out.println("Finished Scheduling!");
        return eventToAdd;
    }

    // 2. Register at front desk
    // 2.1 new encounter
    public Encounter registerAtFrontDesk(Clinic clinic, Event event, Person person) {
        System.out.println("Start registering at front desk for patient: " + person.getPersonId());
        Patient patient = clinic.getPatientDirectory().getPatientByPersonId(person.getPersonId());
        if (event == null || patient == null) {
            System.out.println("The person haven't scheduled, please schedule first!");
            return null;
        }
        Encounter encounter = new Encounter(patient,null, event, null);
        event.addEncounter(encounter);
        patient.getEncounterHistory().getEncounterList().add(encounter);
        System.out.println("Finished Registering!");
        return encounter;
    }

    // 3. Nurse takes the patient in
    //  3.1 Vital signs taken
    //  3.2 Chief complaint record
    public void nurseTakePatient(Encounter encounter, String chiefComplaintByPatient) {
        System.out.println("Nurse starts taking the patient: " + encounter.getPatient().getPerson().getPersonId());
        if (chiefComplaintByPatient != null) {
            System.out.println("Nurse records the chief complaint: " + chiefComplaintByPatient);
            encounter.setChiefComplaint(chiefComplaintByPatient);
        }
        // take vital signs
        System.out.println("Nurse starts measuring and recording vital signs: ");
        VitalSigns vitalSigns = encounter.getVitalSigns();

        System.out.println("Respiratory Rate: 28");
        VitalSignMetric respiratory_rate = vitalSigns.addNewVitals("Respiratory Rate", 28);

        System.out.println("Heart Rate: 98");
        vitalSigns.addNewVitals("Heart Rate", 98);

        System.out.println("Systolic blood pressure: 80");
        vitalSigns.addNewVitals("Systolic blood pressure", 80);

        System.out.println("Weight in kilos:  67");
        vitalSigns.addNewVitals("Weight in kilos", 67);
        System.out.println("Nurse work finished!");
    }

    // 4. Doctor treatment
    public void doctorTreat(Encounter encounter) {
        System.out.println("Doctor starts seeing the patient: " + encounter.getPatient().getPerson().getPersonId());

        Patient patient = encounter.getPatient();

        System.out.println("Doctor: tell me how you feel.");
        System.out.println("Patient: " + encounter.getChiefComplaint());

        System.out.println("Doctor starts reviewing patient history: ");
        reviewPatientHistory(patient);

        System.out.println("Doctor starts diagnosing the problem: ");
        encounter.getVitalSigns().areNormal();

        System.out.println("Doctor starts assigning lab test order: ");
        VOrderInventoryCatalog vOrderInventoryCatalog = encounter.createVOrderInventoryCatalog();
        List<AssessmentOrder> assessmentOrders = vOrderInventoryCatalog.getAssessmentOrders();
        System.out.println("Assign blood test order!");
        assessmentOrders.add(new AssessmentOrder("Blood test", encounter.getEvent().getDate()));

        System.out.println("After assessment, the doctor gives diagnosis: ");
        encounter.newDiagnosis(Constants.DISEASES[1], true);

        String suggestTreatment = "Please take medicine on time, eat healthy food, and drink enough water";
        System.out.println("Doctor suggest treatment: " + suggestTreatment);
        vOrderInventoryCatalog.getTreatmentOrders().add(new TreatmentOrder(suggestTreatment, false, null));

        System.out.println("Doctor starts assigning medication order: ");
        List<MedicationOrder> medicationOrders = vOrderInventoryCatalog.getMedicationOrders();
        MedicationOrder medicationOrder = new MedicationOrder();
        Drug drug = new Drug("Cephalopod");
        medicationOrder.getDrugCatalog().getDrugs().add(drug);
        medicationOrder.getCountForEachDrug().put(drug, 3);
        medicationOrders.add(medicationOrder);
        System.out.println("drug name: " + drug.getName() + ", count: " + medicationOrder.getCountForEachDrug().get(drug));

        System.out.println("Doctor refers the patient to a third party specialist: ");
        if (encounter.getDiagnosis().getDisease().isTransfer()) {
            Disease disease = encounter.getDiagnosis().getDisease();
            System.out.println("Need further treatment for: " + disease.getName() +
                    ", please transfer to: " + disease.getThirdPartyName());
            encounter.getvOrderInventoryCatalog().getTreatmentOrders()
                    .add(new TreatmentOrder("Send to a third party to see specialist", true, disease.getThirdPartyName()));
        } else {
            System.out.println("No need transfer!");
        }

    }

    private void reviewPatientHistory(Patient patient) {
        EncounterHistory encounterHistory = patient.getEncounterHistory();
        AllergyHistory allergyHistory = patient.getAlergyhistory();
        VaccinationHistory vaccinationHistory = patient.getVaccinationhistory();
        System.out.println("Encounter history: ");
        if (encounterHistory.getEncounterList().size() < 2) {
            System.out.println("We don't have encounter history info.");
        } else {
            System.out.println("ID | date | site | chief complaint | disease | third party");
            for (Encounter encounter: encounterHistory.getEncounterList()) {
                encounter.printShortInfo();
            }
        }
        System.out.println("Allergy history: ");
        if (allergyHistory.getAllergies().size() == 0) {
            System.out.println("We don't have patient allergic history info.");
        } else {
            for (Allergy allergy : allergyHistory.getAllergies()) {
                allergy.printShortInfo();
            }
        }
        System.out.println("Vaccination history: ");
        if (vaccinationHistory.getVaccinations().size() == 0) {
            System.out.println("We don't have patient allergic history info.");
        } else {
            for (Vaccination vaccination: vaccinationHistory.getVaccinations()) {
                vaccination.printShortInfo();
            }
        }
    }

    public void serve(Clinic clinic, Person person, String scheduleDate, String scheduleSite, String complaint) {
        System.out.println("-------------------------1.Schedule an Appointment----------------");
        // schedule
        Event event = scheduleAppointment(clinic, person, scheduleDate, scheduleSite);
        System.out.println("-------------------------End Schedule---------------------------");
        System.out.println();

        System.out.println("-------------------------2.Start Register--------------------------");
        // register
        Encounter encounter = registerAtFrontDesk(clinic, event, person);
        System.out.println("-------------------------End Register----------------------------");
        System.out.println();

        System.out.println("-------------------------3.Start Nurse Job------------------------");
        // nurse measure
        nurseTakePatient(encounter, complaint);
        System.out.println("-------------------------End Nurse Job---------------------------");
        System.out.println();

        System.out.println("-------------------------4.Start Doctor Job-------------------");
        // doctor treatment
        doctorTreat(encounter);
        System.out.println("-------------------------End Doctor Job---------------------");
        System.out.println();
        System.out.println("==========================================End Encounter===================================================");
    }
}
