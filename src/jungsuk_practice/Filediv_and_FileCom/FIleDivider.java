package jungsuk_practice.Filediv_and_FileCom;

import java.io.*;

public class FIleDivider {
    public static void main(String[] args) {
        File file = new File(".\\src\\facebook.png");

        final int VOLUME = 400; // 분할하고자 하는 크기 (단위 byte)

        try{
            String fileName = file.getName();

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);

            FileOutputStream fos = null;
            BufferedOutputStream bos = null;

            int data = 0;
            int i = 0;
            int number = 0;

            while((data = bis.read()) != -1){
                if(i%VOLUME == 0){
                    if(i != 0)
                        bos.close();

                    fos = new FileOutputStream(fileName + "_." + ++number);
                    bos = new BufferedOutputStream(fos);
                }
                bos.write(data);
                i++;
            }
            bis.close();
            bos.close();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }

    }
}
