class Tiger{
	public char meth_char() {
		return 'a';
	}
	public byte meth_byte() {
		return 2;
	}
	public short meth_short() {
		return 3;
	}
	public int meth_int() {
		return 4;
	}
	public long meth_long() {
		return 5;
	}
	public float meth_float() {
		return new Float(6);
		//return 6.0f;
		//return (float)6.0;
	}
	public double meth_double() {
		return 0.7d;
	}
	public boolean meth_bool() {
		return true;
	}
	public void meth_void() {
		return;
	}
}


public class MyTigre {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tiger t = new Tiger();
		char c = t.meth_char();
		System.out.println("char meth = " + c);
		byte b = t.meth_byte();
		System.out.println("byte meth = " + b);
		short s = t.meth_short();
		System.out.println("short meth = " + s);
		int i = t.meth_int();
		System.out.println("int meth = " + i);
		long l = t.meth_long();
		System.out.println("long meth = " + l);
		float fl = t.meth_float();
		System.out.println("float meth = " + fl);
		double db = t.meth_double();
		System.out.println("double meth = " + db);
		boolean bl = t.meth_bool();
		System.out.println("boolean meth = " + bl);
		t.meth_void();
		System.out.println("void meth = ‰½‚à–ß‚è‚Ü‚¹‚ñ");
		String str1="abc",str2=str1;
		System.out.println(str1 == str2);
		str1 += "def";
		System.out.println(str1 == str2);
		System.out.println("abc" == str2);
	}
}
