package backend.database;

public class numVehEnteringRate {
    private int time;
    private int rate;

    public numVehEnteringRate(int time, int rate) {
        this.time = time;
        this.rate = rate;
    }

    public int getRate() {return rate;}
    public void setRate(int newRate) {rate = newRate;}
    
    public int getTime() {return time;}
    public void setTime(int newTime) {time = newTime;}
}
