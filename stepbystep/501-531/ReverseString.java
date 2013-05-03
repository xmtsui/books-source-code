/**
 *Write code to reverse a C-Style String 
 *(C-String means that “abcd” is represented as five characters, including the null character )
 */ 
class ReverseString{
	public static String doReverse(String str)
	{
		//StringBuffer sb = new StringBuffer(str);
		StringBuilder sb = new StringBuilder(str);
		String reversedStr = new String(sb.reverse());
		return reversedStr;
	}

	public static String doReverse1(String str)
	{
		int len = str.length();
		char[] ch = new char[len];
		for(int i=len-2; i>=0; i--)
		{
			ch[len-2-i] = str.charAt(i);
		}
		ch[len-1] = str.charAt(len-1);
		return new String(ch);
	}

	public static String doReverse2(String str)
	{
		int len = str.length();
		char[] ch = new char[len];
		str.getChars(0, str.length(), ch, 0);
		for(int i=(len-2) >> 1; i>=0; i--)
		{
			char tmp = ch[i];
			ch[i] = ch[len-2-i];
			ch[len-2-i] = tmp;
		} 
		return new String(ch);
	}

	public static void main(String[] args)
	{
		String a = "abcdefg";
		System.out.println("String '" + a + "' reverse to be:" + doReverse(a));

		String b = "abcdefg";
		CString cstr = new CString(b);
		System.out.print("CString is: " + cstr.getCString());//with '\n' now
		System.out.println("Lenghth is: " + cstr.getCString().length());


		long start1 = System.currentTimeMillis();
		System.out.println("CString '" + cstr.getCString() + "' reverse to be:" + doReverse1(cstr.getCString()));
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;
		
		long start2 = System.currentTimeMillis();
		System.out.println("CString '" + cstr.getCString() + "' reverse to be:" + doReverse2(cstr.getCString()));
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;

		long start3 = System.currentTimeMillis();
		System.out.println("CString '" + cstr.getCString() + "' reverse to be:" + doReverse(cstr.getCString()));
		long end3 = System.currentTimeMillis();
		long time3 = end3 - start3;

		System.out.println( "time: " + time1 + " " + time2 + " " + time3);
	}

	static class CString{
		private String str;	

		public CString(){
		}

		public CString(String str)
		{
			this.str = str + "\n";
		}

		public String getCString()
		{
			return this.str;
		}
	}
}