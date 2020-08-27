package TcpIpPractice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TcpIpMultiChatClient {
    public static void main(String[] args) {
        String name;
        System.out.println("대화명을 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();

        try{
            String ServerIp = "127.0.0.1";

            Socket socket = new Socket(ServerIp,7777);
            System.out.println("서버에 연결되었습니다.");

            Thread sender = new Thread(new ClientSender(socket,name));
            Thread Receiver = new Thread(new ClientReceiver(socket));
            sender.start();
            Receiver.start();
        }catch (Exception e){e.printStackTrace();}
    }
    static class ClientSender implements Runnable{
        Socket socket;
        String name;
        DataOutputStream out;
        ClientSender(Socket socket, String name){
            this.socket = socket;
            this.name = name;
            try{
                out = new DataOutputStream(socket.getOutputStream());
            }catch (IOException ie){ie.printStackTrace();}
        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            try{
                if(out != null){
                    out.writeUTF(name);
                }
                while(out != null){
                    out.writeUTF("["+name+"] : "+scanner.nextLine());
                }
            }catch (IOException ie){ie.printStackTrace();}
        }
    }
    static class ClientReceiver implements Runnable{
        Socket socket;
        DataInputStream in;

        ClientReceiver(Socket socket){
            this.socket = socket;
            try{
                in = new DataInputStream(socket.getInputStream());
            }catch (IOException ie){ie.printStackTrace();}
        }
        @Override
        public void run() {
            while(in != null){
                try{
                    System.out.println(in.readUTF());
                }catch (IOException ie){ie.printStackTrace();}
            }
        }
    }
}
