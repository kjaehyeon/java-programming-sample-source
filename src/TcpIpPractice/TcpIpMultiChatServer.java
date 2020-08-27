package TcpIpPractice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class TcpIpMultiChatServer {
    HashMap clients;
    TcpIpMultiChatServer(){
        clients = new HashMap();
        Collections.synchronizedMap(clients);
    }
    public static void main(String[] args) {
        new TcpIpMultiChatServer().start();
    }
    public void start(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            serverSocket = new ServerSocket(7777);
            System.out.println("서버가 시작하였습니다.");
            while(true){
                socket = serverSocket.accept();
                System.out.println("["+socket.getInetAddress()+" : "+socket.getPort()+"] 에서 접속함");
                ServerReceiver serverReceiver = new ServerReceiver(socket);
                serverReceiver.start();
            }
        }catch (IOException ie){ie.printStackTrace();}
    }
    void sendToAll(String message){
        Iterator iterator = clients.keySet().iterator();

        while(iterator.hasNext()){
            try{
                DataOutputStream out = (DataOutputStream)clients.get(iterator.next());
                out.writeUTF(message);
            }catch (IOException ie){ie.printStackTrace();}
        }
    }
    class ServerReceiver extends Thread{
        Socket socket;
        DataOutputStream out = null;
        DataInputStream in = null;
        ServerReceiver(Socket socket){
            this.socket = socket;
            try{
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
            }catch (IOException ie){ie.printStackTrace();}
        }
        public void run(){
            String name = "";
            try{
                name = in.readUTF();
                clients.put(name,out);
                sendToAll("#"+name+"님이 들어오셨습니다.");
                System.out.println("현재 접속자 수 : "+clients.size());
                while(in != null){
                    sendToAll(in.readUTF());
                }
            }catch (IOException ie){ie.printStackTrace();}
            finally {
                sendToAll("#"+name+"님이 나가셨습니다.");
                clients.remove(name);
                System.out.println("["+socket.getInetAddress()+" : "+socket.getPort()+"] 에서 접속종료");
                System.out.println("현재 접속자 수 : "+clients.size());
            }
        }
    }
}
