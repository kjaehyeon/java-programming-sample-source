package jungsuk_practice;

class sutdadeck{
    final int CARD_NUM = 20;
    sutdacard[] card = new sutdacard[CARD_NUM];

    sutdadeck(){
        for(int i =0; i<CARD_NUM; i++){
            card[i] = new sutdacard();
            if(i<10){
                card[i].num = i+1;
                card[i].isKwang = false;
                if(i+1 == 1 || i+1 == 3 || i+1 == 8){
                    card[i].isKwang = true;
                }
            }
            else{
                card[i].num = i-9;
                card[i].isKwang = false;
            }
        }
    }

    void shuffle(){
        for(int i =0; i < CARD_NUM; i++){
            int ranNum = (int)(Math.random() * CARD_NUM);
            sutdacard tmp = card[ranNum];
            card[ranNum] = card[i];
            card[i] = tmp;
        }
    }
    sutdacard pick(){
        int ranNum = (int)(Math.random() * CARD_NUM);
        return card[ranNum];
    }
    sutdacard pick(int index){
        if(index>20){
            System.out.println("input small than 20!");
            return null;
        }
        return card[index-1];
    }
}
class sutdacard{
    int num;
    boolean isKwang;
    sutdacard(){
        this(1,true);
    }
    sutdacard(int num, boolean isKwang){
        this.num = num;
        this.isKwang = isKwang;
    }
    public String toString(){
        return num + (isKwang?"k":"");
    }
}
public class Sutda {
    public static void main(String[] args) {
        sutdadeck deck = new sutdadeck();
        deck.shuffle();
        for(int i =0; i < deck.card.length; i++){
            System.out.print(" "+deck.card[i].toString());
        }
        System.out.println();
        System.out.println(" "+deck.pick(21));
    }

}
