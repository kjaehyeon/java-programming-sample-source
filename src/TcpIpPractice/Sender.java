package TcpIpPractice;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class Sender extends Thread{
    DataOutputStream out;
    String name;
    Socket socket;
    public Sender(Socket socket){
        this.socket = socket;
        try{
            out = new DataOutputStream(socket.getOutputStream());
            name = "["+socket.getInetAddress()+" : "+socket.getPort()+"]";
        }catch (IOException ie){ie.getStackTrace();}
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while(out != null){
            try{
                out.writeUTF(name+scanner.nextLine());
            }catch (IOException ie){ie.getStackTrace();}

        }
    }
}