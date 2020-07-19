import org.omg.PortableInterceptor.ServerRequestInfo;
import java.io.*;

public class try_with_resource {
    public static void main(String[] args) {
        try(CloseableResource cr = new CloseableResource()){
            cr.exceptionWork(false);
        }catch(WorkException e){
            e.printStackTrace();
        }catch(CloseException e){
            e.printStackTrace();
        }
        System.out.println();
        try(CloseableResource cr = new CloseableResource()){
            cr.exceptionWork(true);
        }catch(WorkException e){
            e.printStackTrace();
        }catch(CloseException e){
            e.printStackTrace();
        }
    }
}
class CloseableResource implements AutoCloseable{
    public void exceptionWork(boolean exception) throws WorkException{
        System.out.println("exceptionWork("+exception+")가 호출됨");

        if(exception)
            throw new WorkException("WorkException occur!");
    }
    public void close() throws CloseException{
        System.out.println("close() is called");
        throw new CloseException("CloseException occur!");
    }
}
class WorkException extends Exception{
    WorkException(String msg){ super(msg);}
}
class CloseException extends Exception{
    CloseException(String msg){super(msg);}
}