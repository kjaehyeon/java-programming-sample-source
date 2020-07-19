import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class practice2 {
    public static void main(String[] args) {
        int answer = 0;
        String skill = "CBDF";
//        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA", "CB","CBAF","CBD"};
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA","CBF"};
        for(String str : skill_trees){
            int temp = 0;
            boolean decide = true;
            for(int i = 0 ; i < skill.length(); i++){
                int num = str.indexOf(skill.charAt(i));
                if(num >= temp && temp != -1){
                    temp = num;
                }else if(num == -1){
                    temp = -1;
                    continue;
                }
                else {
                    decide = false;
                    break;
                }
            }
            if(decide)
                answer++;
        }
        System.out.println(answer);
    }
}
