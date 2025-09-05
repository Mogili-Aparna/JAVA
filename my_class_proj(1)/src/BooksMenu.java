import java.util.Scanner;
public class BooksMenu {
    public static void main(String s[]) throws CloneNotSupportedException{
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        Book[] books = new Book[10];
        int bkIdx = 0;
        while(true) {
            System.out.println("Press 1 to view books, 2 to add books, 3 to get Highest Price book,4 to compare 2 books,5 to update price, any other key to exit");
            String userAction = scanner.nextLine();
            if (userAction.equals("1")) {
                for(int i=0;i<books.length;i++) {
                    if(books[i] != null) {
                        System.out.println(books[i]);
                    }
                }
            } else if (userAction.equals("2")) {
                if(bkIdx == 10) {
                    System.out.println("10 books added already. Cannot add any more books!");
                    continue;
                }
                /*System.out.println("Enter book title");
                String tmpTitle = scanner.nextLine();
                System.out.println("Enter book author");
                String tmpAuthor = scanner.nextLine();
                System.out.println("Enter book price");
                float tmpPrice = Float.parseFloat(scanner.nextLine());
                Book bkTmp = new Book();
                bkTmp.setTitle(tmpTitle);
                bkTmp.setAuthor(tmpAuthor);
                bkTmp.setPrice(tmpPrice);
                books[bkIdx++] = bkTmp;*/
                System.out.println("Press 1 to create a new Object, 2 to clone an existing object, any other key to exit");
                int option = scanner.nextInt();
                scanner.nextLine();
                if(option == 1)
                {
                    System.out.println("Which constructor do you want to use? Press 1 for default,"+
                    "any other key for overloaded constructor");
                    String constructor = scanner.nextLine();
                    System.out.println("Enter book title");
                    String tmpTitle = scanner.nextLine();
                    System.out.println("Enter book author");
                    String tmpAuthor = scanner.nextLine();
                    System.out.println("Enter book price");
                    float tmpPrice = Float.parseFloat(scanner.nextLine());
                    if (constructor.equals("1")) {
                        Book bkTmp = new Book();
                        bkTmp.setTitle(tmpTitle);
                        bkTmp.setAuthor(tmpAuthor);
                        bkTmp.setPrice(tmpPrice);
                        books[bkIdx++] = bkTmp;
                    } else {
                        books[bkIdx++] = new Book(tmpTitle, tmpAuthor, tmpPrice);
                    }
                }
                else if(option == 2){
                    System.out.println("Enter index of the book to be cloned");
                    int idx = scanner.nextInt();
                    scanner.nextLine();
                    if(idx>-1 && idx<books.length){
                        books[bkIdx++]=(Book)books[idx].clone();
                    }
                    else{
                        System.out.println("invalid book index. value should be in between: 0 and "+books.length);
                    }
                }

            } else if (userAction.equals("3")){
                try{
                    System.out.println("Enter first book Index to compare");
                    int book1Idx = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter second book Index to compare");
                    int book2Idx = scanner.nextInt();
                    scanner.nextLine();
                    Book hightestPriceBook = getHighestPriceBook(books[book1Idx],books[book2Idx]);
                    System.out.println("Most Expensive Book is:  ");
                    System.out.println(hightestPriceBook);
                }
                catch(IllegalArgumentException iae){
                    System.out.println(iae.getMessage());
                }
            }else if (userAction.equals("4")){
                System.out.println("Enter first index of book to compare: ");
                int idx1 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter second index of book to compare: ");
                int idx2 = scanner.nextInt();
                scanner.nextLine();
                if(idx1<0 && idx1>books.length &&idx2>0 && idx2>books.length) {
                    System.out.println("invalid book index. value should be in between: 0 and "+books.length);
                }
                else{
                    System.out.println("comparing books :"+BookAccess.compareBooks(books[idx1], books[idx2]));
                }
            }
            else if(userAction.equals("5")){
                System.out.println("Enter index of book to change price: ");
                int idx3 = scanner.nextInt();
                scanner.nextLine();
                if(idx3<0 && idx3>books.length) {
                    System.out.println("invalid book index. value should be in between: 0 and "+books.length);
                }
                System.out.println("Enter latest price");
                float price = scanner.nextFloat();
                scanner.nextLine();
                books[idx3].setPrice(price);
            }
            else {
                break;
            }
        }
    }
    private static Book getHighestPriceBook(Book book1,Book book2){
        if(book1== null && book2!=null)
            return book2;
        else if(book1!= null && book2==null)
            return book1;
        else if(book1== null && book2==null){
            throw new IllegalArgumentException("pass books to compare the price");
        }
        else{
            if(book1.getPrice()<=book2.getPrice()){
                return book2;
            }
            else {
                return book1;
            }
        }
    }
}