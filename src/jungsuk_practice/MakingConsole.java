
import java.util.*;

public class MakingConsole {
    static String[] argArr = null;
    static  ArrayList list = new ArrayList();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            String prompt = ">>";
            System.out.print(prompt);
            String input = scanner.nextLine();

            save(input);

            input = input.trim();

            argArr = input.split(" +");
            String command = argArr[0].trim();

            if(command.equals("")) continue;

            if(command.toLowerCase().equals("q"))
                System.exit(0);
            else if(command.equals("history"))
                history();
            else {
                for (int i = 0; i < argArr.length; i++) {
                    System.out.println(argArr[i]);
                }
            }
        }
    }
    static void save(String s){
        list.add(s);
    }
    static void history(){
        int size = list.size();

        for(int i = 0; i < size; i++){
            System.out.println((i+1)+" : "+list.get(i));
        }
    }
}
