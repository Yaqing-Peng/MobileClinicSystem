/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

/**
 *
 * @author kal bugrara
 */
public class Allergy {

    public String name;

    public Allergy(String name) {
        this.name = name;
    }

    public void printShortInfo() {
        System.out.println("Allergic to " + name);
    }
}
