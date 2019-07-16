// Pxxxxxx_26.java
// xxxxxx 氏名 
import java.  io  .*;
class Pxxxxxx_26 {
	public static void main(String[] args) throws IOException {
		String str, str2;
		int n[] = new int[4];
		int index1 = 0, index2 = 0;  

		System.out.print("IP address = ");
		BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
		str = kbd. readLine (); 

		for(int i=0; i < 4; i++) {
			index2 = str.indexOf (".", index1);  // ピリオドの位置を調べる
			if(index2 == -1)
				index2 = str.length ();  // 見つからないときは最後までとする 
			n[i] = Integer. parseInt  (str.substring(index1, index2)); // 切り出し10進数に変換
			str2 = "00000000" + Integer.toBinaryString(n[i]);       // 先頭にゼロを付加
			System.out.println("第"+(i+1)+"オクテット : " +str2.substring(str2.length()-8, str2.length()));   // 後ろから８文字切り出す
			index1 = index2 + 1;  // 次の10進数の先頭位置         } 
		}

		if(n[0] < 128)
			System.out.println("クラスＡです");     // 第１オクテットの左が 0
		else if(n[0] < 192)
			System.out.println("クラスＢです");     // 第１オクテットの左が 10
		else if(n[0] < 224)
			System.out.println("クラスＣです");     // 第１オクテットの左が110
	}
} 