
public class D28 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 0) {
			System.err.println("������^���Ă��������B");
			return;
		}
		System.out.println("10�i��	->	2�i��	");
		for(String str : args) {
			subfunc(str);
		}
	}

	public static void subfunc(String n) {
		try {
			System.out.println(n + "\t->\t" + Integer.toBinaryString(Integer.parseInt(n)));
		}catch (java.lang.NumberFormatException e){
			//e.printStackTrace();
			System.err.println("���͒l��" + n + "��10�i���ł͂���܂���B");
		}
	}
}