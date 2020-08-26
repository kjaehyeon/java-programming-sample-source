package TcpIpPractice;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TcpIpClient {
    public static void main(String[] args) {
        try{
            String ServerIp = "127.0.0.1";//localhost(자기 자신)를 가르키는 가상 ip

            System.out.println("Connecting to Server. Server IP : "+ServerIp);

            Socket socket = new Socket(ServerIp,7777);
            //소캣 생성

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            //소켓의 입력 스트림 얻음

            System.out.println("Message from Server : "+dataInputStream.readUTF());
            System.out.println("Closing connection");

            dataInputStream.close();
            socket.close();
            System.out.println("Connection is closed");
        }catch (IOException ie){ie.printStackTrace();}
        
    }
}
