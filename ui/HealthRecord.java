package ui;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HealthRecord{

	private Date date;
	private String healthStatus;
	private String notes;
	private List<String> usedMedicines = new ArrayList<>();
	private List<String> dose = new ArrayList<>();
	
	public HealthRecord(Date date, String healthStatus, String notes,List<String> usedMedicines, List<String> dose) {
		this.date=date;
		this.healthStatus=healthStatus;
		this.notes=notes;
		this.usedMedicines=usedMedicines;
		this.dose=dose;
	}	
	
    public Date getDate() {
        return date;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public String getNotes() {
        return notes;
    }

    public List<String> getUsedMedicines() {
        return usedMedicines;
    }

    public List<String> getDose() {
        return dose;
    }
    
    @Override
    public String toString() {
        String result = "Tarih: " + date.toString() + "\n";
        result += "Sağlık Durumu: " + healthStatus + "\n";
        result += "Notlar: " + notes + "\n";
        result += "Kullanılmış İlaçlar: " + usedMedicines.toString() + "\n";
        result += "Doz: " + dose.toString() + "\n";
        return result;
    }
}

