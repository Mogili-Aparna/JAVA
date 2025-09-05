import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MoodTracker {

    // Date and time formatters
    public static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        ArrayList<Mood> moods = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Press 'a' to add mood\n" +
                    "'d' to delete mood(s)\n" +
                    "'e' to edit mood\n" +
                    "'s' to search for moods\n" +
                    "'M' to get all moods\n" +
                    "'w' to write the moods to a file\n" +
                    "Type 'Exit' to exit");
            String option = sc.nextLine();

            switch (option) {
                case "a": addMood(sc, moods); continue;
                case "d": deleteMood(sc, moods); continue;
                case "e": editMood(sc, moods); continue;
                case "M": getAllMoods(moods); continue;
                case "w": writeAllMoodsToAFile(moods); continue;
                case "s": searchMoods(sc, moods); continue;
                case "Exit":
                    System.out.println("Thanks for using the mood tracker application.");
                    System.exit(1);
                default:
                    System.out.println("Invalid input");
                    continue;
            }
        }
    }

    // Convert string to LocalDate
    public static LocalDate convertStringTodate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, df);
    }

    // Convert string to LocalTime
    public static LocalTime convertStringToTime(String time) throws DateTimeParseException {
        return LocalTime.parse(time, tf);
    }

    // Add a new mood
    public static void addMood(Scanner sc, ArrayList<Mood> moods) {
        try {
            System.out.println("Please Enter Name: ");
            String name = sc.nextLine();

            System.out.println("Are you tracking the mood for a current day? y/n");
            String isForCurrentDate = sc.nextLine();

            Mood mood;
            if (isForCurrentDate.equalsIgnoreCase("n")) {
                // User specifies date and time
                System.out.println("Please Enter date(in format : dd/MM/yyyy): ");
                LocalDate date = convertStringTodate(sc.nextLine());
                System.out.println("Please Enter time(in format : HH:mm:ss): ");
                LocalTime time = convertStringToTime(sc.nextLine());
                System.out.println("Please Enter notes: ");
                String notes = sc.nextLine();

                if (notes.strip().equalsIgnoreCase("")) {
                    mood = new Mood(name, date, time);
                } else {
                    mood = new Mood(name, date, time, notes);
                }
            } else {
                // Use current date/time
                System.out.println("Please Enter notes: ");
                String notes = sc.nextLine();

                if (notes.strip().equalsIgnoreCase("")) {
                    mood = new Mood(name);
                } else {
                    mood = new Mood(name, notes);
                }
            }

            // Check for duplicate mood
            if (moods.contains(mood)) {
                throw new InvalidMoodException("Mood was already tracked for this time and date");
            }

            moods.add(mood);
            System.out.println("The mood has been added to the tracker");

        } catch (DateTimeParseException dtpe) {
            System.out.println("Invalid date / time format! Cannot add mood");
        } catch (InvalidMoodException ime) {
            System.out.println("The mood is not valid: " + ime.getMessage());
        }
    }

    // Delete moods
    public static void deleteMood(Scanner sc, ArrayList<Mood> moods) {
        try {
            System.out.println("Do you want to delete all moods by date or delete a particular mood:\n" +
                    "please enter 1 for deleting moods by date.\n" +
                    "please enter 2 for deleting a mood.\n");
            int option = Integer.parseInt(sc.nextLine());
            boolean areMoodsDeleted = false;

            switch (option) {
                case 1: areMoodsDeleted = delelteAllByDate(sc, moods); break;
                case 2: areMoodsDeleted = delete(sc, moods); break;
                default: return;
            }

            if (areMoodsDeleted) {
                System.out.println("The moods have been deleted");
            } else {
                System.out.println("No matching moods found");
            }
        } catch (NumberFormatException nfe) {
            System.err.println(nfe.getMessage());
        } catch (DateTimeParseException dtpe) {
            System.out.println("Invalid date / time format! Cannot delete mood(s)");
        }
    }

    public static boolean delelteAllByDate(Scanner sc, ArrayList<Mood> moods) {
        System.out.println("Please Enter date(in format : dd/MM/yyyy): ");
        LocalDate date = convertStringTodate(sc.nextLine());
        return moods.removeIf(mood -> mood.getDate().equals(date));
    }

    public static boolean delete(Scanner sc, ArrayList<Mood> moods) {
        System.out.println("Please Enter Name: ");
        String name = sc.nextLine();
        System.out.println("Please Enter date(in format : dd/MM/yyyy): ");
        LocalDate date = convertStringTodate(sc.nextLine());
        System.out.println("Please Enter time(in format : HH:mm:ss): ");
        LocalTime time = convertStringToTime(sc.nextLine());
        return moods.removeIf(mood -> mood.equals(new Mood(name, date, time)));
    }

    // Edit existing mood notes
    public static void editMood(Scanner sc, ArrayList<Mood> moods) {
        try {
            System.out.println("Please Enter Name: ");
            String name = sc.nextLine();
            System.out.println("Please Enter date(in format : dd/MM/yyyy): ");
            LocalDate date = convertStringTodate(sc.nextLine());
            System.out.println("Please Enter time(in format : HH:mm:ss): ");
            LocalTime time = convertStringToTime(sc.nextLine());

            Mood moodToEdit = null;
            // Find the mood in the list
            for (Mood tempMood : moods) {
                if (tempMood.equals(new Mood(name, date, time))) {
                    moodToEdit = tempMood; // point to the existing mood object
                    break;
                }
            }

            if (moodToEdit == null) {
                throw new InvalidMoodException("Invalid input. Couldn't find mood.");
            }

            System.out.println("Please Enter notes: ");
            String notes = sc.nextLine();

            // Update notes only if non-empty
            if (!notes.strip().equalsIgnoreCase("")) {
                moodToEdit.setNotes(notes);
            }

            System.out.println("Updated Mood successfully!");

        } catch (DateTimeParseException dtpe) {
            System.out.println("Invalid date / time format! Cannot edit mood");
        } catch (InvalidMoodException ime) {
            System.out.println("The mood is not valid: " + ime.getMessage());
        }
    }

    // Search moods
    public static void searchMoods(Scanner sc, ArrayList<Mood> moods) {
        try {
            System.out.println("Do you want to search all moods by date or search a particular mood:\n" +
                    "please enter 1 for searching moods by date.\n" +
                    "please enter 2 for searching a mood.\n");
            int option = Integer.parseInt(sc.nextLine());
            boolean foundMood = false;

            switch (option) {
                case 1: foundMood = searchAllByDate(sc, moods); break;
                case 2: foundMood = search(sc, moods); break;
                default: return;
            }

            if (!foundMood) {
                System.out.println("Not Found Mood");
            }
        } catch (NumberFormatException nfe) {
            System.err.println(nfe.getMessage());
        } catch (DateTimeParseException dtpe) {
            System.out.println("Invalid date / time format! Cannot search mood(s)");
        }
    }

    public static boolean searchAllByDate(Scanner sc, ArrayList<Mood> moods) {
        System.out.println("Please Enter date(in format : dd/MM/yyyy): ");
        LocalDate date = convertStringTodate(sc.nextLine());
        boolean found = false;
        for (Mood mood : moods) {
            if (mood.getDate().equals(date)) {
                System.out.println(mood);
                found = true;
            }
        }
        return found;
    }

    public static boolean search(Scanner sc, ArrayList<Mood> moods) {
        Mood mood = null;
        try {
            System.out.println("Please Enter Name: ");
            String name = sc.nextLine();
            System.out.println("Please Enter date(in format : dd/MM/yyyy): ");
            LocalDate date = convertStringTodate(sc.nextLine());
            System.out.println("Please Enter time(in format : HH:mm:ss): ");
            LocalTime time = convertStringToTime(sc.nextLine());

            for (Mood tempMood : moods) {
                if (tempMood.equals(new Mood(name, date, time))) {
                    mood = tempMood; // point to the existing object
                    System.out.println(mood); // print found mood
                    break;
                }
            }

            if (mood == null) {
                throw new InvalidMoodException("Invalid input. Couldn't find mood.");
            }

        } catch (DateTimeParseException dtpe) {
            System.out.println("Invalid date / time format! Cannot search mood");
        } catch (InvalidMoodException ime) {
            System.out.println("The mood is not valid: " + ime.getMessage());
        }

        return true; // true if found
    }

    // Print all moods
    public static void getAllMoods(ArrayList<Mood> moods) {
        for (Mood mood : moods) {
            System.out.println(mood);
        }
    }

    // Write all moods to a file
    public static void writeAllMoodsToAFile(ArrayList<Mood> moods) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("moodtracker.txt"))) {
            for (Mood mood : moods) {
                // Convert mood object to string and write to file
                bufferedWriter.write(mood.toString());
                bufferedWriter.newLine(); // Add a new line after each mood
            }
            System.out.println("All moods have been written to moodtracker.txt");
        } catch (IOException ioe) {
            System.out.println("Error writing to file: " + ioe.getMessage());
        }
    }
}