package sample1;
import java.io.*;

public class Sample1{
    public static void main(String[] args){
        if (args.length != 1){
            System.out.println("パラメータの数が違います。");
            System.exit(1);
        }

        try{
            File fl = new File(args[0]);
            println("ファイル名は" + fl.getName() + "です。");
            if(!fl.exists()){
                println("ファイルが見つかりません");
            }else{
                println("絶対パス:" + fl.getAbsolutePath());
                println("ファイルサイズ:" + fl.length() + "バイト");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    static private void println(String str){
        System.out.println(str);
    }
    static private void println(int str){
        System.out.println(str);
    }
}