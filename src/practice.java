

import java.util.Scanner;

public class practice {
    static int Answer;
    static int[] list = new int[1000000];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 0; test_case < T; test_case++) {
            int result = 0;
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            for(int i = n1; i <= n2 ; i++){
                list[i-1] = recursive(i);
                result += list[i-1];
            }
            Answer = result;
            System.out.println("Case #" + (test_case + 1));
            System.out.println(Answer);
        }
    }
    public static int recursive(int n){
        int result = 0 ;
        if(n == 1)
            return 0;
        if(list[n-1] != 0){
            return list[n-1];
        }
        if(n%2 == 0){
            result++;
            return result + recursive(n/2);
        }else{
            result++;
            return result + recursive(n+1);
        }
    }
    public static boolean isEven(int n){
        return n%2 == 0 ? true : false;
    }
}
