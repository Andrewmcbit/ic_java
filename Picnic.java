import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Picnic {
    protected String file_name;
    protected HashMap<String, Integer> uniqueObject;

    public Picnic(String file_name){
        this.file_name = file_name;
        uniqueObject = new HashMap<>();
    }

    protected void readFile(){
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file_name))) {
            String[] lines = reader.readLine().split(" ");
            for (int i = 0; i < lines.length; i++) {
                if (uniqueObject.containsKey(lines[i]) == true){
                    uniqueObject.put(lines[i], uniqueObject.get(lines[i])+1);
                } else {
                    uniqueObject.put(lines[i], 1);
                }
            }
            uniqueObject.remove("");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void getData(){
        if (uniqueObject.isEmpty())
            readFile();
        System.out.println("Frequency:");
        System.out.println(uniqueObject.entrySet());
        System.out.println();
    }

    public void findMaxLenghtWord(){
        if (uniqueObject.isEmpty())
            readFile();
        String maxLengthWord = "";
        for (String string : uniqueObject.keySet()) {
            if (maxLengthWord.length() < string.length()){
                maxLengthWord = string;
            }
        }
        System.out.println("First max length word: " + maxLengthWord + "\n");
    }

    public void findPopular(){
        if (uniqueObject.isEmpty())
            readFile();
        System.out.println("Sorted map:");
        uniqueObject.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) 
        .forEach(System.out::println);
        System.out.println();
    }
}