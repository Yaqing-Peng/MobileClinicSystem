/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Orders;

import PatientManagement.Catalogs.Drug;
import PatientManagement.Catalogs.DrugCatalog;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kal bugrara
 */
public class MedicationOrder {

    DrugCatalog drugCatalog;

    Map<Drug, Integer> countForEachDrug;

    public MedicationOrder() {
        drugCatalog = new DrugCatalog();
        countForEachDrug = new HashMap<>();
    }

    public DrugCatalog getDrugCatalog() {
        return drugCatalog;
    }

    public Map<Drug, Integer> getCountForEachDrug() {
        return countForEachDrug;
    }
}
