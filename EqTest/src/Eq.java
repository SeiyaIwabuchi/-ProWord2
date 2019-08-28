public class Eq {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String p1 = new String("ABC"), p2 = new String("ABC"),p3 = p1;
		
		System.out.println(p1 == p2);
		//equalsメソッドを使った場合は値が同じであればtrueが返ってくる。
		System.out.println(p1.equals(p2));
	}

}

class X{}