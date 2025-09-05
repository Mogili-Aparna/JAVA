import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * TravelPlanner - A utility to help travelers plan their trips
 * This class provides functionality to:
 * 1. Calculate the duration of a trip
 * 2. Validate travel dates
 * 3. Calculate check-in and check-out dates
 * 4. Check if a trip overlaps with holidays
 */
public class TravelPlanner {
    
    // Step 1: Declare a DateTimeFormatter for the standard date format "dd/MM/yyyy"
    // Hint: Use DateTimeFormatter.ofPattern()
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /**
     * Calculates the duration of a trip in days
     * @param departureDate The date of departure
     * @param returnDate The date of return
     * @return The number of days between departure and return
     */
    public static long calculateTripDuration(LocalDate departureDate, LocalDate returnDate) {
        // Step 2: Calculate and return the number of days between departure and return dates
        // Hint: Use ChronoUnit.DAYS.between()
        
        return ChronoUnit.DAYS.between(departureDate, returnDate); // Replace with actual calculation
    }
    
    /**
     * Validates that the provided dates make logical sense for a trip
     * @param departureDate The date of departure
     * @param returnDate The date of return
     * @return true if dates are valid, false otherwise
     */
    public static boolean validateTravelDates(LocalDate departureDate, LocalDate returnDate) {
        // Step 3: Implement validation logic:
        // - Departure date should not be in the past
        // - Return date should be after departure date
        // - Trip should not be longer than 90 days
        // Hint: Use LocalDate.now() for current date and various comparison methods
        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        if(departureDate.isBefore(currentDate)){
            System.err.println("Departure date should not be in the past");
            return false;
        }
        else if(returnDate.isBefore(departureDate)){
            System.err.println("Return date should be after departure date");
            return false;
        }
        else if(calculateTripDuration(departureDate,returnDate)>90){
            System.err.println("Trip should not be longer than 90 days");
            return false;
        }
        return true; // Replace with actual validation
    }
    
    /**
     * Calculates hotel check-in and check-out dates based on travel dates
     * @param departureDate The date of departure
     * @param returnDate The date of return
     * @return A string containing the check-in and check-out dates
     */
    public static String calculateHotelDates(LocalDate departureDate, LocalDate returnDate){
        // Step 4: Calculate hotel dates:
        // - Check-in is usually the same day as departure
        // - Check-out is usually the same day as return
        // Hint: Format the dates using the formatter declared in Step 1
        
        return "check-in : "+departureDate.format(dtf)+" & Check-out : "+returnDate.format(dtf); // Replace with actual calculation
    }
    
    /**
     * Checks if a trip overlaps with a specific holiday
     * @param departureDate The date of departure
     * @param returnDate The date of return
     * @param holidayDate The date of the holiday
     * @return true if the trip overlaps with the holiday, false otherwise
     */
    public static boolean tripOverlapsHoliday(LocalDate departureDate, LocalDate returnDate, LocalDate holidayDate) {
        // Step 5: Check if the holiday date falls between departure and return dates
        // Hint: Use isAfter() and isBefore() methods or similar
        
        return validateTravelDates(departureDate, returnDate) 
        && departureDate.isBefore(holidayDate) 
        && returnDate.isAfter(holidayDate); // Replace with actual check
    }
    /**
     * Parses a date string into a LocalDate object
     * @param dateStr The date string in format "dd/MM/yyyy"
     * @return The parsed LocalDate
     * @throws DateTimeParseException if the date string cannot be parsed
     */
    private static LocalDate parseDate(String dateStr) throws DateTimeParseException {
        return LocalDate.parse(dateStr, dtf);
    }
    
    /**
     * Main method to run the TravelPlanner application
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Step 6: Implement a menu-driven interface allowing the user to:
        // - Enter departure and return dates
        // - Calculate trip duration
        // - Validate travel dates
        // - Calculate hotel check-in and check-out dates
        // - Check if trip overlaps with holidays
        
        // Step 7: Use try-catch blocks to handle invalid date inputs
        // Hint: Catch DateTimeParseException for invalid date formats
        
        // Step 8: Display appropriate messages to the user based on the operations performed
        
        boolean running = true;
        
        System.out.println("=== Travel Planner Application ===");
        System.out.println("All dates should be entered in format dd/MM/yyyy");
        
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Calculate trip duration");
            System.out.println("2. Validate travel dates");
            System.out.println("3. Calculate hotel check-in and check-out");
            System.out.println("4. Check if trip overlaps with a holiday");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            
            String choice = scanner.nextLine();
            
            // Variables for dates
            LocalDate departureDate = null;
            LocalDate returnDate = null;
            
            try {
                switch (choice) {
                    case "1":
                        // Calculate trip duration
                        System.out.print("Enter departure date (dd/MM/yyyy): ");
                        departureDate = parseDate(scanner.nextLine());
                        
                        System.out.print("Enter return date (dd/MM/yyyy): ");
                        returnDate = parseDate(scanner.nextLine());
                        
                        long duration = calculateTripDuration(departureDate, returnDate);
                        System.out.println("Trip duration: " + duration + " days");
                        break;
                        
                    case "2":
                        // Validate travel dates
                        System.out.print("Enter departure date (dd/MM/yyyy): ");
                        departureDate = parseDate(scanner.nextLine());
                        
                        System.out.print("Enter return date (dd/MM/yyyy): ");
                        returnDate = parseDate(scanner.nextLine());
                        
                        boolean isValid = validateTravelDates(departureDate, returnDate);
                        if (isValid) {
                            System.out.println("Travel dates are valid!");
                        }
                        break;
                        
                    case "3":
                        // Calculate hotel check-in and check-out
                        System.out.print("Enter departure date (dd/MM/yyyy): ");
                        departureDate = parseDate(scanner.nextLine());
                        
                        System.out.print("Enter return date (dd/MM/yyyy): ");
                        returnDate = parseDate(scanner.nextLine());
                        
                        if (validateTravelDates(departureDate, returnDate)) {
                            String hotelDates = calculateHotelDates(departureDate, returnDate);
                            System.out.println(hotelDates);
                        }
                        break;
                        
                    case "4":
                        // Check if trip overlaps with a holiday
                        System.out.print("Enter departure date (dd/MM/yyyy): ");
                        departureDate = parseDate(scanner.nextLine());
                        
                        System.out.print("Enter return date (dd/MM/yyyy): ");
                        returnDate = parseDate(scanner.nextLine());
                        
                        System.out.print("Enter holiday date (dd/MM/yyyy): ");
                        LocalDate holidayDate = parseDate(scanner.nextLine());
                        
                        if (validateTravelDates(departureDate, returnDate)) {
                            boolean overlaps = tripOverlapsHoliday(departureDate, returnDate, holidayDate);
                            if (overlaps) {
                                System.out.println("Your trip overlaps with the holiday!");
                            } else {
                                System.out.println("Your trip does not overlap with the holiday.");
                            }
                        }
                        break;
                        
                    case "5":
                        // Exit
                        running = false;
                        System.out.println("Thank you for using Travel Planner!");
                        break;
                        
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Please use dd/MM/yyyy format.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}
