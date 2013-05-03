import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class ArrayListTest{
	public static void main(String args[])
	{
		List<String> l = new ArrayList<String>();
		l.add("hello");
		Iterator i = l.iterator();


		while(i.hasNext())
		{
			System.out.println(i.next());
		}
	}

	public ArrayList<String> merge(String[] words, String[] more)
	{
		ArrayList<String> al = new ArrayList<String>();
		for(String i : words) al.add(i);
		for(String i : more) al.add(i);
		return al;
	}
}
