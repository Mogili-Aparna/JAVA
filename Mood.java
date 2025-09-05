import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class MoodTracker{
    public static void main(String[] args){
        ArrayList<Mood> moods = new ArrayList<Mood>();
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
        while(true){
            System.out.println("Press 'a' to add mood\n" +
                                "'d' to delete mood(s)\n" +
                                "'e' to edit mood\n" +
                                "'s' to search for moods\n" +
                                "'M' to get all moods\n" +
                                "'w' to write the moods to a file\n" +
                                "Type 'Exit' to exit");
            String option = sc.nextLine();
            switch(option){
                case "a":addMood(sc,moods);continue;
                case "d":deleteMood(sc,moods);continue;
                case "e":editMood(sc,moods);continue;
                case "M":getAllMoods(sc,moods);continue;
                case "w":writeAllMoodsToAFile(sc,moods);continue;
                case "s":searchMoods(sc,moods);continue;
                case "Exit" : 
                    System.out.println("Thanks for using the mood tracker application.");
                    System.exit(1);
                default :
                    System.out.println("Invalid input");
                    continue;
                }
        }
    }
    public static LocalDate convertStringTodate(String date) throws DateTimeParseException{
            return LocalDate.parse(date,df);
    }
    public static LocalTime convertStringToTime(String time) throws DateTimeParseException{
            return LocalTime.parse(time,tf);
    }
    public static void addMood(Scanner sc, ArrayList<Mood> moods){
        try{
            System.out.println("Please Enter Name: ");
            String name = sc.nextLine();
            System.out.println("Are you tracking the mood for a current day? y/n");
            String isForCurrentDate = scanner.nextLine();
            Mood mood;
            if(isForCurrentDate.equalsIgnoreCase("n")) {
                System.out.println("Please Enter date(in format : dd/mm/yyyy): ");
                LocalDate date = convertStringTodate(sc.nextLine());
                System.out.println("Please Enter time(in format : HH:mm): ");
                String time = convertStringToTime(sc.nextLine());
                System.out.println("Please Enter notes: ");
                String notes = sc.nextLine();
                if(notes.strip().equalsIgnoreCase("")) {
                    mood = new Mood(name, date, time);
                } else {
                    mood = new Mood(name,date,time,notes);
                }
            }
            else{
                System.out.println("Please Enter notes: ");
                String notes = sc.nextLine();
                if(notes.strip().equalsIgnoreCase("")) {
                    mood = new Mood(name);
                } else {
                    mood = new Mood(name, notes);
                }
            }
            if(moods.contains(mood)){
                throw new InvalidMoodException("Mood was already tracked for this time and date");
            }
            moods.add(mood);
            System.out.println("The mood has been added to the tracker");
        }
        catch(DateTimeParseException dtpe){
            System.out.println("Invalid date / time format!.Cannot add mood");
        }
        catch(InvalidMoodException ime) {
            System.out.println("The mood is not valid : "+ime.getMessage());
        }
    }
    public static void deleteMood(Scanner sc , ArrayList<Mood> moods){
        try{
            System.out.println("Do you want to delete all moods by date or delete a particular mood:\n"
                                    +" please enter 1 for deleting moods by date.\n"
                                    +" please enter 2 for deleting a mood.\n");
            int option = Integer.parseInt(sc.nextLine());
            boolean areMoodsDeleted = false;
            switch(option){
                case 1 : areMoodsDeleted = delelteAllByDate(sc,moods);continue;
                case 2 : areMoodsDeleted = delete(sc,moods);continue;
                default: return;
            }   
            if(areMoodsDeleted) {
                System.out.println("The moods have been deleted");
            } else {
                System.out.println("No matching moods found");
            }
        }
        catch(NumberFormatExcpetion nfe){
            System.err.println(nfe.getMessage());
        }
        catch(DateTimeParseException dtpe){
            System.out.println("Invalid date / time format!.Cannot delete mood(s)");
        }
    }
    public static boolean delelteAllByDate(Scanner sc,ArrayList<Mood> moods){
        System.out.println("Please Enter date(in format : dd/mm/yyyy): ");
        LocalDate date = convertStringTodate(sc.nextLine());
        return moods.removeIf(mood -> mood.getDate().equals(date));
    }
    public static boolean delete(Scanner sc,ArrayList<Mood> moods){
        System.out.println("Please Enter your Name: ");
        String name = sc.nextLine();
        System.out.println("Please Enter date(in format : dd/mm/yyyy): ");
        LocalDate date = convertStringTodate(sc.nextLine());
        System.out.println("Please Enter time(in format : HH:mm): ");
        String time = convertStringToTime(sc.nextLine());
        return moods.removeIf(mood->mood.equals(new Mood(name,date,time)));
    }
    public static void editMood(Scanner sc,ArrayList<Mood> moods){
        try{
            System.out.println("Please Enter your Name: ");
            String name = sc.nextLine();
            System.out.println("Please Enter date(in format : dd/mm/yyyy): ");
            LocalDate date = convertStringTodate(sc.nextLine());
            System.out.println("Please Enter time(in format : HH:mm): ");
            String time = convertStringToTime(sc.nextLine());
            Mood mood,moodToBeFound =new Mood(name,date,time);
            for(Mood tempMood: moodsList) {
                if (tempMood.equals(moodToBeFound)) {
                    mood = moodToBeFound;
                }
            }
            if(mood == null){
                throw new InvalidMoodException("Invalid input.couldn't find mood.");
            }
            System.out.println("Please Enter notes: ");
            String notes = sc.nextLine();
            if(notes.strip().equalsIgnoreCase("")) {
                mood.setNotes(notes);
            }
            System.out.println("Updated Mood successfully!");
        }
        catch(DateTimeParseException dtpe){
            System.out.println("Invalid date / time format!.Cannot delete mood(s)");
        }
    }
    public static void searchMood(Scanner sc,ArrayList<Moods> mood){
        System.out.println("Do you want to search all moods by date or search a particular mood:\n"
                                    +" please enter 1 for searching moods by date.\n"
                                    +" please enter 2 for searching a mood.\n");
            int option = Integer.parseInt(sc.nextLine());
            Mood foundMood = false;
            switch(option){
                case 1 : foundMood = searchAllByDate(sc,moods);continue;
                case 2 : foundMood = search(sc,moods);continue;
                default: return;
            }   
            if(!foundMood) {
                System.out.println("Not Found Mood");
            }
        }
        catch(NumberFormatExcpetion nfe){
            System.err.println(nfe.getMessage());
        }
        catch(DateTimeParseException dtpe){
            System.out.println("Invalid date / time format!.Cannot delete mood(s)");
        }
    }
    public static boolean searchAllByDate(Scanner sc, ArrayList<Mood> moods){
        System.out.println("Please Enter date(in format : dd/mm/yyyy): ");
        LocalDate date = convertStringTodate(sc.nextLine());
        boolean found = false;
        for (Mood mood : moods) {
            if(mood.getDate().equals(date)){
                System.out.println(mood);
                found = true;
            }
        }
        return found;
    }
    public static boolean search(Scanner sc, ArrayList<Mood> moods){
        Mood mood =null;
        try{
            System.out.println("Please Enter your Name: ");
            String name = sc.nextLine();
            System.out.println("Please Enter date(in format : dd/mm/yyyy): ");
            LocalDate date = convertStringTodate(sc.nextLine());
            System.out.println("Please Enter time(in format : HH:mm): ");
            String time = convertStringToTime(sc.nextLine());
            Mood moodToBeFound =new Mood(name,date,time);
            for(Mood tempMood: moodsList) {
                if (tempMood.equals(moodToBeFound)) {
                    mood = moodToBeFound;
                }
            }
            if(mood == null){
                throw new InvalidMoodException("Invalid input.couldn't find mood.");
            }
        }
        catch(DateTimeParseException dtpe){
            System.out.println("Invalid date / time format!.Cannot delete mood(s)");
        }
        return mood == null;
    }

}