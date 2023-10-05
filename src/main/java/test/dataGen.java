package test;
import backend.database.vehicle;

public class dataGen {

    public static vehicle genVehicle(String id, int time, int parking_duration) {

        //calculate outTime
        //time will be between 0000 and 2400hrs
        int outTime = time; //temp mutable time duration variable
        int dur = parking_duration; //temp mutable duration variable

        if (parking_duration >= 60) {
            while (dur >= 60) {

                if (time + 100 >= 2400) { //check if past midnight
                    int timeTo2400 = 2400 - outTime;
                    dur -= timeTo2400;
                    outTime = 0;
                    continue;
                }

                outTime += 100;
                dur -= 60;
            }

            outTime += dur; //add remaining minutes

        } else {
            if (time + parking_duration >= 2400) { //check if past midnight
                int timeTo2400 = 2400 - time;
                dur -= timeTo2400;
                outTime = 0 + dur;
            } else {
                outTime = time + parking_duration;
            }
        }

        vehicle newVehicle = new vehicle(id, time, outTime);
        return newVehicle;
    }


}
