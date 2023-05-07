/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import PatientManagement.Catalogs.DrugCatalog;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Patient.Patient;
import PatientManagement.Persona.PersonDirectory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kal bugrara
 */
public class Clinic {
    PatientDirectory patientdirectory;
    PersonDirectory persondirectory;
    SiteCatalog siteList;

    LocationList locationList;
    DrugCatalog drugcatalog;
    EventSchedule eventschedule;
    VitalSignsCatalog vitalSignsCatalog;
    String name; // name of the clinic

    // key is locationName
    Map<String, List<Patient>> patientLocationMap;

    public Clinic(String n) {
        eventschedule = new EventSchedule();
        siteList = new SiteCatalog();
        locationList = new LocationList();
        persondirectory = new PersonDirectory();
        patientdirectory = new PatientDirectory(this);
        vitalSignsCatalog = new VitalSignsCatalog();
        patientLocationMap = new HashMap<>();
        name = n;
    }

    public Map<String, List<Patient>> getPatientLocationMap() {
        return patientLocationMap;
    }

    public String getName() {
        return name;
    }

    public EventSchedule getEventschedule() {
        return eventschedule;
    }

    public void setPersondirectory(PersonDirectory persondirectory) {
        this.persondirectory = persondirectory;
    }

    public void setVitalSignsCatalog(VitalSignsCatalog vitalSignsCatalog) {
        this.vitalSignsCatalog = vitalSignsCatalog;
    }

    public SiteCatalog getSiteCatalog() {
        return siteList;
    }

    public LocationList getLocationList() {
        return locationList;
    }

    public Site newSite(Location loc, String n) {

        Site s = siteList.newSite(loc,n);
        return s;
    }

    public VitalSignsCatalog getVitalSignsCatalog() {
        return vitalSignsCatalog;
    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public PatientDirectory getPatientDirectory() {
        return patientdirectory;
    }

    public void printShortInfo(){
        System.out.println("Checking what's inside the clinic of " + name);
        persondirectory.printShortInfo();
        locationList.printShortInfo();
        eventschedule.printShortInfo();
    }
}
