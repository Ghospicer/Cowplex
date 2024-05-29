package ui;
import java.util.*;

public class Farm {
	
    private int id;
    private String name;
    private String location;
    private List<Animal> animals; 
    private List<String> notes=new ArrayList<>();

	public Farm(int id, String name, String location) {
		this.id=id;
		this.name=name;
		this.location=location;
		this.animals=new ArrayList<>();
	}
	
	public void addNote(String note) {
		notes.add(note);
	}
	
	public List<String> getNotes(){
		return this.notes;
	}
	
	public void addAnimal(Animal id) {
        animals.add(id);
    }

    public void removeAnimal(Animal id) {
        animals.remove(id);
    }
    
    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }
    
    public String getName(){
    	String name = this.name;
    	return name;
    }
    
    public String getLocation(){
    	String location = this.location;
    	return location;
    }
    
    public int getId(){
    	int id = this.id;
    	return id;
    }
    
}