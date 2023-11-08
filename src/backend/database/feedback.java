package backend.database;

public class feedback {

    private int overallRating; //1 - 5 5 being best
    private boolean foundLocation;
    private boolean parkAtRecommendation;
    private boolean happy;
    private boolean wouldRecommend;

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public boolean getFoundLocation() {
        return foundLocation;
    }

    public void setFoundLocation(boolean foundLocation) {
        this.foundLocation = foundLocation;
    }

    public boolean getParkAtRecommendation() {
        return parkAtRecommendation;
    }

    public void setParkAtRecommendation(boolean parkAtRecommendation) {
        this.parkAtRecommendation = parkAtRecommendation;
    }

    public boolean getHappy() {
        return happy;
    }

    public void setHappy(boolean happy) {
        this.happy = happy;
    }

    public boolean getWouldRecommend() {
        return wouldRecommend;
    }

    public void setWouldRecommend(boolean wouldRecommend) {
        this.wouldRecommend = wouldRecommend;
    }


}
