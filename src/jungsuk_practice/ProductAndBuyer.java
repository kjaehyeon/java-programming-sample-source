

import java.util.*;

class product{
    int price;
    int bonuspoint;

    product(int price){
        this.price = price;
        bonuspoint = (int)(price * 0.1);
    }
}
class tv extends product{
    tv(){
        super(100);
    }
    public String toString() {
        return "com.tv";
    }
}
class computer extends product{
    computer(){
        super(200);
    }
    public String toString() {
        return "com.computer";
    }
}
class sofa extends product{
    sofa(){
        super(70);
    }
    public String toString() {
        return "com.sofa";
    }
}
class buyer{
    int money;
    int bonuspoint;
    Vector item = new Vector();
    buyer(int money, int bonuspoint){
        this.money = money;
        this.bonuspoint = bonuspoint;
    }
    void buy(product p){
        if(money < p.price){
            System.out.println("Not enough Money!");
        }
        else{
            money -= p.price;
            bonuspoint += p.bonuspoint;
            item.add(p);
            System.out.println(p.toString() + "을 구매하셨습니다.");
        }
    }
    void refund(product refunditem){
        if(item.remove(refunditem)){
            System.out.println(refunditem.toString()+ "을 반품하셨습니다.");
            money += refunditem.price;
            bonuspoint -= refunditem.bonuspoint;
        }
        else System.out.println("제거에 실패하였습니다.");
    }
    void summary(){
        int sum =0;
        String List = "";
        if(item.isEmpty()) System.out.println("구입하신 상품이 없습니다.");
        for(int i =0; i<item.size(); i++){
            product p = (product)item.get(i);
            sum += p.price;
            List += (i ==0)? " "+p.toString() : ", "+ p.toString();
        }

        System.out.println("total price :"+sum);
        System.out.println("com.product List :"+List);
    }
}

public class ProductAndBuyer{
    public static void main(String[] args){
        buyer b = new buyer(1000, 0);
        product p = new sofa();
        computer com = new computer();
        b.buy(new tv());
        b.buy(com);
        b.buy(new sofa());
        b.summary();
        System.out.println();
        b.refund(com);
        b.summary();


    }
}
