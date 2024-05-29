package ui;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Animal {
	
	private int id;
    private String name;
    private String species;
    private String breed;
    private Date birthDate;
    private List<String> diseases = new ArrayList<>();
    private List<HealthRecord> healthRecords = new ArrayList<>();
    private List<FeedLog> feedLogs = new ArrayList<>();
    private List<MilkingSession> milkingSessions = new ArrayList<>();
    private boolean gebe = false;
    private boolean düve = true;
    private boolean cinsiyet;

    public Animal(int id, String name, String species, String breed, Date birth_date, boolean cinsiyet) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.birthDate = birth_date;
        this.cinsiyet=cinsiyet;
        if (cinsiyet==false) {
        	düve=false;
        	gebe=false;
        }
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    
    public List<String> getDiseases() {
        return new ArrayList<>(diseases);
    }

    public List<HealthRecord> getHealthRecords() {
    	
        return new ArrayList<>(healthRecords);
    }

    public List<FeedLog> getFeedLogs() {
    	 return new ArrayList<>(feedLogs);
    }

    public List<MilkingSession> getMilkingSessions() {
    	 return new ArrayList<>(milkingSessions);
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setBirthDate(Date birth_date) {
        this.birthDate = birth_date;
    }

    public void addDisease(String hastalık) {
        this.diseases.add(hastalık);
    }

    public void addHealthRecord(HealthRecord healthRecord) {
        this.healthRecords.add(healthRecord);
    }

    public void addFeedLog(FeedLog feedLog) {
        this.feedLogs.add(feedLog);
    }

    public void addMilkingSession(MilkingSession milkingSession) {
        this.milkingSessions.add(milkingSession);
    }

    public void removeDisease(String hastalık) {
        this.diseases.remove(hastalık);
    }
    
    public boolean isGebe() {
        return gebe;
    }

    public void setGebe(boolean gebe) {
        this.gebe = gebe;
    }

    public boolean isDuve() {
        return düve;
    }

    public void setDuve(boolean düve) {
        this.düve = düve;
    }
    
    public boolean isCinsiyet() {
        return cinsiyet;
    }
}