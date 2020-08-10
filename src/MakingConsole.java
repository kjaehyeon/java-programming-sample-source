import java.io.File;
import java.io.IOException;
import java.util.*;

public class MakingConsole {
    static String[] argArr = null;
    static Queue queue = new LinkedList();
    static final int MAX_SIZE = 10;

    static File curDir;
    static {
        try {
            curDir = new File(".\\");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            try {
                String prompt = curDir.getCanonicalPath() + ">>";
                System.out.print(prompt);
            }catch (IOException ie){
                ie.getStackTrace();
            }
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
                for(String str : argArr)
                    System.out.println(str);
            }
        }
    }
    static void save(String s){
        if(s != null && !s.equals(""))
            queue.offer(s);
        if(queue.size() >= MAX_SIZE)
            queue.poll();
    }
    static void history(){
        Iterator iterator = queue.iterator();
        int count = 1;
        while(iterator.hasNext()) {
            System.out.println(count++ + " : " + queue.poll());
        }
    }
}
