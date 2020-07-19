package Filediv_and_FileCom;

import java.io.*;

public class FileCombiner {
    public static void main(String[] args) {

        String combineFileName = "facebook.png"; // 복구할 파일 이름

        try{
            FileOutputStream fos = new FileOutputStream(combineFileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            BufferedInputStream bis = null;

            int number = 1;

            File f = new File(".\\" +combineFileName + "_." + number);

            while(f.exists()){
                f.setReadOnly();

                bis = new BufferedInputStream(new FileInputStream(f));

                int data = 0;

                while((data = bis.read()) != -1){
                    bos.write(data);
                }
                bis.close();

                f.delete(); // 분할된 파일 없에기

                f = new File(combineFileName + "_." + ++number);
            }
            bos.close();

        }catch (IOException ioException){
            ioException.printStackTrace();
        }

    }
}
