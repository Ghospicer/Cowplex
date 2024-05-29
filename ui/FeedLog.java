package ui;

import java.util.Date;

public class FeedLog {

    private Date date;
    private String feedType;
    private float quantity;

    public FeedLog(Date date, String feedType, float quantity) {
        this.date = date;
        this.feedType = feedType;
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public String getFeedType() {
        return feedType;
    }

    public float getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        String result = "Tarih: " + date.toString() + "\n";
        result += "Yem Tipi: " + feedType + "\n";
        result += "Miktar(kg): " + quantity + "\n";
        return result;
    }
}
