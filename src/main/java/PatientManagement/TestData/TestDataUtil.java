package PatientManagement.TestData;

import PatientManagement.Clinic.*;
import PatientManagement.Patient.ClinicalHistory.Allergy;
import PatientManagement.Patient.ClinicalHistory.Vaccination;
import PatientManagement.Patient.Encounters.Disease;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Patient;
import PatientManagement.Persona.Person;
import PatientManagement.Persona.PersonDirectory;

import java.util.*;

import static PatientManagement.TestData.VitalSignsDetail.generateGlobalVitalSignsCatalog;

public class TestDataUtil {

    public static Clinic createClinicAndLoadData(String name, int locationCount, int patientCount, int eventCount, int encounterCount) {
        Clinic clinic = new Clinic(name);
        loadLocation(clinic, locationCount);
        loadPatients(clinic, patientCount);
        loadEvenSchedule(clinic, eventCount, encounterCount);
        clinic.setVitalSignsCatalog(generateGlobalVitalSignsCatalog());
        return clinic;
    }
    
    // 1.Generate random cityLocation
    public static void loadLocation(Clinic clinic, int locationCount){
        for(int j = 0; j < locationCount; j++){
            String cityLocation = Constants.FAKER.address().city();
            Location location = clinic.getLocationList().newLocation(cityLocation);
            loadSites(clinic, location);
            clinic.getPatientLocationMap().put(location.getName(), new ArrayList<>());
        }
    }

    public static void loadSites(Clinic clinic, Location location) {
        int randomSiteNum = Constants.FAKER.random().nextInt(Constants.MAX_SITE_NUM_EACH_LOCATION);
        for (int j = 0; j < randomSiteNum; j++) {
            String siteName = Constants.FAKER.address().streetName();
            clinic.getSiteCatalog().newSite(location,siteName);
        }
    }
    
    // 2.Add patients for each clinic
    public static void loadPatients(Clinic clinic, int patientCount){
        PersonDirectory personDirectory = clinic.getPersonDirectory();
        PatientDirectory patientDirectory = clinic.getPatientDirectory();
        for (int k = 0; k < patientCount; k++) {
            String id = Constants.FAKER.idNumber().valid();
            int age = Constants.FAKER.random().nextInt(Constants.MAX_AGE);
            int cityNum = clinic.getLocationList().getLocations().size();
            Location city = clinic.getLocationList().getLocations().get(Constants.FAKER.random().nextInt(cityNum));
            Person person = personDirectory.newPerson(id, age, city);
            Patient patient = patientDirectory.newPatient(person);
            person.setPatient(patient);
            clinic.getPatientLocationMap().get(city.getName()).add(patient);

            // randomly generate allergy history
            int allergyCount = Constants.FAKER.random().nextInt(Constants.MAX_ALLERGY_COUNT);
            Set<String> allergySet = new HashSet<>();
            for (int t = 0; t < allergyCount; t++) {
                String allergyName = Constants.ALLERGY_THING[Constants.FAKER.random().nextInt(Constants.ALLERGY_THING.length)];
                while (allergySet.contains(allergyName)) {
                    allergyName = Constants.ALLERGY_THING[Constants.FAKER.random().nextInt(Constants.ALLERGY_THING.length)];
                }
                allergySet.add(allergyName);
                patient.getAlergyhistory().getAllergies()
                        .add(new Allergy(allergyName));
            }

            int vaccinationCount = Constants.FAKER.random().nextInt(Constants.MAX_VACCINATION_COUNT);
            for (int t = 0; t < vaccinationCount; t++) {
                patient.getVaccinationhistory().getVaccinations()
                        .add(new Vaccination(Constants.VACCINATIONS[t]));
            }
        }
    }

    //// 3.Add eventSchedule for each clinic
    public static void loadEvenSchedule(Clinic clinic, int eventCount,int encounterCount){
        EventSchedule eventSchedule = clinic.getEventschedule();
        SiteCatalog siteCatalog = clinic.getSiteCatalog();
        String startDate = Constants.START_DATE;
        String lastSiteName = "";
        for (int j=0; j<eventCount; j++) {
            //3.1 add events to the eventSchedule
            String budgetNumber = Constants.FAKER.regexify("[0-9]{3}-[1-9][0-9]?|100");
            if(siteCatalog.getSites().size()==0){
                System.out.println("No sites available! Please generate some sites first!");
                break;
            }
            int randomSiteIndex = Constants.FAKER.random().nextInt(siteCatalog.getSites().size());
            Site site = siteCatalog.getSites().get(randomSiteIndex);
            String randomDate = DateUtil.generateRandomDate(startDate);
            if (randomDate.equals(startDate)) {
                while (site.getName().equals(lastSiteName)) {
                    randomSiteIndex = Constants.FAKER.random().nextInt(siteCatalog.getSites().size());
                    site = siteCatalog.getSites().get(randomSiteIndex);
                }
            }
            Event event = eventSchedule.newEvent(site, budgetNumber, randomDate);
            startDate = randomDate;
            lastSiteName = site.getName();

            Set<String> patientSet = new HashSet<>();

            //3.2 add encounters to each event
            for (int k = 0; k < encounterCount; k++) {
                List<Patient> locationPatients = clinic.getPatientLocationMap().get(site.getLocation().getName());
                // Patient won't appear on same site on a same day.
                Patient patient = locationPatients.get(Constants.FAKER.random().nextInt(locationPatients.size()));
                while (patientSet.contains(patient.getPerson().getPersonId())) {
                    patient = locationPatients.get(Constants.FAKER.random().nextInt(locationPatients.size()));
                }
                patientSet.add(patient.getPerson().getPersonId());
                Encounter encounter = new Encounter(patient, null, event, null);

                //3.2.1 add diagnosis to each encounter
                boolean isConfirmed = Constants.FAKER.random().nextBoolean();
                Disease disease = null;
                if (isConfirmed) {
                    disease = Constants.DISEASES[Constants.FAKER.random().nextInt(Constants.DISEASES.length)];
                }
                encounter.newDiagnosis(disease, isConfirmed);

                event.addEncounter(encounter);// add encounter to the event
                patient.getEncounterHistory().getEncounterList().add(encounter);//add encounter to the patient's encounter history
            }
        }
    }

}
