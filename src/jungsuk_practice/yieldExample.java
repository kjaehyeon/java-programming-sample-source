package jungsuk_practice;

public class yieldExample {
    public static void main(String[] args) {
        threadex1 th1 = new threadex1("*");
        threadex1 th2 = new threadex1("**");
        threadex1 th3 = new threadex1("***");
        th1.start();
        th2.start();
        th3.start();
        try{
            Thread.sleep(2000);
            th1.suspend();
            Thread.sleep(2000);
            th2.suspend();
            Thread.sleep(3000);
            th1.resume();
            Thread.sleep(3000);
            th1.stop();
            th2.stop();
            Thread.sleep(2000);
            th3.stop();
        }catch (InterruptedException e){}
    }
}
class threadex1 implements Runnable {
     volatile boolean suspended = false;
     volatile boolean stopped = false;

     Thread th;

    threadex1(String name){
        th = new Thread(this , name);
    }

    @Override
    public void run(){
       while(!stopped){
           if(!suspended){
               System.out.println(Thread.currentThread().getName());
               try{
                   Thread.sleep(1000);
               }catch (InterruptedException e){}
           }else
               Thread.yield();
       }
        System.out.println(Thread.currentThread().getName()+ " - stopped");
    }
    public void suspend(){
        suspended = true;
        th.interrupt();
    }
    public void stop(){
        stopped = true;
        th.interrupt();
    }
    public void resume(){suspended = false;}
    public void start(){ th.start();}

}
