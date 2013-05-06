/**
 *Write code to reverse a C-Style String 
 *(C-String means that abcd is represented as five characters, including the null character )
 */ 
class ReverseString{
	/**
	 * 不考虑换行符
	 * @param  str [description]
	 * @return     [description]
	 */
	public static String doReverse(String str)
	{
		//StringBuffer sb = new StringBuffer(str);
		StringBuilder sb = new StringBuilder(str);
		String reversedStr = new String(sb.reverse());
		return reversedStr;
	}

	/**
	 * 借助额外数组, time: o(n), space: o(n)
	 * @param  str [description]
	 * @return     [description]
	 */
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

	/**
	 * 现转化成数组，再对称交换, time: o(n/2), space: o(0);
	 * @param  str [description]
	 * @return     [description]
	 */
	public static String doReverse2(String str)
	{
		int len = str.length();
		// char[] ch = new char[len];
		// str.getChars(0, str.length(), ch, 0);
		char[] ch = str.toCharArray();
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
		System.out.println("String '" + a + "' reverse to be: " + doReverse(a));
		// System.out.println("String '" + a + "' reverse to be: " + doReverse1(a));
		// System.out.println("String '" + a + "' reverse to be: " + doReverse2(a));

		CString b = new CString(a);
		// System.out.print("CString is: " + cstr.getCString());//with '\n' now
		// System.out.println("Lenghth is: " + cstr.getCString().length());
		System.out.println("CString '" + b + "' reverse to be: " + doReverse(b.getCString()));
		System.out.println("CString '" + b + "' reverse to be: " + doReverse1(b.getCString()));
		System.out.println("CString '" + b + "' reverse to be: " + doReverse2(b.getCString()));

		String c = "";
		for(int i=0; i<10000; i++)
			c+="abcdefg";

		CString cstr = new CString(c);

		long start = System.currentTimeMillis();
		doReverse(cstr.getCString());
		long end = System.currentTimeMillis();
		long time = end - start;

		long start1 = System.currentTimeMillis();
		doReverse1(cstr.getCString());
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;
		
		long start2 = System.currentTimeMillis();
		doReverse2(cstr.getCString());
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;


		System.out.println( "time consume: " + time + " " + time1 + " " + time2);
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