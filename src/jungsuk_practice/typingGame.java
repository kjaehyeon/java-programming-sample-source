import java.util.*;

public class typingGame {
    ArrayList words = new ArrayList();
    String[] data = {"김재현","김강현","박영미","김주천","유지애","조대근","김경록","한승엽","강준구","김태원"};
    int interval = 1000*2;

    public static void main(String[] args) {

    }
    public void delay(int millis){
        try{
            Thread.sleep(millis);
        }catch(Exception e){}
    }
}
class WordGenerator{
    
}