
public class D28 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 0) {
			System.err.println("引数を与えてください。");
			return;
		}
		System.out.println("10進数	->	2進数	");
		for(String str : args) {
			subfunc(str);
		}
	}

	public static void subfunc(String n) {
		try {
			System.out.println(n + "\t->\t" + Integer.toBinaryString(Integer.parseInt(n)));
		}catch (java.lang.NumberFormatException e){
			//e.printStackTrace();
			System.err.println("入力値の" + n + "は10進数ではありません。");
		}
	}
}