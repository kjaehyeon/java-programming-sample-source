package TcpIpPractice;

import java.net.Socket;

public class TcpIpChatClient {
    public static void main(String[] args) {
        String ServerIp = "127.0.0.1";
        try{
            Socket socket = new Socket(ServerIp,7777);

            System.out.println("서버에 연결되었습니다.");

            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            sender.start();
            receiver.start();
        }catch (Exception e){e.printStackTrace();}

    }
}
