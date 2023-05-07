package PatientManagement.TestData;

import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Location;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Patient;

import java.util.*;

public class Reports {

    // Report 1 & 4
    public static void printSickPatientsLastSeen(Clinic clinic) {
        System.out.println(" ");
        System.out.println("================================================= Report 1 Sick Patients Info (last seen) ==================================================");
        System.out.println(" ");

        String[] thirdParties = {"1.Mental health center ", "2.Local hospital ", "3.Emergency Room "};
        System.out.println("This is the list of local service providers where we can transfer our patients to: ");

        for (String party : thirdParties) {
            System.out.println(party);
        }
        System.out.println("------------------------------------------------------------");

        List<Patient> patients = clinic.getPatientDirectory().getPatients();
        System.out.println("Sick patients last seen:");
        System.out.println("Patients Id | LastSeen Site |  LastSeen Location | LastSeen Date | Disease Name | Third Party(transfer)");

        int transferSum = 0;
        for (Patient patient: patients) {
            String thirdParty = "No need to transfer";
            List<Encounter> confirmedEncounters = patient.getConfirmedEncounters();
            if (confirmedEncounters.size() == 0) {
                continue;
            }
            Encounter lastEncounter = confirmedEncounters.get(confirmedEncounters.size()-1);

            String lastDate = lastEncounter.getEvent().getDate();
            String lastSeenSite = lastEncounter.getEvent().getSite().getName();
            String lastSeenLocation = lastEncounter.getEvent().getSite().getLocation().getName();
            String diseaseName = lastEncounter.getDiagnosis().getDisease().getName();
            if (lastEncounter.getDiagnosis().getDisease().isTransfer()) {
                transferSum++;
                thirdParty = lastEncounter.getDiagnosis().getDisease().getThirdPartyName();
            }

            System.out.println(patient.getPerson().getPersonId() + " | "
                            + lastSeenSite + " | " + lastSeenLocation + " | " + lastDate + " | " + diseaseName
                            + " | " + "Transferred to " + thirdParty);
        }
        System.out.println("Total num transfer encounters in this clinic is: " + transferSum);
        System.out.println("================================================ End of Report 1 =================================================");
        System.out.println();
    }

    // Report 2
    public static void printPatientsAppearedOnMultiSites(Clinic clinic) {
        System.out.println(" ");
        System.out.println("============================================= Report 2 Infectious Patients(visited multiple sites) =======================================");
        System.out.println(" ");
        System.out.println("Infectious patient id" + " | " + "Num of site visited" + " | " + "Site names and visited dates");
        System.out.println(" ");
        for(Patient p : clinic.getPatientDirectory().getPatients()){
            Map<String, String> siteNames = new HashMap<>();
            for (Encounter encounter: p.getConfirmedEncounters()) {
                if (encounter.getDiagnosis().getDisease().isInfectious()) {
                    for (Encounter en : p.getEncounterHistory().getEncounterList()){
                        siteNames.put(en.getEvent().getSite().getName(), en.getEvent().getDate());
                    }
                }
            }
            int siteCount = siteNames.size();
            if (siteCount > 1) {
                StringBuilder sitesVisited = new StringBuilder();
                for (String key: siteNames.keySet()) {
                    sitesVisited.append(key).append('-').append(siteNames.get(key)).append(", ");
                }
                sitesVisited.delete(sitesVisited.length()-2, sitesVisited.length());
                System.out.println(p.getPerson().getPersonId() + " | "
                        + siteCount + " | " + sitesVisited);
            }
        }
        System.out.println("================================================ End of Report 2 =================================================");
        System.out.println();
    }

    // Report 3
    public static void printTrendsOnInfectiousDisease(Clinic clinic) {
        System.out.println(" ");
        System.out.println("================================================= Report 3 Trends of Infection(by month and site) ==================================================");
        System.out.println(" ");
        System.out.println("Trends on infectious disease in 2023 in " + clinic.getName());
        int locationCount = 0;
        for(Location location : clinic.getLocationList().getLocations()) {
            System.out.println(locationCount++ + ".Location name: " + location.getName());
            System.out.println("------------------------------------------------------------");
            System.out.println("SiteName" + " | " + "Jan." + " | Feb." + " | Mar." + " | Apr."
                    + " | May" + " | June" + " | July" + " | Aug."
                    + " | Sep." + " | Oct." + " | Nov." + " | Dec.");
            System.out.println("------------");
            Map<String, Map<String, Integer>> infectiousPatientsBySite = new HashMap<>();
            Set<String> set = new HashSet<>();
            for (Patient patient: clinic.getPatientDirectory().getPatientsByLocation(location.getName())){
                for (Encounter encounter: patient.getConfirmedEncounters()) {
                    if (encounter.getDiagnosis().getDisease().isInfectious()) {
                        String date = encounter.getEvent().getDate();
                        String month = date.split("/")[0];//01/23/2023
                        String siteName = encounter.getEvent().getSite().getName();
                        String distinctKey = siteName + "-" + month + "-" + patient.getPerson().getPersonId();
                        if (set.contains(distinctKey)) {
                            continue;
                        }
                        set.add(distinctKey);
                        if (!infectiousPatientsBySite.containsKey(siteName)) {
                            Map<String, Integer> infectiousPatientsByMonth = new HashMap<>();
                            // curryCenter -> Map<>
                            infectiousPatientsBySite.put(siteName, infectiousPatientsByMonth);
                        }
                        Map<String, Integer> monthInfectious = infectiousPatientsBySite.get(siteName);
                        if (!monthInfectious.containsKey(month)) {
                            //curryCenter -> Map -> <April ,1>
                            monthInfectious.put(month, 1);
                        } else {
                            //<April, 2>
                            monthInfectious.put(month, monthInfectious.get(month) + 1);
                        }
                    }
                }
            }
            for (String siteName: infectiousPatientsBySite.keySet()) {
                Map<String, Integer> monthMap = infectiousPatientsBySite.get(siteName);
                System.out.println(siteName + " | " + monthMap.get("01") + " | " + monthMap.get("02")
                        + " | " + monthMap.get("03") + " | " + monthMap.get("04")
                        + " | " + monthMap.get("05") + " | " + monthMap.get("06")
                        + " | " + monthMap.get("07") + " | " + monthMap.get("08")
                        + " | " + monthMap.get("09") + " | " + monthMap.get("10")
                        + " | " + monthMap.get("11") + " | " + monthMap.get("12"));
            }
        }
        System.out.println("================================================ End of Report 3 =================================================");
        System.out.println();
    }

}
