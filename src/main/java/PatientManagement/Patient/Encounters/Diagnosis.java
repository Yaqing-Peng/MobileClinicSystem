/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Encounters;

/**
 *
 * @author kal bugrara
 */
public class Diagnosis {

    boolean confirmed = false;
    Disease disease;

    public Disease getDisease() {
        return disease;
    }

    public Diagnosis(Disease dis, boolean conf) {
        disease = dis;
        confirmed = conf; // Assumption: if true then lab tests confirm that it is a diesease
    }

    public boolean isConfirmed() {
        return confirmed; // just return the boolean
    }

    public void printShortInfo(){

        String diseaseName = "None";
        //String thirdParty = "None";
        if(disease != null){
            diseaseName = disease.name;
            // if(disease.isTransfer){
            //     thirdParty = disease.thirdPartyName;
            // }
        }
        if(disease == null){
            System.out.println("After dignosis, this patient has no disease");
        }else{
            disease.printShortInfo();
        }
        // System.out.println("Dignosis of this patient:"+ " | " + "Disease: " + diseaseName 
        // + " | " +  "Is : " + confirmed + " | " + "needTransferTo: " + disease.thirdPartyName);
    }

}
