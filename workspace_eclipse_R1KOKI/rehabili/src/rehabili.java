abstract class Animal{
	abstract int meth();
}

interface Mammal{
	void meth2();
}

class Cat extends Animal implements Mammal{
	
	public Cat() {
		
	}
	
	@Override
	public int meth() {
		// TODO Auto-generated method stub
		System.out.println("meth���\�b�h���Ăяo����܂����B");
		return 0;
	}
	
	public int meth(int a) {
		// TODO Auto-generated method stub
		System.out.println("meth(int�������Ɏ���)���\�b�h���Ăяo����܂����B");
		return 0;
	}
	
	public int meth(String str) {
		// TODO Auto-generated method stub
		System.out.println("meth(String�������Ɏ���)���\�b�h���Ăяo����܂����B");
		return 0;
	}

	@Override
	public void meth2() {
		// TODO Auto-generated method stub
		System.out.println("meth2���\�b�h���Ăяo����܂����B");
	}
	
}

class Tiger extends Cat{
	
	private String name;
	private int age;
	static private int id = 0;
	Tiger(String name,int age){
		super();
		this.age = age;
		this.name = name;
	}
	
	Tiger(){
		this(new Integer(id++).toString(),-1);
	}
	
	public String toString() {
		return String.format("name:%s\tage:%d",this.name,this.age);
	}
	
	@Override
	public void meth2() {
		System.out.println("Tiger�N���X�ŃI�[�o�[���C�h���ꂽ���\�b�h");
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return this.age;
	}
	
}

public class rehabili {
	
	public static void main(String[] args) {
		Cat myCat = new Cat();
		Tiger mt = new Tiger();
		System.out.println(mt);
	}

}
