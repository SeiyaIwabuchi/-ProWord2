import java.lang.*;
public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testStr = "SeiyaIwabuchi";
		String testStr2 = testStr;
		System.out.println("Sample String\n-----------------\n" + testStr + "\n" + testStr2);
		
		testStr2 += " TE2A";
		
		System.out.println("testStr2 += \" TE2A\"; \t ->\t" + testStr2);
		
		for(int i=0;i<testStr.length();i++) {
			System.out.println("testStr[" + i + "] -> " + testStr.charAt(i));
		}
		
	}

}
