import java.io.*;
public class RintTest {
	static BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) {
		String input = "";
		// TODO Auto-generated method stub
		for (double i = -10;i <= 10;i+=0.2) {
			System.out.println(i + "\t->\t" + Math.rint(i)); 
		}
		System.out.println("RINT�m�F���");
		for(int a=0;a<10;a++) {
			double randnum = -10 + (Math.random() % 20);
			System.out.print(randnum + "\t->\t");
			try {
				input = kbd.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(Integer.parseInt(input) == Math.rint(randnum)) {
				System.out.println("����");
			}else {
				System.out.println("�s����");
			}
			System.out.println("�I���ł��B");
		}
	}

}
