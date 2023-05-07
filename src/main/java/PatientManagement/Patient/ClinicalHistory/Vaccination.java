/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

import PatientManagement.Catalogs.VOrderItem;

/**
 *
 * @author kal bugrara
 */
public class Vaccination {
    String name;
    public Vaccination(String name) {
        this.name = name;
    }

    public void printShortInfo() {
        System.out.println("Injected vaccine: " + name);
    }
}
