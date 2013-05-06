class Value{
	public int i=5;
}

public class Test_1 {
	public static void main(String argv[]){
		Test_1 t=new Test_1();
		t.first();
	}

	public void first(){
		int i=5;
		Value v=new Value();
		v.i=25;
		second(v,i);
		System.out.print(v.i);
	}

	public void second(Value v, int i){
		i=0;
		v.i=20;
		Value val=new Value();
		v=val;
		System.out.print(v.i+" "+i);
	}
}