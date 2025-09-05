// Custom exception class for item not found
public class ItemNotFoundException extends Exception{
    public ItemNotFoundException(String msg){
        super(msg);
    }
}