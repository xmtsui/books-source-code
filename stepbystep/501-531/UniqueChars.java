/**
 * Implement an algorithm to determine if a string has all unique characters 
 * What if you can not use additional data structures?
 */
class UniqueChars{
	//Assume only ascii
	public static boolean isUniqueChars(String str)
	{
		boolean[] char_set = new boolean[256];
		for(int i=0; i<str.length(); i++)
		{
			int tmp = str.charAt(i);
			if(char_set[tmp])
				return false;
			char_set[tmp] = true;
		}
		return true;
	}

	//Assume 'a' to 'z'
	public static boolean isUniqueChars2(String str)
	{
		int checker=0;
		for(int i=0; i<str.length(); i++)
		{
			int tmp = str.charAt(i) - 'a';
			if((checker & (1<<tmp)) > 0 ) return false;
			checker |= (1<<tmp);
		}
		return true;
	}
	public static void main(String[] args)
	{
		String a = "abcdefg";
		String b = "aabbcc";
		String c = "AABC";
		System.out.println("a: " + isUniqueChars(a));
		System.out.println("b: " + isUniqueChars(b));
		System.out.println("c: " + isUniqueChars(c));

		System.out.println("---------------------");
		System.out.println("a: " + isUniqueChars2(a));
		System.out.println("b: " + isUniqueChars2(b));
		System.out.println("c: " + isUniqueChars2(c));
		/*
		int tmp=1;
		for(int i=0; i<16; i++)
		{
			tmp*=2;
		}	
		int t=0;
		System.out.println(tmp);
		t |= 1<<32;
		System.out.println(t&(1<<32));
		System.out.println('A' - 'a');*/
	}
}