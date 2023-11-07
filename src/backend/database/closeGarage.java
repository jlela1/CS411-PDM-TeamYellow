package backend.database;

public class closeGarage {

    private int time;
    private boolean closed;

    public closeGarage(int time, boolean closed) {
        this.time = time;
        this.closed = closed;
    }

    public boolean getClosed() {return closed;}
    public void setClosed(boolean newClosed) {this.closed = newClosed;}

    public int getTime() {return time;}
    public void setTime(int newTime) {time = newTime;}

}
