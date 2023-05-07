package PatientManagement.TestData;

import PatientManagement.Patient.Encounters.Disease;
import com.github.javafaker.Faker;

public class Constants {

    public final static Faker FAKER = new Faker();

    public final static String START_DATE = "01/01/2023";

    public final static Integer MAX_AGE = 100;

    public final static Integer MAX_SITE_NUM_EACH_LOCATION = 10;

    public final static Integer MAX_ALLERGY_COUNT = 5;

    public final static Integer MAX_VACCINATION_COUNT = 3;

    public final static String[] THIRD_PARTIES = {"Mental health center", "Local hospital", "Emergency Room", "No need to Transfer"};

    public final static String[] ALLERGY_THING = {"peanut", "flower powder", "dusk", "coca cola", "cake", "apple", "cat fur"};

    public final static String[] VACCINATIONS = {"hiv", "influenza", "COVID-19"};

    public static Disease[] DISEASES = {
            new Disease("Depression", false, true, THIRD_PARTIES[0]),
            new Disease("Surgery", false, true, THIRD_PARTIES[1]),
            new Disease("Heart Attack", false, true, THIRD_PARTIES[2]),
            new Disease("Covid-19", true, false, THIRD_PARTIES[3]),
            new Disease("HIV", true, false, THIRD_PARTIES[3]),
            new Disease("Malaria", true, false, THIRD_PARTIES[3]),
            new Disease("Dental Disease", false, false, THIRD_PARTIES[3]),
            new Disease("Nasal Disease", false, false, THIRD_PARTIES[3]),
            new Disease("Eye Disease", false, false, THIRD_PARTIES[3])
    };


}
