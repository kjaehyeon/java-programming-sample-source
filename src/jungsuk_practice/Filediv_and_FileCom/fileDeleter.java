package jungsuk_practice.Filediv_and_FileCom;

import java.io.*;

public class fileDeleter {
    public static void main(String[] args) {
        File dir = new File(".\\");
        File[] list = dir.listFiles();

        for(int i = 0; i < list.length; i++){
            String fileName = list[i].getName();

            if(fileName.startsWith("facebook")){
                list[i].delete();
            }
        }
    }
}
