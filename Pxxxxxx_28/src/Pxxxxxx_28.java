import java.io.*;
public class Pxxxxxx_28 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte dt;
		int ct = 0;
		String filePath = "./bin/" + args[0];

		DataInputStream bin = null; // 便利な機能を提供するクラス

		if( args.length  != 1) {  // プログラム引数が1でないとき             
			System.out.println("usage: java Pxxxxxx_28 filename");
			System.exit(1);
		}

		try {
			bin = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath))); 

			while(true) {
				try {
					if(ct % 16 == 0) {
						System.out.println();
						System.out.print(int2hex(ct, 8) + ": "); 
					}
					dt = bin.readByte();
					System.out.print(int2hex(dt,2) + " ");
					ct += 1;
				} catch(EOFException e) {
					System.out.println();
					break;
				}
			}
			bin.close();
		} catch(FileNotFoundException e) {
			System.out.println(args[0]+"を開くことができません。"); 
		} catch    (IOException e) { // 入出力例外 
			System.out.println("err: " + e);
		}
	}
	public static String int2hex(int dt, int n) {
		if(n > 8) n = 8;
		String ss = "00000000" + Integer.toHexString(dt);
		return ss.substring(ss.length()-n).toUpperCase();
	}
}
