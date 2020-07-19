
public class synchronizedExample {
    public static void main(String[] args) {
        Runnable r = new ThreadEx();
        new Thread(r).start();
        new Thread(r).start();
    }
}
class Account{
    private int balance = 1000;

    public int getBalance(){
        return balance;
    }
    public synchronized void withdraw(int money){
        if(balance >= money){
            try{Thread.sleep(100);}catch (InterruptedException e){}
            balance -= money;
        }
    }
}
class ThreadEx implements Runnable{
    Account acc = new Account();
    public void run(){
        while(acc.getBalance() > 0){
            int money = (int)(Math.random() * 3 + 1)*100;
            acc.withdraw(money);
            System.out.println("acc balance : "+ acc.getBalance());
        }
    }
}
