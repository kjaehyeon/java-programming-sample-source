package jungsuk_practice;

import java.util.*;

public class hashMap_PhoneBook_input_HashMap_In_Value {
    static HashMap phoneBook = new HashMap();
    public static void main(String[] args) {
        String[] group = {"","친구","가족","회사"};
        String[] name = {"김","이","박","최","강"};

        for(int i = 0 ; i < 20; i++){
            addPhoneNo(group[(int)(Math.random()*group.length)],name[(int)(Math.random()*name.length)], i);
        }
        printBook();
    }
    static void addPhoneNo(String group, String name, int tel){
        if(group.equals(""))
            group = "기타";
        addGroup(group);
        HashMap Group = (HashMap)phoneBook.get(group);
        Group.put(tel,name);
    }
    static void addPhoneNo(String name, int tel){
        addPhoneNo("기타",name,tel);
    }
    static void addGroup(String groupname){
        if(!phoneBook.containsKey(groupname))
            phoneBook.put(groupname,new HashMap());
    }
    static void printBook(){
        Set set = phoneBook.entrySet();
        Iterator iterator = set.iterator();

        while(iterator.hasNext()){
            Map.Entry e = (Map.Entry)iterator.next();

            Set subset = ((HashMap)e.getValue()).entrySet();
            Iterator iterator1 = subset.iterator();

            System.out.println("*" + e.getKey() + "["+subset.size()+"]");
            while(iterator1.hasNext()){
                Map.Entry subE = (Map.Entry)iterator1.next();

                System.out.println( subE.getValue()+" "+subE.getKey());
            }

        }
    }
}

