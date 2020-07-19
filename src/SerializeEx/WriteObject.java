package SerializeEx;

import java.io.*;
import java.util.*;

public class WriteObject {
    public static void main(String[] args) {
        try {
            String fileName = "User.ser";
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            ObjectOutputStream oos = new ObjectOutputStream(bos);

            UserInfo u1 = new UserInfo("Man","1234",20);
            UserInfo u2 = new UserInfo("Woman","4321",23);
            ArrayList<UserInfo> list = new ArrayList<>();

            list.add(u1);
            list.add(u2);

            oos.writeObject(u1);
            oos.writeObject(u2);
            oos.writeObject(list);
            oos.close();
            System.out.println("Serialize Finish");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
