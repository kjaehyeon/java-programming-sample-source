package SerializeEx;
import java.io.*;

public class UserInfo implements Serializable{
    private static final long serialVersionUID = -4905609823569210688L;
    String name;
    String password;
    int age;

    public UserInfo(){
        this("Unkown","1111",0);
    }
    public UserInfo(String name,String password, int age){
        this.name = name;
        this.password = password;
        this.age = age;
    }
    public String toString(){
        return "( "+name+", "+password + ", " + age+" )";
    }
}

