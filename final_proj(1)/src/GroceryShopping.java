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
        // Stock array (parallel to groceries and unitPrices, must be 15 elements)
        int[] stock = {
            10, 20, 15, 50, 30,   // Rice to Milk
            100, 25, 40, 35,      // Eggs to Onions
            45, 60, 20, 30, 25    // Potatoes to Chicken
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
                        applyDiscount(totalBill);
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
                        stock[itemIndex]-=quantity;
                        if(stock[itemIndex]<0){
                            stock[itemIndex]+=quantity;
                            throw new OutOfStock("Item "+groceries[itemIndex]+" is Out of Stock.Currently Available quantity : "+stock[itemIndex]);
                        }
                        // Calculate the cost for the item and add it to the total bill
                        float itemCost = unitPrice[itemIndex] * quantity;
                        totalBill += itemCost;
                        System.out.println("Remaining stock of " + groceries[itemIndex] + ": " + stock[itemIndex]);
                        System.out.println("Added " + quantity + " x " + groceries[itemIndex] + " to the bill. Current total: $" + totalBill);
                    }
                    else{
                        // If the item is not found, throw the custom exception
                        throw new ItemNotFoundException("Item '" + inputItem + "' not found. Please try again.");
                    }
                } catch (ItemNotFoundException e) {
                    System.out.println(e.getMessage()); // Print the exception message
                }catch(OutOfStock ofse){
                    System.out.println(ofse.getMessage());
                    sc.nextLine(); // Clear the invalid input
                }
                 catch (Exception e) {
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
        try{
            searchItem("Capsicum",groceries);
            searchItem("Chicken",groceries);
            searchItem("rice",groceries);
            searchItem("Eggs",groceries);
            searchItem("unknowItem",groceries);
        }
        catch(ItemNotFoundException infe){
            System.out.println(infe.getMessage());
        }
        System.out.println("Average price of groceries:" + calculateAveragePrice(unitPrice));
        System.out.println("Average price empty array:" + calculateAveragePrice(new float[0]));
        System.out.println("Average price empty array:" + calculateAveragePrice(new float[]{}));
        System.out.println("Average price null object:" + calculateAveragePrice(null));
        System.out.println("Average price of : {10.5f, 20.3f, 30.7f}t:" + calculateAveragePrice(new float[]{10.5f, 20.3f, 30.7f}));
        System.out.println("Test 1: Threshold = 50");
        filterItemsBelowPrice(groceries, unitPrice, 50.0f);

        System.out.println("\nTest 2: Threshold = 25");
        filterItemsBelowPrice(groceries, unitPrice, 25.0f);

        System.out.println("\nTest 3: Threshold = 20");
        filterItemsBelowPrice(groceries, unitPrice, 20.0f);

        System.out.println("\nTest 4: Threshold = 100");
        filterItemsBelowPrice(groceries, unitPrice, 100.0f);

        System.out.println("\nTest 5: Threshold = 300");
        filterItemsBelowPrice(groceries, unitPrice, 300.0f);

        System.out.println("\nTest 6: Threshold = 10");
        filterItemsBelowPrice(groceries, unitPrice, 10.0f);

    }
    public static int indexOf(String searchItem,String [] items){
        int index = -1;  // default if not found

        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(searchItem)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public static void searchItem(String searchItem,String [] items) throws ItemNotFoundException{
        int index = indexOf(searchItem,items);
        if(index==-1){
             // If the item is not found, throw the custom exception
             throw new ItemNotFoundException("Item '" + searchItem + "' not found. Please try again.");
        }
        else{
            System.out.println("Item "+searchItem+" found at index :"+index);
        }
    }
    /*
    public static float calculateAveragePrice(float[] prices){
        try{
            float sum =0.0f;
            for(float ele:prices){
                sum+=ele;
            }
            return sum/(float)prices.length;
        }
        catch(ArithmeticException ae){
            if(prices.length == 0){
                System.out.println("float array of prices cannot be empty.");
            }
            else
                System.out.println(ae.getMessage());
        }
        catch(NullPointerException npe){
            if(prices == null){
                System.out.println("float array of prices has to be initialized");
            }
            else
                System.out.println(npe.getMessage());
        }
        return 0.0f;
    }
    o/p:
    Average price of groceries:70.26667
    Average price empty array:NaN
    Average price empty array:NaN
    float array of prices has to be initialized
    Average price null object:0.0
    Average price of : {10.5f, 20.3f, 30.7f}t:20.5
    Why you got NaN instead of ArithmeticException

    In Java, integer division by zero (10 / 0) → throws ArithmeticException: / by zero.

    But floating-point division by zero (10.5f / 0.0f) → does NOT throw an exception.
    Instead it follows IEEE-754 floating-point rules:

    positiveNumber / 0.0 → Infinity

    negativeNumber / 0.0 → -Infinity

    0.0 / 0.0 → NaN

    So when you do:
    return sum / (float) prices.length;
    and prices.length == 0, you’re doing something / 0.0f, which gives NaN, not an exception.
    That’s why your catch (ArithmeticException ae) block never ran.

    */
    public static float calculateAveragePrice(float[] prices) {
        try {
            if (prices == null) {
                System.out.println("float array of prices has to be initialized");
                return 0.0f;
            }
    
            if (prices.length == 0) {
                System.out.println("float array of prices cannot be empty.");
                return 0.0f;
            }
    
            float sum = 0.0f;
            for (float ele : prices) {
                sum += ele;
            }
            return sum / prices.length; // safe now
    
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return 0.0f;
        }
    }
    /*
     o/p:
        Average price of groceries: 70.26667
        float array of prices cannot be empty.
        Average price empty array: 0.0
        float array of prices cannot be empty.
        Average price empty array: 0.0
        float array of prices has to be initialized
        Average price null object: 0.0
        Average price of : {10.5f, 20.3f, 30.7f}t: 20.5
    */ 
    /*
    Test Cases for filterItemsBelowPrice(items, unitPrice, threshold)

Threshold = 50.0f

Should print: Wheat Flour (40.0), Sugar (48.0), Salt (20.0), Milk (28.5), Bread (35.0), Tomatoes (30.0), Onions (25.0), Potatoes (22.0).

(8 items)

Threshold = 25.0f

Should print: Salt (20.0), Potatoes (22.0).

(2 items)

Threshold = 20.0f

Nothing (since comparison is < threshold, and Salt = 20.0 is not included).

(0 items)

Threshold = 100.0f

Should print: everything priced < 100 (i.e., all except Cheese (150.0), Apples (100.0), Chicken (250.0)).

(11 items)

Threshold = 300.0f

Should print all 15 items (since all < 300).

(15 items)

Threshold = 10.0f

Nothing printed (all items priced higher).

(0 items)
    */   
    public static void filterItemsBelowPrice(String[] items,float[] prices,float threshold){
        boolean first = true;
        for(int i =0;i<prices.length;i++){
            if(prices[i] < threshold){
                if(first){
                    System.out.println("Items below threshold are : ");
                    first=false;
                }
                System.out.println(items[i]);
            }
        }
    }
    public static void applyDiscount(double totalBillAmt){
        if(totalBillAmt > 100){
            totalBillAmt-=(totalBillAmt * 0.1);
            System.out.println("Your total bill after 10% discount is: $ " + totalBillAmt);
        }
    }

}