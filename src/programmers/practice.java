package programmers;
import java.util.*;
public class practice {
    public static void main(String[] args) {
        int[] answer = {};
        int[] answers = {2,1,2,3,2,4,2,5,2,1,2,3,2,4,2,5,2,1,2,3,2,4,2,5,2,1,2,3,2,4,2,5};
        int[][] supoja = {{1,2,3,4,5},{2,1,2,3,2,4,2,5},{3,3,1,1,2,2,4,4,5,5}};
        int[] count = new int[3];
        int max = 0;
        int size = 0;
        for(int i = 0 ; i < 3; i++){
            for(int j = 0 ; j < answers.length; j ++){
                if(supoja[i][j%(supoja[i].length)] == answers[j]) count[i]++;
                System.out.print(supoja[i][j%(supoja[i].length)]);
            }
            System.out.println();
        }
        max =Math.max(Math.max(count[0],count[1]) , count[2]);
        System.out.println(max);

        for(int i = 0 ; i < 3 ; i++){
            if(max == count[i]) size++;
        }

        answer = new int[size];

        for(int i = 1 ; i <= answer.length ; i++){
            answer[i - 1] = i;
            System.out.print(answer[i-1]);
        }

    }
}
