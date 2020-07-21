package jungsuk_practice;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//Conditon을 Cust가 원하는 음식의 종류로 더 세분화 하면 개선될 수 있다.
public class synchronizedExample2 {
    public static void main(String[] args) {
        Table table = new Table();

        new Thread(new cook(table),"COOK1").start();
        new Thread(new cook(table),"COOK2").start();
        new Thread(new customer(table,"dount"),"CUST1").start();
        new Thread(new customer(table,"buger"),"CUST2").start();
        new Thread(new customer(table,"pizza"),"CUST3").start();
        new Thread(new customer(table,"donut"),"CUST4").start();

        try{Thread.sleep(3000);}catch (InterruptedException e){}
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
    String[] dishNames = {"dount","donut", "pizza", "buger",};
    final int MAX_FOOD = 6;
    ArrayList<String> dishes = new ArrayList<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition forCook = lock.newCondition();
    private Condition forCust = lock.newCondition();

    //public synchronized void add(String dish){
    public void add(String dish){
        lock.lock();
        try {
            while (dishes.size() >= MAX_FOOD) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " is waiting... ");
                try {
                    forCook.await();//jungsuk_practice.cook 대기
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
            dishes.add(dish);
            forCust.signal(); // 기다리는 CUST 깨우기
            System.out.println("Dishes : " + dishes.toString());
        }finally {
            lock.unlock();
        }
    }

    public void remove(String dish){
        lock.lock();//synchronized (this){
        try {
            String name = Thread.currentThread().getName();
            while (dishes.size() == 0) {
                System.out.println(name + " is waiting...");
                try {
                    forCust.await();//CUST 기다리게 한다.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }

            while (true) {
                if (dishes.remove(dish)) {
                    forCook.signal();// jungsuk_practice.cook 깨우기
                    return;
                }
                try {
                    System.out.println(name + " is waiting...");
                    forCust.await(); //원하는 음식 없는 CUST 기다리게
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }//while(true)
            //}//synchronized
        }finally {
            lock.unlock();
        }
    }
    public int dishNum(){return dishNames.length;}
}