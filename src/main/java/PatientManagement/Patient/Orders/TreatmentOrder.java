/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Orders;

/**
 *
 * @author kal bugrara
 */
public class TreatmentOrder {

    public String suggestion;

    public boolean isTransfer;

    public String thirdPartyName;

    public TreatmentOrder(String suggestion, Boolean isTransfer, String thirdPartyName) {
        this.suggestion = suggestion;
        this.isTransfer = isTransfer;
        this.thirdPartyName = thirdPartyName;
    }
}
