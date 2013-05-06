/**
 * Design an algorithm and write code to remove the duplicate characters in a string without using any additional buffer 
 * NOTE: One or two additional variables are fine An extra copy of the array is not
 * Write the test cases for this method:
 * 1)String does not contain any duplicates, e g : abcd
 * 2)String contains all duplicates, e g : aaaa
 * 3)Null string
 * 4)Empty string
 * 5)String with all continuous duplicates, e g : aaabbb
 * 6)String with non-contiguous duplicates, e g : abababa
 * @author tsui
 *
 */
class DuplicateString{

	/**
	 * Assume all ascii
	 * Use String built-in method
	 * @param  str
	 * @return
	 */
	public static String removeDuplicates(String str)
	{
		if(str==null)
			return "";
		int len = str.length();
		long checker=0;
		for(int i=0; i<len; i++)
		{
			int tmp = str.charAt(i) - 'A';
			if(i>=1 && (checker & (1<<tmp)) > 0)
			{
				//str = str.replace(str.charAt(i), '\0'); //this is wrong, replace all
				//str = str.substring(0,i-1)+str.substring(i+1, len);//substring is from begin to end-1
				str = str.substring(0,i)+str.substring(i+1, len);
				len = str.length();
			    i--;//redo from present index;
			}
			checker |= (1<<tmp);
		}
		return str;
	}

	/**
	 * use char[], no more space consume, O(n^2)
	 * Algorithm No (Large) Additional Memory
	 * 1) For each character, check if it is a duplicate of already found characters
	 * 2) Skip duplicate characters and update the non duplicate characters
	 * @param  str
	 * @return
	 */
	public static String removeDuplicates1(String str)
	{
		if(str == null)
			return "";
		int len = str.length();
		
		/*char[] ch = new char[len];
		str.getChars(0,len,ch,0);//srcBegin, srcEnd, dst, dstBegin*/
		
		char[] ch = str.toCharArray();

		for(int i=1; i<len; ++i)
		{
			int j=0;
			for(j=0; j<i; ++j)
			{
				if(ch[i] == ch[j])
			    {
			    	ch[i]=0;
					break;
				}
			}
		}
		return new String(ch);
	}

	/**
	 * use char[]
	 * Assume all ascii
	 * @param  str
	 * @return
	 */
	public static String removeDuplicates2(String str)
	{
		if(str == null)
			return "";

		char[] ch = str.toCharArray();
		long checker=0;
		int len = ch.length;
		for(int i=0; i<len; i++)
		{
			int tmp = ch[i] - 'A';
			if((checker & (1<<tmp))>0)
			{
				ch[i]=0;
			}
			checker |= (1<<tmp);
		}
		return new String(ch);
	}
	/**
	 * [main description]
	 * @param  args
	 * @return
	 */
	public static void main(String[] args)
	{
		String[] a={"abcd","aaa", "a", null, "", "aaabbbcccd", "abccbaicbaabcd"};
		String b="";
		for(int i=0;i<50;i++)
			b+="abccbaicbaabcd";
		
		for(String item: a)
			System.out.println(item + "|"+ removeDuplicates(item));

		System.out.println("-------------------");

		for(String item: a)
			System.out.println(item + "|"+ removeDuplicates1(item));

		System.out.println("-------------------");

		for(String item: a)
			System.out.println(item + "|"+ removeDuplicates2(item));

		long start1 = System.currentTimeMillis();
		removeDuplicates(b);
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;

		long start2 = System.currentTimeMillis();
		removeDuplicates1(b);
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;

		long start3 = System.currentTimeMillis();
		removeDuplicates2(b);
		long end3 = System.currentTimeMillis();
		long time3 = end3 - start3;

		System.out.println("For big data, time1: " + time1 + "| time2: "+ time2 + "| time3: " + time3);
	}
}