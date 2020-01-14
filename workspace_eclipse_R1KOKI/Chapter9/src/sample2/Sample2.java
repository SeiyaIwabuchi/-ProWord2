package sample2;
import java.io.*;

public class Sample2{
    public static void main(String[] args){
        if (args.length != 2){
            System.out.println("パラメータの数が違います。");
            System.exit(1);
        }

        try{
            File file[] = {new File(args[0]),new File(args[1])};
            for(int i=0;i<file.length;i++){
                if(!file[i].exists()){
                    println("ファイルが見つかりません");
                }else{
                    println(String.format("変更前ファイル名:%s",file[i]));
                    println("ファイルサイズ:" + file[i].length());
                }
            }
            File file2 = new File(file[1].getPath()); //file2.txtを指す
            File file3 = new File(file[0].getPath()); //file1.txtを指す
            file[1].renameTo(new File(file[1].getPath() + "_t")); //ファイル名を実際に変更。この時点でFileオブジェクトの参照は切り替わらない。
            file[1] = new File(file[1].getPath() + "_t"); //参照を変更 ここで変更しないと↑を実行前のファイルを探しに行く
            file[0].renameTo(file2); //名前をfile2.txtに変更
            file[1].renameTo(file[0]); //名前をfile1.txtに変更
            file[1] = file3; //file1.txtへ参照切り替え
            file[0] = file2; //file2.txtへ参照切り替え
            println("###############################\n#2つのファイルを入れ替えます。#\n###############################");
            for(int i=0;i<file.length;i++){
                if(!file[i].exists()){
                    println("ファイルが見つかりません");
                }else{
                    println(String.format("変更後ファイル名:%s",file[i]));
                    println("ファイルサイズ:" + file[i].length());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    static private void println(String str){
        System.out.println(str);
    }
    static private void println(boolean bn){
        System.out.println(bn);
    }
}