package backend.database.Presets;
import backend.database.MilitaryTimeConverter;
import backend.database.parkingStructure;
import java.util.ArrayList;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class NormalDay {
    /* to access a certain garage, simply call the garage ex:
    NormalDay normalDay = new NormalDay();
    parkingStructure garage = normalDay.G1.getDay;
    garage.setTotal_capacity(100); // Modify a public property of G1

    time to park, time parked, and cars per minute are all public for easy access */
    public int vehiclesPerMin = 40;
    public int timeToPark = 5;
    public int timeParked = 30;
    Random rand = new Random(); // random number to distinguish between runs in SQL
    int upperbound = 5208;
    int random_num = rand.nextInt(upperbound);
    Random rand2 = new Random();
    int upper = 12;
    int random = rand2.nextInt(upper); //make a random month
    String[] Months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    Random dayRand = new Random();
    int upperDay = 28;
    int RandomDay = dayRand.nextInt(upperDay); // get a random day
    public ArrayList<parkingStructure> garageList = new ArrayList<parkingStructure>();
    MilitaryTimeConverter converter = new MilitaryTimeConverter();
    public String long_date = converter.getMilitaryTime();
    public parkingStructure G1 = new parkingStructure("43rd & Elkhorn Ave", 655, 0, "", random_num, 0, Months[random], RandomDay, "", long_date);
    public parkingStructure G2 = new parkingStructure("Constant Center South", 1535, 0, "", random_num, 0, Months[random], RandomDay, "", long_date);
    public parkingStructure G3 = new parkingStructure("Constant Center North", 1045, 0, "", random_num, 0, Months[random], RandomDay, "", long_date);
    public parkingStructure G4 = new parkingStructure("49th Street Stadium ", 745, 0, "", random_num, 0, Months[random], RandomDay, "", long_date);
    public parkingStructure G5 = new parkingStructure("43rd & Bluestone Ave", 1535, 0, "", random_num, 0, Months[random], RandomDay, "", long_date);

    public void setArray() {
        //set list with all garages
        garageList.add(0,G1);
        garageList.add(1,G2);
        garageList.add(2,G3);
        garageList.add(3,G4);
        garageList.add(4,G5);

//print out all garages by looping
        for (int i = 0; i < 5; i++) {
            System.out.println("Simulation Number: " + garageList.get(i).getSimulationNumber());
            System.out.println("Time: " + garageList.get(i).getTime());
            System.out.println("Garage ID: " + garageList.get(i).getGarage_id());
            System.out.println("Current Occupancy: " + garageList.get(i).getCurrent_capacity());
            System.out.println("Capacity: " + garageList.get(i).getTotal_capacity());
            System.out.println("Notification: " + garageList.get(i).getNotification());
            System.out.println("Clock Time: " + garageList.get(i).getClockTime());
            System.out.println("Month: " + garageList.get(i).getMonth());
            System.out.println("Day: " + garageList.get(i).getDay());
            System.out.println();

        }
    }
    public static void main(String[] args){
        NormalDay obj = new NormalDay();
        obj.setArray();
        System.out.println(obj.G1.getLong_date());

    }
}

