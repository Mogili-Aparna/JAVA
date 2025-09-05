import java.util.Scanner;
public class GroceryShopping{
    public static void main(String s[]){
        String[] groceries = {
            "Rice",
            "Wheat Flour",
            "Sugar",
            "Salt",
            "Milk",
            "Eggs",
            "Bread",
            "Butter",
            "Cheese",
            "Tomatoes",
            "Onions",
            "Potatoes",
            "Apples",
            "Bananas",
            "Chicken"
        };
        // Float array with corresponding unit prices
        float[] unitPrice = {
            55.5f,   // Rice
            40.0f,   // Wheat Flour
            48.0f,   // Sugar
            20.0f,   // Salt
            28.5f,   // Milk
            60.0f,   // Eggs
            35.0f,   // Bread
            120.0f,  // Butter
            150.0f,  // Cheese
            30.0f,   // Tomatoes
            25.0f,   // Onions
            22.0f,   // Potatoes
            100.0f,  // Apples
            70.0f,   // Bananas
            250.0f   // Chicken
        };
        double totalBill =0;
        Scanner sc = new Scanner(System.in);
        while(true){
            
            while(true){
                try{
                    System.out.println("Enter the name of the item (or type 'finish' to end shopping):");
                    String inputItem = sc.nextLine();
                    // Check if the user wants to finish shopping
                    if("finish".equalsIgnoreCase(inputItem)){
                        System.out.println("Your total bill is: $" + totalBill);
                        System.out.println("Thank you for shopping with us!");
                        break;
                    }
                    // Find the index of the item in the array
                    int itemIndex = indexOf(inputItem,groceries);
                    if(itemIndex!=-1){
                        // Ask for the quantity of the item
                        System.out.println("Enter the quantity of " + groceries[itemIndex] + ":");
                        int quantity = sc.nextInt();
                        sc.nextLine(); // Consume the newline character
                        // Calculate the cost for the item and add it to the total bill
                        float itemCost = unitPrice[itemIndex] * quantity;
                        totalBill += itemCost;
                        System.out.println("Added " + quantity + " x " + groceries[itemIndex] + " to the bill. Current total: $" + totalBill);
                    }
                    else{
                        // If the item is not found, throw the custom exception
                        throw new ItemNotFoundException("Item '" + inputItem + "' not found. Please try again.");
                    }
                } catch (ItemNotFoundException e) {
                    System.out.println(e.getMessage()); // Print the exception message
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    sc.nextLine(); // Clear the invalid input
                }
            }
            String text = sc.nextLine();
            if("Exit".equalsIgnoreCase(text)){
                System.out.println("Thank you for using the shopping cart. Goodbye!");
                break;
            }
        }
        sc.close();
    }
    public static int indexOf(String searchItem,String [] groceries){
        int index = -1;  // default if not found

        for (int i = 0; i < groceries.length; i++) {
            if (groceries[i].equals(searchItem)) {
                index = i;
                break;
            }
        }
        return index;
    }
}