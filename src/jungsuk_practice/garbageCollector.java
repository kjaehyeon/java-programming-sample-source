package jungsuk_practice;

public class garbageCollector {
    public static void main(String[] args) {
        threadEx gc = new threadEx();
        gc.setDaemon(true);
        gc.start();

        int requiredMemory = 0;

        for(int i = 0; i <20 ; i++){
            requiredMemory = (int)(Math.random()*10)*20;

            if(requiredMemory > gc.freeMemory() || gc.freeMemory() < gc.totalMemory() *0.4){

                try{
                    gc.interrupt();
                    gc.join(1);
                }catch (InterruptedException e){}
                //gc.interrupt();
            }
            gc.usedMemory += requiredMemory;
            System.out.println("usedMemory : " + gc.usedMemory);
        }
    }
}
class threadEx extends Thread{
    final static int MAX_MEMORY = 1000;
    int usedMemory = 0;

    @Override
    public void run(){
        while(true) {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                System.out.println("GC AWAKE");
            }
            gc();
            System.out.println("Garbage Collected, Free Memory : " + freeMemory());
        }
    }
    public void gc(){
        usedMemory -= 300;
        if(usedMemory < 0)
            usedMemory = 0;
    }
    public int freeMemory(){ return MAX_MEMORY - usedMemory;}
    public int totalMemory(){ return MAX_MEMORY;}
}
