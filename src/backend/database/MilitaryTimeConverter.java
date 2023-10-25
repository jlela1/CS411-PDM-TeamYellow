package backend.database;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.time.format.DateTimeFormatter;


public class MilitaryTimeConverter {
    private String originalDateTime;
    private String militaryTime;

    public MilitaryTimeConverter() {
        // Get the current date and time in the format MM.dd.hh.mm
        LocalDateTime now = LocalDateTime.now();
        originalDateTime = now.format(DateTimeFormatter.ofPattern("MM.dd.hh.mm"));
        convertToMilitaryTime();
    }

    public String getOriginalDateTime() {
        return originalDateTime;
    }

    public void setOriginalDateTime(String originalDateTime) {
        this.originalDateTime = originalDateTime;
        convertToMilitaryTime();
    }

    public String getMilitaryTime() {
        return militaryTime;
    }

    private void convertToMilitaryTime() {
        // Define the input format
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM.dd.hh.mm");

        try {
            // Parse the original date and time string to a Date object
            Date date = inputFormat.parse(originalDateTime);

            // Define the output format for military time (24-hour format)
            SimpleDateFormat militaryTimeFormat = new SimpleDateFormat("MM.dd.HHmm");

            // Format the Date object in military time format
            militaryTime = militaryTimeFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MilitaryTimeConverter converter = new MilitaryTimeConverter();

        System.out.println("Original Date and Time: " + converter.getOriginalDateTime());
        System.out.println("Military Time: " + converter.getMilitaryTime());
    }
}

