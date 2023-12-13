package backend.database;

public class recommendation {
    private String recommendedGarage;

    private String occPercentOnArrival;

    public recommendation(String recommendedGarage, String occPercentOnArrival) {
        this.recommendedGarage = recommendedGarage;
        this.occPercentOnArrival = occPercentOnArrival;
    }

    public String getOccPercentOnArrival() {
        return occPercentOnArrival;
    }

    public void setOccPercentOnArrival(String occPercentOnArrival) {
        this.occPercentOnArrival = occPercentOnArrival;
    }

    public String getRecommendedGarage() {
        return recommendedGarage;
    }

    public void setRecommendedGarage(String recommendedGarage) {
        this.recommendedGarage = recommendedGarage;
    }

}
