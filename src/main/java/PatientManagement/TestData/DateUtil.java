package PatientManagement.TestData;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final Faker faker = new Faker();

    public static String generateRandomDate(String startDate) {
        String[] startDates = startDate.split("/");
        LocalDate date = LocalDate.of(Integer.parseInt(startDates[2]),
                        Integer.parseInt(startDates[0]),
                        Integer.parseInt(startDates[1]))
                .plusDays(faker.random().nextInt(3));
        return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

}
