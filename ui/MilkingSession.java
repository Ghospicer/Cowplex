package ui;

import java.util.Date;

public class MilkingSession {

    private Date date;
    private float quantity;
    private int duration;

    public MilkingSession(Date date, float quantity, int duration) {
        this.date = date;
        this.quantity = quantity;
        this.duration = duration;
    }
    

    public Date getDate() {
        return date;
    }

    public float getQuantity() {
        return quantity;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        String result = "Tarih: " + date.toString() + "\n";
        result += "Miktar(lt): " + quantity + "\n";
        result += "Süre: " + duration + " Dakika\n";
        return result;
    }
}
