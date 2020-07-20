import java.util.*;

public class synchronizedExample2 {
    public static void main(String[] args) {
        Table table = new Table();

        new Thread(new cook(table),"COOK1").start();
        new Thread(new customer(table,"dount"),"CUST1").start();
        new Thread(new customer(table,"buger"),"CUST2").start();

        try{Thread.sleep(5000);}catch (InterruptedException e){}
        System.exit(0);

    }
}
class customer implements Runnable{
    private Table table;
    private String food;

    customer(Table table, String food){
        this.table = table;
        this.food = food;
    }
    @Override
    public void run() {
        while(true){
            try{Thread.sleep(100);}catch (InterruptedException e){}
            String name = Thread.currentThread().getName();

            table.remove(food);
            System.out.println(name+ " ate a " + food);
            /*if(eatFood())
                System.out.println(name + " ate a "+food);
            else
                System.out.println(name + " failed to eat...");*/
        }
    }
    //boolean eatFood(){return table.remove(food);}
}
class cook implements Runnable{
    private Table table;

    cook(Table table){ this.table = table;}

    @Override
    public void run() {
        while(true){
            int idx = (int)(Math.random()*table.dishNum());
            table.add(table.dishNames[idx]);
            try{Thread.sleep(10);}catch (InterruptedException e){}
        }
    }
}
class Table{
    String[] dishNames = {"dount", "dount", "buger"};
    final int MAX_FOOD = 6;

    ArrayList<String> dishes = new ArrayList<>();
    public synchronized void add(String dish){
        String name = Thread.currentThread().getName();
        while(dishes.size() >= MAX_FOOD){
            System.out.println(name+" is waiting... ");
            try{
                wait();
                Thread.sleep(500);
            }catch (InterruptedException e){}
        }
        dishes.add(dish);
        notify(); // 기다리는 CUST 깨우기
        System.out.println("Dishes : " + dishes.toString());
    }
    public void remove(String dish){
        synchronized (this){
            String name = Thread.currentThread().getName();
            while(dishes.size() == 0){
                System.out.println(name+" is waiting...");
                try{
                    wait();//CUST 기다리게 한다.
                    Thread.sleep(500);
                }catch (InterruptedException e){}
            }

            while(true){
                if(dishes.remove(dish)){
                    notify();// cook 깨우기
                    return;
                }
                try{
                    System.out.println(name + " is waiting...");
                    wait(); //원하는 음식 없는 CUST 기다리게
                    Thread.sleep(500);
                }catch (InterruptedException e){}
            }//while(true)
        }//synchronized

    }
    public int dishNum(){return dishNames.length;}
}