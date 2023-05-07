import PatientManagement.Clinic.Site;
import PatientManagement.Persona.Person;
import PatientManagement.TestData.ReceptionService;
import PatientManagement.TestData.TestDataUtil;
import PatientManagement.Clinic.Clinic;
import static PatientManagement.TestData.Reports.*;
import com.github.javafaker.Faker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kal bugrara
 */

public class PatientCareMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Clinic UNClinic = TestDataUtil.createClinicAndLoadData("UN Mobile Clinic", 3, 500, 330, 20);

        // serve patient
        servePatient(UNClinic);

        // Report 1 
        printSickPatientsLastSeen(UNClinic);

        // Report 2
        printPatientsAppearedOnMultiSites(UNClinic);

        // Report 3
        printTrendsOnInfectiousDisease(UNClinic);
    }

    public static void servePatient(Clinic clinic) {
        ReceptionService receptionService = new ReceptionService();
        System.out.println(" ");
        System.out.println("=========================================Situation One: Serve a New Patient====================================================");
        System.out.println(" ");
        Faker faker = new Faker();
        // Patient schedule an appointment
        Site site = clinic.getSiteCatalog().getSites().get(0);
        Person person = new Person(faker.idNumber().valid(), 20, site.getLocation());
        // Nurse recieve the patient
        receptionService.serve(clinic, person, "03/25/2023", site.getName(),"I have headache!");

        System.out.println(" ");
        System.out.println("=========================================Situation Two: Serve an Existing Patient====================================================");
        System.out.println(" ");
        Person existingPerson = clinic.getPersonDirectory().getPersonList().get(0);

        receptionService.serve(clinic, existingPerson, "02/25/2023", site.getName(), "I still have headache!");

    }
}

