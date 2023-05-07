/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Catalogs;

import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Orders.AssessmentOrder;
import PatientManagement.Patient.Orders.MedicationOrder;
import PatientManagement.Patient.Orders.TreatmentOrder;
import PatientManagement.Patient.Orders.VaccinationOrder;
import PatientManagement.Patient.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kal bugrara
 */
public class VOrderInventoryCatalog {

    Encounter encounter;
    List<AssessmentOrder> assessmentOrders;

    List<MedicationOrder> medicationOrders;

    List<TreatmentOrder> treatmentOrders;

    List<VaccinationOrder> vaccinationOrders;

    public VOrderInventoryCatalog(Encounter encounter) {
        this.encounter = encounter;
        this.assessmentOrders = new ArrayList<>();
        this.medicationOrders = new ArrayList<>();
        this.treatmentOrders = new ArrayList<>();
        this.vaccinationOrders = new ArrayList<>();
    }

    public List<AssessmentOrder> getAssessmentOrders() {
        return assessmentOrders;
    }

    public List<MedicationOrder> getMedicationOrders() {
        return medicationOrders;
    }

    public List<TreatmentOrder> getTreatmentOrders() {
        return treatmentOrders;
    }

    public List<VaccinationOrder> getVaccinationOrders() {
        return vaccinationOrders;
    }
}
