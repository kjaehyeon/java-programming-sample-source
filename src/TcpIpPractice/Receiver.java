package TcpIpPractice;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

class Receiver extends Thread{
    DataInputStream in;
    String name;
    Socket socket;
    public Receiver(Socket socket){
        this.socket = socket;
        try{
            in = new DataInputStream(socket.getInputStream());
            name = "["+socket.getInetAddress()+" : "+socket.getPort()+"]";
        }catch (IOException ie){ie.getStackTrace();}
    }
    public void run(){
        while(in != null){
            try{
                System.out.println(in.readUTF());
            }catch (IOException ie){ie.getStackTrace();}

        }
    }
}