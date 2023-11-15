package backend.database;

public class timeOfArrival {
    private String time;
    private int rate;

    public timeOfArrival(String time) {
        this.time = time;

    }

    public String getTime() {return time;}
    public void setTime(String newTime) {time = newTime;}
}
