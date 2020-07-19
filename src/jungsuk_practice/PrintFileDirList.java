package jungsuk_practice;

import java.util.*;
import java.io.*;

public class PrintFileDirList {
    static int totaldir = 0;
    static int totalfile = 0;
    public static void main(String[] args)  {
        File dir = new File(".\\");

        printFileList(dir);

        System.out.println();
        System.out.println("총 " + totaldir +"개의 디렉토리");
        System.out.println("총 " + totalfile + "개의 파일" );
    }
    static void printFileList(File dir){
        try {
            System.out.println(dir.getCanonicalPath() + " 디렉토리");
        }catch (IOException ie){}

        File[] files = dir.listFiles();
        ArrayList<Integer> subDir = new ArrayList<>();

        for(int i = 0 ; i < files.length; i++){
            String fileName = files[i].getName();

            if(files[i].isDirectory()){
                fileName = "["+fileName+"]";
                subDir.add(i);
            }
            System.out.println(fileName);
        }

        int dirNum = subDir.size();
        int fileNum = files.length - dirNum;

        totalfile += fileNum;
        totaldir += dirNum;

        System.out.println(fileNum + "개의 파일, "+ dirNum + "개의 디렉토리");
        System.out.println();

        for (int index : subDir) {
            printFileList(files[index]);
        }
    }
}
