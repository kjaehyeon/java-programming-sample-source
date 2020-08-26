package TcpIpPractice;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class TcpIpServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(7777);
            //serversocket을 7777번 포트와 bind 시킴
            System.out.println(getTime()+" 서버가 준비되었습니다.");
        }catch (IOException ioException){ioException.getStackTrace();}

        while(true){
            try{
                System.out.println(getTime()+ " 연결요청을 기다립니다.");
                Socket socket = serverSocket.accept();
                socket.setSoTimeout(5000);
                //서버소켓의 대기시간 설정함
                System.out.println(getTime()+socket.getInetAddress()+" 로부터 연결요청이 들어왔습니다.");

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                //소켓의 출력 스트림을 얻는다.

                dataOutputStream.writeUTF("[NOTICE] Test Message from Server");
                System.out.println(getTime()+" 데이터를 전송했습니다.");
                //remote socket에 데이터 보낸다

                dataOutputStream.close();
                socket.close();
            }catch (SocketTimeoutException socketTimeoutException){
                System.out.println("지정된 시간동안 연결 요청이 없어서 서버를 종료합니다.");
                System.exit(0);
            } catch (IOException ie){ ie.printStackTrace(); }
        }
    }
    static String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("[hh:mm:ss]");
        return format.format(new Date());
    }
}
