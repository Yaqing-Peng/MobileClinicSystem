package PatientManagement.TestData;

import PatientManagement.Catalogs.AgeGroup;
import PatientManagement.Catalogs.Limits;
import PatientManagement.Catalogs.VitalSignLimits;
import PatientManagement.Catalogs.VitalSignsCatalog;

import java.util.ArrayList;
import java.util.HashMap;

public class VitalSignsDetail {

    public static VitalSignsCatalog generateGlobalVitalSignsCatalog() {
        VitalSignsCatalog vitalSignsCatalog = new VitalSignsCatalog();

        // age range: [lower, upper)
        AgeGroup infant = vitalSignsCatalog.newAgeGroup("Infant", 3, 0);
        AgeGroup preSchool = vitalSignsCatalog.newAgeGroup("preSchool", 7, 3);
        AgeGroup children = vitalSignsCatalog.newAgeGroup("children", 12, 7);
        AgeGroup adolescent = vitalSignsCatalog.newAgeGroup("adolescent", 18, 12);
        AgeGroup adult = vitalSignsCatalog.newAgeGroup("adult", 60, 18);
        AgeGroup elder = vitalSignsCatalog.newAgeGroup("elder", 100, 60);

        // Limits
        // 1. Add respiratory Rate
        VitalSignLimits respiratoryRate = vitalSignsCatalog.newVitalSignLimits("Respiratory Rate");
        HashMap<AgeGroup, Limits> respiratoryRateLimitsMap = respiratoryRate.getLimitsMap();

        respiratoryRateLimitsMap.put(infant, new Limits(30, 50));
        respiratoryRateLimitsMap.put(preSchool, new Limits(20, 30));
        respiratoryRateLimitsMap.put(children, new Limits(20, 30));
        respiratoryRateLimitsMap.put(adolescent, new Limits(20, 30));
        respiratoryRateLimitsMap.put(adult, new Limits(20, 30));
        respiratoryRateLimitsMap.put(elder, new Limits(12, 20));

        // 2. Add heart rate
        VitalSignLimits heartRate = vitalSignsCatalog.newVitalSignLimits("Heart rate");
        HashMap<AgeGroup, Limits> heartRateLimitsMap = heartRate.getLimitsMap();

        heartRateLimitsMap.put(infant, new Limits(120, 160));
        heartRateLimitsMap.put(preSchool, new Limits(80, 140));
        heartRateLimitsMap.put(children, new Limits(80, 130));
        heartRateLimitsMap.put(adolescent, new Limits(80, 120));
        heartRateLimitsMap.put(adult, new Limits(70, 110));
        heartRateLimitsMap.put(elder, new Limits(55, 105));

        // 3. Add systolic blood pressure
        VitalSignLimits systolicBloodPressure = vitalSignsCatalog.newVitalSignLimits("Systolic blood pressure");
        HashMap<AgeGroup, Limits> systolicBloodPressureLimitsMap = systolicBloodPressure.getLimitsMap();

        systolicBloodPressureLimitsMap.put(infant, new Limits(50, 70));
        systolicBloodPressureLimitsMap.put(preSchool, new Limits(70, 100));
        systolicBloodPressureLimitsMap.put(children, new Limits(80, 110));
        systolicBloodPressureLimitsMap.put(adolescent, new Limits(80, 110));
        systolicBloodPressureLimitsMap.put(adult, new Limits(80, 120));
        systolicBloodPressureLimitsMap.put(elder, new Limits(110, 120));

        // 4. Add weight in kilos
        VitalSignLimits weightInKilos = vitalSignsCatalog.newVitalSignLimits("Weight in kilos");
        HashMap<AgeGroup, Limits> weightInKilosLimitsMap = weightInKilos.getLimitsMap();

        weightInKilosLimitsMap.put(infant, new Limits(2, 3));
        weightInKilosLimitsMap.put(preSchool, new Limits(4, 10));
        weightInKilosLimitsMap.put(children, new Limits(10, 14));
        weightInKilosLimitsMap.put(adolescent, new Limits(14, 18));
        weightInKilosLimitsMap.put(adult, new Limits(20, 42));
        weightInKilosLimitsMap.put(elder, new Limits(50, 120));

        // Add limits to AgeGroup
        ArrayList<AgeGroup> ageGroups = vitalSignsCatalog.getAgeGroups();
        ArrayList<VitalSignLimits> vitalSignLimits = vitalSignsCatalog.getVitalSignLimits();
        for (AgeGroup ageGroup : ageGroups) {
            for (VitalSignLimits vitalSignLimit : vitalSignLimits) {
                ageGroup.addLimits(vitalSignLimit);
            }
        }

        return vitalSignsCatalog;
    }
}
