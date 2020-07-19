package programmers;


import java.util.*;

public class truck_problem {
    public static void main(String[] args) {
        int answer = 0;
        int bridge_length = 100;
        int weight = 100;
        int order =0;
        int[] truck_weight = {10,10,10,10,10,10,10,10,10,10};
        int goalTruck = 0;
        LinkedList<Integer> q = new LinkedList();

        while(goalTruck < truck_weight.length){
            answer++;
            if(!q.isEmpty() && locationOfLast(q)  >= bridge_length){
                if(q.getLast() != 0)
                    goalTruck++;
                q.removeLast();
            }
            if(order >= truck_weight.length){
                q.addFirst(new Integer(0));
                continue;
            }
            if(weightOnBridge(q) + truck_weight[order] > weight){
                q.addFirst(new Integer(0));
                continue;
            }
            q.addFirst(new Integer(truck_weight[order++]));

        }
        System.out.println(answer);
    }
    static int weightOnBridge(LinkedList q){
        int result = 0;
        Iterator<Integer> iterator = q.iterator();
        while(iterator.hasNext()){
            result += iterator.next();
        }
        return result;
    }
    static int locationOfLast(LinkedList linkedList){
        Iterator iterator = linkedList.iterator();
        int location =0;
        while(iterator.hasNext()){
            iterator.next();
            location++;
        }
        return location;
    }
}
