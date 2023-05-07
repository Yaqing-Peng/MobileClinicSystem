/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Orders;

import PatientManagement.Patient.ClinicalHistory.Vaccination;
import PatientManagement.Patient.ClinicalHistory.Vaccine;
import PatientManagement.Patient.Patient;
import PatientManagement.TestData.TestDataUtil;

/**
 *
 * @author kal bugrara
 */
public class VaccinationOrder extends Order {
    Vaccine vaccine;
    Patient patient;
    String date;
    Vaccination vaccination;

    public VaccinationOrder(Vaccine v) {
        super();
        vaccine = v;

    }

}
