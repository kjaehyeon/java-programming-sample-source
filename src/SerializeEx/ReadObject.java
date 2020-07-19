package SerializeEx;

import java.io.*;
import java.util.*;

public class ReadObject {
    public static void main(String[] args) {
        try{
            FileInputStream fis = new FileInputStream(".\\User.ser");
            BufferedInputStream bis = new BufferedInputStream(fis);

            ObjectInputStream ois = new ObjectInputStream(bis);

            UserInfo u1 = (UserInfo)ois.readObject();
            UserInfo u2 = (UserInfo)ois.readObject();
            ArrayList<UserInfo> list = (ArrayList)ois.readObject();

            System.out.println(u1);
            System.out.println(u2);
            System.out.println(list);
            ois.close();
        }catch (Exception ie){
            ie.printStackTrace();
        }
    }
}
