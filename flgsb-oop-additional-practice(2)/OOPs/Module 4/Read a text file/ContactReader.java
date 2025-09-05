import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;

public class ContactReader{
    public static void main(String[] args){
        System.out.println("Enter the file name : ");
        Scanner scanner = new Scanner(System.in);
        try
        {
            Scanner fileScanner = new Scanner(new FileReader(scanner.nextLine()));
            while(fileScanner.hasNext()){
                System.out.println(fileScanner.nextLine());
            }
        }
        catch(IOException exception){
            System.err.println(exception.getMessage());
        }
    }
}