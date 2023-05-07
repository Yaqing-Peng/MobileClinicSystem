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
public class Disease {
    String name;
    boolean isInfectious;
    boolean isTransfer;
    String thirdPartyName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInfectious() {
        return isInfectious;
    }

    public void setInfectious(boolean isInfectious) {
        this.isInfectious = isInfectious;
    }

    public boolean isTransfer() {
        return isTransfer;
    }

    public void setTransfer(boolean isTransfer) {
        this.isTransfer = isTransfer;
    }

    public String getThirdPartyName() {
        return thirdPartyName;
    }

    public void setThirdPartyName(String thirdPartyName) {
        this.thirdPartyName = thirdPartyName;
    }

    public Disease(String name, boolean isInfectious, boolean isTransfer, String thirdPartyName) {
        this.name = name;
        this.isInfectious = isInfectious;
        this.isTransfer = isTransfer;
        this.thirdPartyName = thirdPartyName;
    }

    public void printShortInfo(){
        System.out.println(name + " | " + "isInfectious: " + isInfectious + " | " + "isTransfer: " + isTransfer
        + " | " + "needTransferTo: " + thirdPartyName);
    }
}
