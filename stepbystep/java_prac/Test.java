class A{
	public void printValue(){
		System.out.println("A");
	}
}

class S extends A{
	public void printValue(){
		System.out.println("S");
	}
}

public class Test {
	public static void main(String[] args){
		S s=new S();
		s.printValue();
		A as=(A)s;
		as.printValue();
		as=new A();
		as.printValue();

		A a1 = new S();
		a1.printValue();



		String s1="java";
		String s2="java";
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));

		String s0="ja";
		String s3 = "ja" + "va";
		// String s3=s0+"va";
		String s4="java";
		System.out.println(s3==s4);
		System.out.println(s3.equals(s4));
	}
}