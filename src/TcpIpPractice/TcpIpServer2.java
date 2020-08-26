package TcpIpPractice;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer2 implements Runnable{
    ServerSocket serverSocket;
    Thread[] ThreadArr;
    public static void main(String[] args) {
        //여러개의 쓰레드를 만들어 클라이언트의 요청을 병렬적으로 처리하는 서버
        TcpIpServer2 server = new TcpIpServer2(4);
        server.start();
    }

    public TcpIpServer2(int num){
        try{
            serverSocket = new ServerSocket(7777);
            System.out.println("서버가 준비되었습니다.");
            ThreadArr = new Thread[num];
        }catch (IOException ie){ie.printStackTrace();}
    }
    public void start(){
        for(int i = 0; i < ThreadArr.length; i++){
            ThreadArr[i] = new Thread(this);
            ThreadArr[i].start();
        }
    }
    @Override
    public void run() {
        while(true){
            try{
                System.out.println(getTime()+ " 연결요청을 기다립니다.");
                Socket socket = serverSocket.accept();
                System.out.println(getTime()+socket.getInetAddress()+" 로부터 연결요청이 들어왔습니다.");

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                //소켓의 출력 스트림을 얻는다.

                dataOutputStream.writeUTF("[NOTICE] Test Message from Server");
                System.out.println(getTime()+" 데이터를 전송했습니다.");
                //remote socket에 데이터 보낸다

                dataOutputStream.close();
                socket.close();
            } catch (IOException ie){ ie.printStackTrace(); }
        }
    }
    static String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("[hh:mm:ss]");
        return format.format(new Date());
    }
}
