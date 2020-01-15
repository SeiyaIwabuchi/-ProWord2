package sample5S;

import java.util.*;
import java.io.*;
import java.net.*;

public class Sample5S {
    public static final int PORT = 10000;

    public static void main(String[] args) {
        Sample5S sm = new Sample5S();

        try {
            ServerSocket ss = new ServerSocket(PORT);

            System.out.println("待機します。");
            while (true) {
                try {

                    Socket sc = ss.accept();
                    System.out.println("ようこそ。");

                    Client cl = new Client(sc);
                    cl.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Client extends Thread {
    private Socket sc;
    private BufferedReader br;
    private PrintWriter pw;

    public Client(Socket s) {
        sc = s;
    }

    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));
        } catch (Exception e) {

            e.printStackTrace();
        }

        while (true) {
            try {
                String str = br.readLine();
                pw.println("サーバ:「" + str + "」ですね。");
                pw.flush();
                System.out.println("クライアント:" + str); //サーバーコンソールでも受信した文字列を表示するように変更。
            } catch (Exception e) {
                try {
                    br.close();
                    pw.close();
                    sc.close();
                    System.out.println("さようなら。");
                    break;
                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        }
    }
}