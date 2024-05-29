package ui;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map.Entry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class fileReader {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public static List<User> readUsersFromFile(String filePath) {
		
        String projectRoot = System.getProperty("user.dir");

        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath.toString()))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                String email = values[2];
                char[] password = values[3].toCharArray();
                String contactInfo = values[4];
                String userType = values[5];

                User user = new User(id, name, email, password, contactInfo, userType);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    //Read farms from file
    public static List<Farm> readFarmsFromFile(String filePath) {
    	
        String projectRoot = System.getProperty("user.dir");

        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();
        List<Farm> farms = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath.toString()))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                String location = values[2];
                Farm farm = new Farm(id, name, location);
                farms.add(farm);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return farms;
    }

 //Read Animals from file
    public static HashMap<Integer, Animal> readAnimalsFromFile(String filePath) {
    	
        String projectRoot = System.getProperty("user.dir");

        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();
        HashMap<Integer, Animal> animalsMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int farmId = Integer.parseInt(parts[0]);
                int id = Integer.parseInt(parts[1]);
                String name = parts[2];
                String species = parts[3];
                String breed = parts[4];
                Date birthDate = dateFormat.parse(parts[5]);
                boolean cinsiyet = Boolean.parseBoolean(parts[6]);
                Animal animal = new Animal(id, name, species, breed, birthDate,cinsiyet);
                // Add more attributes if needed
                animalsMap.put(farmId, animal);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return animalsMap;
    }
    
    public static Map<Integer, Set<MilkingSession>> readMilkingSessionsFromFile(String filePath) {
    	
    	String projectRoot = System.getProperty("user.dir");

        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();
        
        Map<Integer, Set<MilkingSession>> milkingSessionsMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int animalId = Integer.parseInt(parts[0]);
                Date date = dateFormat.parse(parts[1]);
                float quantity = Float.parseFloat(parts[2]);
                int duration = Integer.parseInt(parts[3]);
                
                MilkingSession milkingSession = new MilkingSession(date, quantity, duration);
                
                if (!milkingSessionsMap.containsKey(animalId)) {
                    milkingSessionsMap.put(animalId, new HashSet<>());
                }
                milkingSessionsMap.get(animalId).add(milkingSession);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return milkingSessionsMap;
    }
    
    public static Map<Integer, Set<FeedLog>> readFeedLogsFromFile(String filePath) {
        
    	String projectRoot = System.getProperty("user.dir");

        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();

        Map<Integer, Set<FeedLog>> feedLogsMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int animalId = Integer.parseInt(parts[0]);
                Date date = dateFormat.parse(parts[1]);
                String feedType = parts[2];
                float quantity = Float.parseFloat(parts[3]);

                FeedLog feedLog = new FeedLog(date, feedType, quantity);

                if (!feedLogsMap.containsKey(animalId)) {
                    feedLogsMap.put(animalId, new HashSet<>());
                }
                feedLogsMap.get(animalId).add(feedLog);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return feedLogsMap;
    }

    public static Map<Integer, Set<HealthRecord>> readHealthRecordsFromFile(String filePath) {
       
    	String projectRoot = System.getProperty("user.dir");

        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();

        Map<Integer, Set<HealthRecord>> healthRecordsMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int animalId = Integer.parseInt(parts[0]);
                Date date = dateFormat.parse(parts[1]);
                String healthStatus = parts[2];
                String notes = parts[3];
                List<String> usedMedicines = Arrays.asList(parts[4].split(";"));
                List<String> dose = Arrays.asList(parts[5].split(";"));

                HealthRecord healthRecord = new HealthRecord(date, healthStatus, notes, usedMedicines, dose);

                if (!healthRecordsMap.containsKey(animalId)) {
                    healthRecordsMap.put(animalId, new HashSet<>());
                }
                healthRecordsMap.get(animalId).add(healthRecord);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return healthRecordsMap;
    }


    public static Map<Integer, Set<String>> readDiseasesFromFile(String filePath) {
    	
    	String projectRoot = System.getProperty("user.dir");

        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();

        Map<Integer, Set<String>> diseasesMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int animalId = Integer.parseInt(parts[0]);
                String disease = parts[1];

                if (!diseasesMap.containsKey(animalId)) {
                    diseasesMap.put(animalId, new HashSet<>());
                }
                diseasesMap.get(animalId).add(disease);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diseasesMap;
    }
    
    public static Map<Integer, Set<String>> readNotesFromFile(String filePath) {
    	
    	String projectRoot = System.getProperty("user.dir");

        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();

        
        Map<Integer, Set<String>> notesMap = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int farmId = Integer.parseInt(parts[0]);
                String note = parts[1];
                
                if (!notesMap.containsKey(farmId)) {
                	notesMap.put(farmId, new HashSet<>());
                }
                notesMap.get(farmId).add(note);
            }
            }
         catch (IOException e) {
            e.printStackTrace();
        }

        return notesMap;
    }

    // Write to CSV file
    public static void writeNotesToFile(String filePath, Map<Integer, Set<String>> notesMap) {
    	String projectRoot = System.getProperty("user.dir");
        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath.toString()))) {
            for (Map.Entry<Integer, Set<String>> entry : notesMap.entrySet()) {
                for (String note : entry.getValue()) {
                    bw.write(entry.getKey() + "," + note);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void writeUsersToFile(String filePath, List<User> users) {
        String projectRoot = System.getProperty("user.dir");
        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath.toString()))) {
            bw.write("id,name,email,password,contactInfo,userType");
            bw.newLine();
            for (User user : users) {
                bw.write(user.getId() + "," + user.getName() + "," + user.getEmail() + "," + new String(user.getPassword()) + "," + user.getContactInfo() + "," + user.getUserType());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFarmsToFile(String filePath, List<Farm> farms) {
        String projectRoot = System.getProperty("user.dir");
        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath.toString()))) {
            bw.write("id,name,location");
            bw.newLine();
            for (Farm farm : farms) {
                bw.write(farm.getId() + "," + farm.getName() + "," + farm.getLocation());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAnimalsToFile(String filePath, Map<Integer, Animal> animals) {
        String projectRoot = System.getProperty("user.dir");
        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath.toString()))) {
            for (Map.Entry<Integer, Animal> entry : animals.entrySet()) {
                Animal animal = entry.getValue();
                bw.write(entry.getKey() + "," + animal.getId() + "," + animal.getName() + "," + animal.getSpecies() + "," + animal.getBreed() + "," + dateFormat.format(animal.getBirthDate()) + "," + animal.isCinsiyet());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void writeMilkingSessionsToFile(String filePath, Map<Integer, Set<MilkingSession>> milkingSessionsMap) {
        String projectRoot = System.getProperty("user.dir");
        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath.toString()))) {
            for (Map.Entry<Integer, Set<MilkingSession>> entry : milkingSessionsMap.entrySet()) {
                for (MilkingSession milkingSession : entry.getValue()) {
                    bw.write(entry.getKey() + "," + dateFormat.format(milkingSession.getDate()) + "," + milkingSession.getQuantity() + "," + milkingSession.getDuration());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFeedLogsToFile(String filePath, Map<Integer, Set<FeedLog>> feedLogsMap) {
        String projectRoot = System.getProperty("user.dir");
        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath.toString()))) {
            for (Map.Entry<Integer, Set<FeedLog>> entry : feedLogsMap.entrySet()) {
                for (FeedLog feedLog : entry.getValue()) {
                    bw.write(entry.getKey() + "," + dateFormat.format(feedLog.getDate()) + "," + feedLog.getFeedType() + "," + feedLog.getQuantity());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeHealthRecordsToFile(String filePath, Map<Integer, Set<HealthRecord>> healthRecordsMap) {
        String projectRoot = System.getProperty("user.dir");
        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath.toString()))) {
            for (Map.Entry<Integer, Set<HealthRecord>> entry : healthRecordsMap.entrySet()) {
                for (HealthRecord healthRecord : entry.getValue()) {
                    bw.write(entry.getKey() + "," + dateFormat.format(healthRecord.getDate()) + "," + healthRecord.getHealthStatus() + "," + healthRecord.getNotes() + "," + String.join(";", healthRecord.getUsedMedicines()) + "," + String.join(";", healthRecord.getDose()));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeDiseasesToFile(String filePath, Map<Integer, Set<String>> diseasesMap) {
        String projectRoot = System.getProperty("user.dir");
        Path absolutePath = Paths.get(projectRoot, "src", filePath).toAbsolutePath();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath.toString()))) {
            for (Map.Entry<Integer, Set<String>> entry : diseasesMap.entrySet()) {
                for (String disease : entry.getValue()) {
                    bw.write(entry.getKey() + "," + disease);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeEverythingToFile(String filePath, List<User> users, List<Farm> farms) {
    	
    	Map<Integer, Animal> animalMap = new HashMap<>();
    	Map<Integer, Set<MilkingSession>> milkingSessionsMap= new HashMap<>();
    	Set<MilkingSession> milkingSessions;
    	Map<Integer, Set<HealthRecord>> healthRecordsMap= new HashMap<>();
    	Set<HealthRecord> healthRecords;
    	Map<Integer, Set<String>> diseasesMap = new HashMap<>();
    	Set<String> diseases;
    	Map<Integer, Set<FeedLog>> feedLogsMap= new HashMap<>();
    	Set<FeedLog> feedLogs;
    	Map<Integer, Set<String>> notesMap = new HashMap<>();
    	Set<String> notes;
    	
        for (Farm farm : farms) {
        	notes = new HashSet<>();
        	for(String note: farm.getNotes()) {
        		notes.add(note);
        	}
        	notesMap.put(farm.getId(), notes);
        	
            for(Animal animal: farm.getAnimals()) {
            	animalMap.put(farm.getId(),animal);
            	milkingSessions = new HashSet<>();
            	healthRecords = new HashSet<>();
            	diseases = new HashSet<>();
            	feedLogs= new HashSet<>();
            	
            	for(FeedLog feedLog: animal.getFeedLogs()) {
            		feedLogs.add(feedLog);
            	}
            	for(String disease: animal.getDiseases()) {
            		diseases.add(disease);
            	}
            	for(HealthRecord healthRecord: animal.getHealthRecords()) {
            		healthRecords.add(healthRecord);
            	}
            	for (MilkingSession milkingSession: animal.getMilkingSessions()) {
            		milkingSessions.add(milkingSession);
            	}
            	feedLogsMap.put(animal.getId(), feedLogs);
            	milkingSessionsMap.put(animal.getId(), milkingSessions);
            	healthRecordsMap.put(animal.getId(), healthRecords);
            	diseasesMap.put(animal.getId(), diseases);
            }
        }
        writeNotesToFile(filePath + "/notes.csv", notesMap);
    	writeUsersToFile(filePath + "/users.csv", users);
        writeFarmsToFile(filePath + "/farms.csv", farms);
        writeAnimalsToFile(filePath + "/animals.csv", animalMap);
        writeMilkingSessionsToFile(filePath + "/milkingSessions.csv", milkingSessionsMap);
        writeHealthRecordsToFile(filePath + "/healthRecords.csv", healthRecordsMap);
        writeDiseasesToFile(filePath + "/diseases.csv", diseasesMap);
		writeFeedLogsToFile(filePath + "/feedLogs.csv", feedLogsMap);
    }

    
    public static void readEverythingFromFile(String filePath) {
        CowplexUI.users = readUsersFromFile(filePath + "/users.csv");
        CowplexUI.farms = readFarmsFromFile(filePath + "/farms.csv");
        Map<Integer, Animal> animalsTemp = readAnimalsFromFile(filePath + "/animals.csv");
        Map<Integer, Set<MilkingSession>> milkingSessionsMap = readMilkingSessionsFromFile(filePath + "/milkingSessions.csv");
        Map<Integer, Set<HealthRecord>> healthRecordsMap = readHealthRecordsFromFile(filePath + "/healthRecords.csv");
        Map<Integer, Set<String>> diseasesMap = readDiseasesFromFile(filePath + "/diseases.csv");
        Map<Integer, Set<FeedLog>> feedLogMap = readFeedLogsFromFile(filePath + "/feedLogs.csv");
        Map<Integer, Set<String>> notesMap = readNotesFromFile(filePath+"/notes.csv");
        
        for(Farm farm: CowplexUI.farms) {
        	if(notesMap.containsKey(farm.getId())) {
        		for(String note: notesMap.get(farm.getId())) {
        			farm.addNote(note);
        		}
        	}
        }
        
        for (Entry<Integer, Animal> entry : animalsTemp.entrySet()) {
            Animal animal = entry.getValue();
            int animalId = animal.getId();

            if (diseasesMap.containsKey(animalId)) {
                for (String disease : diseasesMap.get(animalId)) {
                    animal.addDisease(disease);
                }
            }

            if (feedLogMap.containsKey(animalId)) {
                for (FeedLog feedLog : feedLogMap.get(animalId)) {
                    animal.addFeedLog(feedLog);
                }
            }

            if (healthRecordsMap.containsKey(animalId)) {
                for (HealthRecord healthRecord : healthRecordsMap.get(animalId)) {
                    animal.addHealthRecord(healthRecord);
                }
            }

            if (milkingSessionsMap.containsKey(animalId)) {
                for (MilkingSession milkingSession : milkingSessionsMap.get(animalId)) {
                    animal.addMilkingSession(milkingSession);
                }
            }

            for (Farm farm : CowplexUI.farms) {
                if (entry.getKey().equals(farm.getId())) {
                    farm.addAnimal(animal);
                    break;
                }
            }
        }
    }

    
    
}
