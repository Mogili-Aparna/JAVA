import java.util.Scanner;
public class BooksMenu {
    public static void main(String s[]) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        Book[] books = new Book[10];
        int bkIdx = 0;
        while(true) {
            System.out.println("Press 1 to view books, 2 to add books, 3 to get Highest Price book, any other key to exit");
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
                System.out.println("Enter book title");
                String tmpTitle = scanner.nextLine();
                System.out.println("Enter book author");
                String tmpAuthor = scanner.nextLine();
                System.out.println("Enter book price");
                float tmpPrice = Float.parseFloat(scanner.nextLine());
                Book bkTmp = new Book();
                bkTmp.setTitle(tmpTitle);
                bkTmp.setAuthor(tmpAuthor);
                bkTmp.setPrice(tmpPrice);
                books[bkIdx++] = bkTmp;

            } else if (userAction.equals("3")){
                try{
                    System.out.println("Enter first book Index to compare");
                    int book1Idx = scanner.nextInt();
                    System.out.println("Enter second book Index to compare");
                    int book2Idx = scanner.nextInt();
                    Book hightestPriceBook = getHighestPriceBook(books[book1Idx],books[book2Idx]);
                    System.out.println("Most Expensive Book is:  ");
                    System.out.println(hightestPriceBook);
                }
                catch(IllegalArgumentException iae){
                    System.out.println(iae.getMessage());
                }
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